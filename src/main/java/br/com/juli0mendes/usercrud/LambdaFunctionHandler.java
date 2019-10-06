package br.com.juli0mendes.usercrud;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.juli0mendes.usercrud.domain.Person;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

	@Override
	public String handleRequest(Object input, Context context) {
		
		String output = null;
		
        try {
        	ObjectMapper obj = new ObjectMapper();
        	
        	String payloadString = obj.writeValueAsString(input);
        	
			Person person = obj.readValue(payloadString, Person.class);

			context.getLogger().log("Name: " + person.getName());
			context.getLogger().log("Age: " + person.getAge());
			
			output = "Hello, " + person.getName() + "!";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}
}