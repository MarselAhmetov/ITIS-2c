package Model;

public class Tree {
    private Node root;

    public Tree(String name) {
        root = new Node(name, Type.STRANA, null, 1 );
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}