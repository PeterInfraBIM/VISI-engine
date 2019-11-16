package nl.infrabim.visi.translator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.ontology.CardinalityRestriction;
import org.apache.jena.ontology.MaxCardinalityRestriction;
import org.apache.jena.ontology.MinCardinalityRestriction;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.Restriction;
import org.apache.jena.ontology.UnionClass;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.core.io.FileSystemResource;

public class ElementMetaType extends MetaType {
	private static MapService<ElementMetaType> elemMetaTypeSrvc = new MapService<>();

	private static OntModel model;
	private MapService<PropertyTypeRestriction> propTypeRestrictSrvc = new MapService<>();

	private ElementMetaType() {
		super();
	}

	private ElementMetaType(String id) {
		super(id);
	}

	private PropertyTypeRestriction addProperty(String propertyId, Integer minCard, Integer maxCard) {
		PropertyMetaType propertyMetaType = PropertyMetaType.get(propertyId);
		PropertyTypeRestriction propertyTypeRestriction = new PropertyTypeRestriction(propertyMetaType, minCard,
				maxCard);
		return propTypeRestrictSrvc.put(propertyId, propertyTypeRestriction);
	}

	public List<String> getPropertyNames() {
		return propTypeRestrictSrvc.getNames();
	}

	public static ElementMetaType put(String id) {
		return elemMetaTypeSrvc.put(id, new ElementMetaType(id));
	}

	public static ElementMetaType get(String id) {
		return elemMetaTypeSrvc.get(id);
	}

	public static List<String> getNames() {
		return elemMetaTypeSrvc.getNames();
	}

	@Override
	public void print() {
		System.out.println("Element type: " + getId());
		System.out.println("\tProperties: ");
		for (String propName : getPropertyNames()) {
			System.out.print("\tid=\"" + propName + "\" ");
			System.out.print("\tmin. card: " + propTypeRestrictSrvc.get(propName).getMinCardinality());
			System.out.print("\tmax. card: " + propTypeRestrictSrvc.get(propName).getMaxCardinality());
			System.out.print("\tmandatory: " + propTypeRestrictSrvc.get(propName).isMandatory());
			System.out.print("\tmultiple: " + propTypeRestrictSrvc.get(propName).isMultiple());
			System.out.println();
		}
	}

	private void getProperties() {
		Resource elementTypeRsrc = model.createResource(VisiXmlRdfTranslator.VISI_RDF_URI + "#" + getId());

		// Properties from restriction subclasses
		StmtIterator subClassOfStatements = model.listStatements(elementTypeRsrc, RDFS.subClassOf, (Resource) null);
		while (subClassOfStatements.hasNext()) {
			RDFNode object = subClassOfStatements.nextStatement().getObject();
			if (object.isResource()) {
				StmtIterator typestatements = model.listStatements(object.asResource(), RDF.type, (Resource) null);
				if (typestatements.hasNext()) {
					Resource superClass = typestatements.nextStatement().getObject().asResource();
					if (superClass.equals(OWL.Restriction)) {
						Restriction restriction = object.as(Restriction.class);
						OntProperty onProperty = restriction.getOnProperty();
						Integer minCardinality = getMinCardinality(restriction);
						Integer maxCardinality = getMaxCardinality(restriction);
						Integer cardinality = getCardinality(restriction);
						if (minCardinality == null && maxCardinality == null) {
							minCardinality = cardinality;
							maxCardinality = cardinality;
						}
						addProperty(onProperty.getLocalName(), minCardinality, maxCardinality);
					}
				}
			}
		}

		// Properties from property domain specifications
		List<String> names = PropertyMetaType.getNames();
		for (String id : names) {
			PropertyMetaType propertyMetaType = PropertyMetaType.get(id);
			List<String> domain = propertyMetaType.getDomain();
			if (domain != null && domain.contains(getId())) {
				addProperty(id, null, null);
			}
		}
	}

	private Integer getCardinality(Restriction restriction) {
		if (restriction.canAs(CardinalityRestriction.class)) {
			return restriction.asCardinalityRestriction().getCardinality();
		}
		return null;
	}

	private Integer getMinCardinality(Restriction restriction) {
		if (restriction.canAs(MinCardinalityRestriction.class)) {
			return restriction.asMinCardinalityRestriction().getMinCardinality();
		}
		return null;
	}

	private Integer getMaxCardinality(Restriction restriction) {
		if (restriction.canAs(MaxCardinalityRestriction.class)) {
			return restriction.asMaxCardinalityRestriction().getMaxCardinality();
		}
		return null;
	}

