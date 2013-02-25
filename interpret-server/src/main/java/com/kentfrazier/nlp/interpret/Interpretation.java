package com.kentfrazier.nlp.interpret;

import edu.stanford.nlp.pipeline.Annotation;

public class Interpretation {
	
	private final Annotation annotation;
	private final String subject;
	private final String location;
	private final String time;
	
	public Interpretation(String subject, String location, String time) {
		this.annotation = null;
		this.subject = subject;
		this.location = location;
		this.time = time;
	}
	
	public Interpretation(String phrase) {
		this.annotation = PhraseParser.parsePhrase(phrase);
		this.subject = extractSubject();
		this.location = extractLocation();
		this.time = extractTime();
	}

	private String extractTime() {
		return null;
	}

	private String extractLocation() {
		return null;
		
	}

	private String extractSubject() {
		return null;
	}

	public String getSubject() {
		return subject;
	}
	
	public String getLocation() {
		return location;
	}

	public String getTime() {
		return time;
	}

}
