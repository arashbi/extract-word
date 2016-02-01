import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Arash on 2016-02-01.
 * Copyrighted to Kapanak.
 */
public class Split {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = new File("clean.xml");

        Document document = builder.parse(new InputSource(new FileReader(file)));

        split(document);

        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new FileWriter("clean.xml"));
        transformer.transform(new DOMSource(document), result);
    }

    private static void split(Node node) {
        if(isHeaderNode(node)){

        }

    }

    private static boolean isHeaderNode(Node node) {
        return false;
    }
}
