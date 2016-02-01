import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by Arash on 2016-01-31.
 * Copyrighted to Kapanak.
 */
public class RemoveEmpty {
    public static void main(String[] args) throws Exception {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = new File("content.xml");

        Document document = builder.parse(new InputSource(new FileReader(file)));

        removeNodes(document);

        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new FileWriter("clean.xml"));
        transformer.transform(new DOMSource(document), result);
    }

    public static void removeNodes(Node node) {
        NodeList list = node.getChildNodes();
        for (int i = list.getLength() - 1; i > -1  ; i--) {
            removeNodes(list.item(i));
        }
        boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE
                && node.getChildNodes().getLength() == 0;
        boolean emptyText = node.getNodeType() == Node.TEXT_NODE
                && node.getNodeValue().trim().isEmpty();
        if (emptyElement || emptyText) {
            node.getParentNode().removeChild(node);
        }
    }
}
