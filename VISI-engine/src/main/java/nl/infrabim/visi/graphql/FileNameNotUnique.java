package nl.infrabim.visi.graphql;

import java.util.List;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

@SuppressWarnings("serial")
public class FileNameNotUnique extends RuntimeException implements GraphQLError {

	public FileNameNotUnique(String message) {
		super(message);
	}

	public FileNameNotUnique(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public StackTraceElement[] getStackTrace() {
		return null;
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return null;
	}

}
