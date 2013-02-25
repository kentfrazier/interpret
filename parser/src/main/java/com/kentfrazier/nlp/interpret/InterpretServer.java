package com.kentfrazier.nlp.interpret;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class InterpretServer {
	
	private static int DEFAULT_PORT = 8080; 

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		PhraseParser.preload();
		System.out.println("Parser loaded!");
		
		int port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
			    port = Integer.parseInt(args[0], 10);
			} catch (NumberFormatException error) {
				System.err.println("Port must be a number : " + args[0]);
				System.exit(1);
			}
		}
		Component component = new Component();
        component.getServers().add(Protocol.HTTP, port);
		component.getDefaultHost().attach("", new InterpretApplication());
		component.start();
	}

}