	private static Resource getRange(OntProperty onProperty) {
		StmtIterator rangeSttmnts = model.listStatements(onProperty, RDFS.range, (Resource) null);
		if (rangeSttmnts.hasNext()) {
			return rangeSttmnts.nextStatement().getObject().asResource();
		}
		return null;
	}

	private static String getRangeName(OntProperty onProperty) {
		Resource range = getRange(onProperty);
		return range != null ? range.getLocalName() : null;
	}

	private static List<Resource> getDomain(OntProperty onProperty) {
		List<Resource> domainRsrc = null;
		StmtIterator domainSttmnts = model.listStatements(onProperty, RDFS.domain, (Resource) null);
		while (domainSttmnts.hasNext()) {
			domainRsrc = new ArrayList<>();
			Resource object = domainSttmnts.nextStatement().getObject().asResource();
			if (object.isAnon()) {
				UnionClass union = object.as(UnionClass.class);
				ExtendedIterator<? extends OntClass> operands = union.listOperands();
				while (operands.hasNext()) {
					domainRsrc.add(operands.next());
				}
			} else {
				domainRsrc.add(object);
			}
		}
		return domainRsrc;
	}

	private static List<String> getDomainNames(OntProperty onProperty) {
		List<String> domainNames = null;
		List<Resource> domainList = getDomain(onProperty);
		if (domainList != null) {
			domainNames = new ArrayList<>();
			for (Resource domain : domainList) {
				domainNames.add(domain.getLocalName());
			}
		}
		return domainNames;
	}

	public static void initialize() throws IOException {
		model = ModelFactory.createOntologyModel();
//		model.read(new FileInputStream(VisiXmlRdfTranslator.VISI_RDF_FILE), null, "TURTLE");
		model.read(new FileSystemResource("src/main/resources/visi/visi16.ttl").getInputStream(), null, "TURTLE");

		// Collect all defined object properties
		List<Resource> objectProperties = getObjectProperties();
		for (Resource objectProperty : objectProperties) {
			OntProperty property = model.createOntProperty(objectProperty.getURI());
			PropertyMetaType.put(property.getLocalName(), getRangeName(property), getDomainNames(property));
		}

		// Collect all defined datatype properties
		List<Resource> datatypeProperties = getDatatypeProperties();
		for (Resource datatypeProperty : datatypeProperties) {
			OntProperty property = model.createOntProperty(datatypeProperty.getURI());
			PropertyMetaType.put(property.getLocalName(), getRangeName(property), getDomainNames(property));
		}
		for (String propId : PropertyMetaType.getNames()) {
			PropertyMetaType propertyMetaType = PropertyMetaType.get(propId);
			propertyMetaType.print();
			System.out.println();
		}

		// Collect all defined classes
		StmtIterator classSttmnts = model.listStatements((Resource) null, RDF.type, OWL.Class);
		while (classSttmnts.hasNext()) {
			Resource subject = classSttmnts.nextStatement().getSubject();
			if (subject.isAnon())
				continue;
			ElementMetaType elementMetaType = ElementMetaType.put(subject.getLocalName());
			elementMetaType.getProperties();
		}
		for (String classId : ElementMetaType.getNames()) {
			ElementMetaType elementMetaType = ElementMetaType.get(classId);
			elementMetaType.print();
			System.out.println();
		}
	}

	private static List<Resource> getObjectProperties() {
		List<Resource> objectProperties = new ArrayList<>();
		StmtIterator objectPropertySttmnts = model.listStatements((Resource) null, RDF.type, OWL.ObjectProperty);
		while (objectPropertySttmnts.hasNext()) {
			Resource subject = objectPropertySttmnts.nextStatement().getSubject();
			if (subject.isAnon())
				continue;
			objectProperties.add(subject);
		}
		return objectProperties;
	}

	private static List<Resource> getDatatypeProperties() {
		List<Resource> datatypeProperties = new ArrayList<>();
		StmtIterator datatypePropertySttmnts = model.listStatements((Resource) null, RDF.type, OWL.DatatypeProperty);
		while (datatypePropertySttmnts.hasNext()) {
			Resource subject = datatypePropertySttmnts.nextStatement().getSubject();
			if (subject.isAnon())
				continue;
			datatypeProperties.add(subject);
		}
		return datatypeProperties;
	}

	public PropertyTypeRestriction getPropertyTypeRestriction(String propertyId) {
		return propTypeRestrictSrvc.get(propertyId);
	}

}
