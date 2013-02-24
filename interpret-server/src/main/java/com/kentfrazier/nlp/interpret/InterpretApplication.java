package com.kentfrazier.nlp.interpret;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class InterpretApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/interpret/", InterpretationResource.class);
		return router;
	}

}
