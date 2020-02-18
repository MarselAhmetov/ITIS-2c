import Model.Node;
import Model.Type;
import facade.FacadeJson;
import facade.FacadeXML;
import iterators.IteratorBFS;

import java.util.ArrayList;

public class Interpreter {
    private Node root;
    private FacadeJson facadeJson;

    public Interpreter(Node root) {
        this.root = root;
        facadeJson = FacadeJson.getInstance();
    }


    //add address,address,address name type priority
    //return-children address,address,address
    //remove address,address,address
    //save

    public Object dispatch(String string) {
        String[] strings = string.split(" ");
        switch (strings[0]) {
            case "add":
                addChild((Node) new Node.NodeBuilder().setName(strings[2]).setType(getTypeFromString(strings[3])).setPriority(Integer.valueOf(strings[4])).build(), strings[1]);
                break;
            case "return-children":
                return returnChildren(strings[1]);
            case "remove":
                remove(strings[1]);
                break;
            case "save":
                facadeJson.writeTree(root);
                break;
        }
        return null;
    }

    private Type getTypeFromString(String string) {
        switch (string) {
            case "STRANA":
                return Type.STRANA;
            case "SUBEKT":
                return Type.SUBEKT;
            case "RAION":
                return Type.RAION;
            case "GOROD":
                return Type.GOROD;
            case "ULITSA":
                return Type.ULITSA;
            case "DOM":
                return Type.DOM;
            default:
                return null;
        }
    }

    private ArrayList<Node> returnChildren(String address) {
        Node currentNode;
        if ((currentNode = findNode(address)) != null) {
            return (ArrayList<Node>) currentNode.getChildren();
        } else {
            return null;
        }
    }

    private void remove(String address) {
        Node found = findNode(address);
        if (found != null) {
            found.getParent().getChildren().remove(found);
        }
    }
    private void addChild(Node node, String address) {
        Node found = findNode(address);
        if (found != null) {
            node.setParent(found);
        }
    }

    private Node findNode(String address) {
        String[] strings = address.split(",");
        IteratorBFS iteratorBFS = new IteratorBFS(root);
        while (iteratorBFS.hasNext()) {
            Node currentNode = iteratorBFS.next();
            if (strings[strings.length - 1].equals(currentNode.getName()) && checkAddress(strings, currentNode)) {
                System.out.println("Result: " + currentNode.getName());
                return currentNode;
            }
        }
        return null;
    }

    private boolean checkAddress(String[] address, Node node) {
        if (address.length > 1) {
            for (int i = address.length - 2; i >= 0; i--) {
                if (node.getParent() != null) {
                    if (node.getParent().getName().equals(address[i])) {
                        node = node.getParent();
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
