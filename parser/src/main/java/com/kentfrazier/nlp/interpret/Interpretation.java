package com.kentfrazier.nlp.interpret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;
//import groovy.ui.Console;

//// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
//Properties props = new Properties();
//props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
//StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//// read some text in the text variable
//String text = ... // Add your text here!
//
//// create an empty Annotation just with the given text
//Annotation document = new Annotation(text);
//
//// run all Annotators on this text
//pipeline.annotate(document);
//
//// these are all the sentences in this document
//// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
//List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//
//for(CoreMap sentence: sentences) {
//  // traversing the words in the current sentence
//  // a CoreLabel is a CoreMap with additional token-specific methods
//  for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
//    // this is the text of the token
//    String word = token.get(TextAnnotation.class);
//    // this is the POS tag of the token
//    String pos = token.get(PartOfSpeechAnnotation.class);
//    // this is the NER label of the token
//    String ne = token.get(NamedEntityTagAnnotation.class);       
//  }
//
//  // this is the parse tree of the current sentence
//  Tree tree = sentence.get(TreeAnnotation.class);
//
//  // this is the Stanford dependency graph of the current sentence
//  SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
//}
//
//// This is the coreference link graph
//// Each chain stores a set of mentions that link to each other,
//// along with a method for getting the most representative mention
//// Both sentence and token offsets start at 1!
//Map<Integer, CorefChain> graph = 
//  document.get(CorefChainAnnotation.class);

public class Interpretation {
	
	private final Annotation document;
	private final List<Map<String, String>> sentences;
	
	public Interpretation(String phrase) {
		
		sentences = new ArrayList<Map<String, String>>();
		document = PhraseParser.parsePhrase(phrase);
		
		for (CoreMap sentence : document.get(SentencesAnnotation.class)) {
			parseSentence(sentence);
		}
		
	}

	private void parseSentence(CoreMap sentence) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("sentence", sentence.toString());
		
		System.out.println(sentence.toString());
		
		List<String> timeWords = new ArrayList<String>();
		
		List<CoreLabel> tokens = sentence.get(TokensAnnotation.class);
		SemanticGraph dependencies = sentence.get(
				CollapsedCCProcessedDependenciesAnnotation.class);
		Tree tree = sentence.get(TreeAnnotation.class);
		
   // 	Console console = new Console();
   // 	console.setVariable("sentence", sentence);
   // 	console.setVariable("tokens", tokens);
   // 	console.setVariable("dependencies", dependencies);
   // 	console.setVariable("tree", tree);
   // 	console.run();
		
		for (CoreLabel token : tokens) {
		    String text = token.get(TextAnnotation.class);
		    String ner = token.get(NamedEntityTagAnnotation.class);
		    if (ner.equals("DATE") || ner.equals("TIME")) {
		    	timeWords.add(text);   	
		    }
		}
		
		map.put("time", StringUtils.join(timeWords, " "));
		
		
		for (SemanticGraphEdge edge : dependencies.edgeIterable()) {
			String edgeType = edge.getRelation().toString(); 
		    System.out.println(edge.toString());
		}
		
		
		sentences.add(map);
		
		System.out.println();
		System.out.println();
	}

	public List<Map<String, String>> getSentences() {
		return sentences;
	}
	
	

}
