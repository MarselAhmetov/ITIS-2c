package facade;

import Model.Node;
import iterators.IteratorBFS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Scanner;

public final class FacadeXML {
    private static FacadeXML instance;
    private File file;

    public static FacadeXML getInstance() {
        if (instance != null)
            return instance;
        else
            return instance = new FacadeXML();
    }

    private FacadeXML() {
    }

    private Node bindNodes(Node node) {
        IteratorBFS iteratorBFS = new IteratorBFS(node);
        while (iteratorBFS.hasNext()) {
            Node currentNode = iteratorBFS.next();
            for (Node n : currentNode.getChildren()) {
                n.parent = currentNode;
            }
        }
        return node;
    }

    public Node readTree(File file) {
        try {
            String string = null;
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            if (scanner.hasNextLine()) {
                string = scanner.nextLine();
            }
            return XmlToObject(string);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void writeTree(Node node, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(jaxbObjectToXML(node));
            writer.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Node XmlToObject(String string) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Node.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Node) jaxbUnmarshaller.unmarshal(new StringReader(string));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static String jaxbObjectToXML(Node node) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(node, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

