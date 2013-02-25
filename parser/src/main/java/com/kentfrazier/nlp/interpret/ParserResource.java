package com.kentfrazier.nlp.interpret;

//import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
//import java.io.StringWriter;
//import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserResource extends ServerResource {
	
//	private static Transformer transformer;
	private static XPathExpression piXPathExpr;
	
	{
//		TransformerFactory factory = TransformerFactory.newInstance();
//		URL xslURL = ParserResource.class.getResource("/CoreNLP-to-HTML.xsl");
//		Source xsl = new StreamSource(new File(xslURL.getFile()));
//		try {
//			transformer = factory.newTransformer(xsl);
//		} catch (TransformerConfigurationException e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		try {
			piXPathExpr = xpath.compile("//processing-instruction()");
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private String phrase;

	@Override
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		Form params = getRequest().getResourceRef().getQueryAsForm(true);
		phrase = params.getFirstValue("phrase");
	}
	
	private String getXML() {
		return PhraseParser.parsePhraseToXML(phrase);
	}
	
//	@Get("html")
//	public String toHTML() {
//		String xml = getXML();
//		
//		StringWriter writer = new StringWriter();
//		StreamResult result = new StreamResult(writer);
//		
//		try {
//			transformer.transform(new StreamSource(xml), result);
//		} catch (TransformerException e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
//		
//		String html = writer.toString();
//		return html;
//	}
	
	@Get("xml")
	public Representation toXML() {
		Document document = parseXMLToDocument(getXML());
		
		NodeList nodes;
		try {
			nodes = (NodeList) piXPathExpr.evaluate(
					document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
		
		for (int i = 0, size = nodes.getLength(); i < size; ++i) {
			ProcessingInstruction pi = (ProcessingInstruction) nodes.item(i);
			pi.getParentNode().removeChild(pi);
		}
		
		return new DomRepresentation(MediaType.TEXT_XML, document);
	}
	
	private static Document parseXMLToDocument(String xml) {
		Reader reader = new StringReader(xml);
		InputSource source = new InputSource(reader);
		DocumentBuilder builder = null;
		try {
			builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Document document = null;
		try {
			document = builder.parse(source);
		} catch (SAXException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return document;
	}

}
