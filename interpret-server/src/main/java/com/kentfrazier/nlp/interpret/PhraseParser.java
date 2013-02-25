package com.kentfrazier.nlp.interpret;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class PhraseParser {
	
	private PhraseParser() {}
	
	private static final String PROP_FILE = "StanfordCoreNLP.properties";
	private static StanfordCoreNLP pipeline =
			new StanfordCoreNLP(PROP_FILE);
	
	public static Annotation parsePhrase(String phrase) {
		Annotation annotation = pipeline.process(phrase);
		return annotation;
	}
	
	public static String parsePhraseToXML(String phrase) {
		Annotation annotation = parsePhrase(phrase);
		
		Writer writer = new StringWriter();
		try {
			pipeline.xmlPrint(annotation, writer);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return writer.toString();
	}
	
	public static void preload() {
		// Don't need to do anything, just have to have something
		// to run so that we can preload the parser.
	}

}
