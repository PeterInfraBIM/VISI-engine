package nl.infrabim.visi.graphql;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import graphql.schema.DataFetchingEnvironment;

@Component
public class Mutation implements GraphQLMutationResolver {
	public Mutation() {
	}

	public Boolean multipleUpload(List<Part> parts, DataFetchingEnvironment env) {
		// get file parts from DataFetchingEnvironment, the parts parameter is not use
		List<Part> attachmentParts = env.getArgument("files");
		int i = 1;
		for (Part part : attachmentParts) {
			String uploadName = "copy" + i;
			try {
				part.write("your path:" + uploadName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}
		return true;

	}
}
