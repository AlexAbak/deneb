package org.deneblingvo.serialization.xml.plain;
import java.util.*;

public class NsList {

	private Hashtable<String, String> namespaces = new Hashtable<String, String>();
	
	private int nsNumber = 0;

	public String getPrefix(String namespaceURI)
	{
		// TODO: Implement this method
		return null;
	}

	public void addAttrNs(String prefix, String namespaceURI, String elementNamespaceURI) {
		if (namespaceURI == null) {
			namespaceURI = elementNamespaceURI;
		}
		this.addNs(prefix, namespaceURI);
	}

	public void addNs(String prefix, String namespaceURI) {
		if (!this.namespaces.containsKey(namespaceURI)) {
			if (this.namespaces.containsValue(prefix)) {
				this.nsNumber++;
				String nsPrefix = "ns" + Integer.toString(this.nsNumber);
				this.namespaces.put(namespaceURI, nsPrefix);
			} else {
				this.namespaces.put(namespaceURI, prefix);
			}
		}
	}
	
}
