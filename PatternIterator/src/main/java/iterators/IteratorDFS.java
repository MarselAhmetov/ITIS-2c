package iterators;

import Model.Node;
import iterators.Iterator;

import java.util.Stack;

public class IteratorDFS implements Iterator<Node> {
    Stack<Node> stack;

    public IteratorDFS(Node root) {
        stack = new Stack<Node>();
        stack.add(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Node next() {
        Node node = stack.pop();
        stack.addAll(node.getChildren());
        return node;
    }
}
