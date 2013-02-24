package com.kentfrazier.nlp.interpret;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class InterpretationResource extends ServerResource {
	
	public InterpretationResource() {
		
	}

	@Get("html")
	public String toString() {
		return "<html><body>Hello, World!</body></html>";
	}
	
	@Get("json")
	public Representation toJSON() {
		return null;
	}
	
	@Get("xml")
	public Representation toXML() {
		return null;
	}

}
