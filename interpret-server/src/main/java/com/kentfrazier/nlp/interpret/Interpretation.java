package com.kentfrazier.nlp.interpret;

public class Interpretation {
	
	private final String location;
	private final String time;
	private final String subject;
	
	public Interpretation(String location, String time, String subject) {
		this.location = location;
		this.time = time;
		this.subject = subject;
	}
	
	public Interpretation(String sentence) {
		this(null, null, null);
	}

	public String getLocation() {
		return location;
	}

	public String getTime() {
		return time;
	}

	public String getSubject() {
		return subject;
	}

}
