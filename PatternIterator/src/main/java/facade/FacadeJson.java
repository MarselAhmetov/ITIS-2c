package facade;

import Model.Node;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iterators.IteratorBFS;

import java.io.*;
import java.util.Scanner;

public final class FacadeJson {
    private static FacadeJson instance;
    private ObjectMapper mapper = new ObjectMapper();
    private File file;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public static FacadeJson getInstance() {
        if (instance != null)
            return instance;
        else
            return instance = new FacadeJson();
    }

    private FacadeJson() {
        file = new File("file.txt");
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public void writeTree(Node node) {
        try {
            fileWriter.write(mapper.writeValueAsString(node));
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public Node readTree(File file) {
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.next());
        }
        try {
            return bindNodes(mapper.readValue(stringBuilder.toString(), Node.class));
        } catch (JsonProcessingException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
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
}

