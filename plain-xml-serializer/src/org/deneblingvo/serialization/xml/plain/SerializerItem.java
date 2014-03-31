package org.deneblingvo.serialization.xml.plain;
import org.w3c.dom.*;
import java.io.*;

public interface SerializerItem {

	public boolean canSerialize (Node node);

	public void serialize (Writer writer, NsList list, Node node, int deep);
}
