package nl.infrabim.visi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import nl.infrabim.visi.upload.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class VisiEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisiEngineApplication.class, args);
	}

	@Bean
	public GraphQLScalarType jsonType() {
		return ExtendedScalars.DateTime;
	}
}
