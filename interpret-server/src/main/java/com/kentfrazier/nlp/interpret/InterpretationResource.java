package com.kentfrazier.nlp.interpret;

import java.io.IOException;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class InterpretationResource extends ServerResource {

	private Interpretation interpretation;

	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		Form params = getRequest().getResourceRef().getQueryAsForm(true);
		String phrase = params.getFirstValue("phrase");
		interpretation = new Interpretation(phrase);
	}

	@Get("json")
	public Representation toJSON() {
		return new JsonRepresentation(interpretation);
	}

	@Get("xml")
	public Representation toXML() throws IOException {
		DomRepresentation representation = new DomRepresentation();
		Document doc = representation.getDocument();

		Element rootElement = doc.createElement("phrase");
		doc.appendChild(rootElement);

		Element subject = doc.createElement("subject");
		subject.appendChild(doc.createTextNode(interpretation.getSubject()));

		Element location = doc.createElement("location");
		location.appendChild(doc.createTextNode(interpretation.getLocation()));

		Element time = doc.createElement("time");
		time.appendChild(doc.createTextNode(interpretation.getTime()));

		rootElement.appendChild(subject);
		rootElement.appendChild(location);
		rootElement.appendChild(time);

		return representation;
	}

}
