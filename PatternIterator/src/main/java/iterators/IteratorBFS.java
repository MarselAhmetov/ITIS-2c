package iterators;

import Model.Node;
import iterators.Iterator;

import java.util.*;

public class IteratorBFS implements Iterator<Node> {
    Deque<Node> deque;

    public IteratorBFS(Node root) {
        deque = new ArrayDeque<Node>();
        deque.add(root);
    }

    public boolean hasNext() {
        return !deque.isEmpty();
    }

    public Node next() {
        Node node = deque.pop();
        deque.addAll(node.getChildren());
        return node;
    }
}
