package org.deneblingvo.serialization.xml.plain;
import java.util.*;

public class NsList {

	private Hashtable<String, String> namespaces = new Hashtable<String, String>();
	
	private int nsNumber = 0;

	public void addAttrNs(String prefix, String namespaceURI, String elementNamespaceURI) {
		if (namespaceURI == null) {
			namespaceURI = elementNamespaceURI;
		}
		this.addNs(prefix, namespaceURI);
	}

	public void addNs(String prefix, String namespaceURI) {
		if ((namespaceURI != null) && (!this.namespaces.containsKey(namespaceURI))) {
			if (this.namespaces.containsValue(prefix)) {
				this.nsNumber++;
				String nsPrefix = "ns" + Integer.toString(this.nsNumber);
				this.namespaces.put(namespaceURI, nsPrefix);
			} else {
				this.namespaces.put(namespaceURI, prefix);
			}
		}
	}

	public Hashtable<String, String> getNamespaces() {
		return this.namespaces;
	}

	public String getPrefix(String namespaceURI) {
		if ((namespaceURI != null) && this.namespaces.containsKey(namespaceURI)) {
			return this.namespaces.get(namespaceURI);
		} else {
			return null;
		}
	}

}
