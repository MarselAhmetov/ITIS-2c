package iterators;

import Model.Node;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class IteratorBFSPriority implements Iterator<Node> {
    private Deque<Node> deque;

    public IteratorBFSPriority(Node root) {
        deque = new ArrayDeque<Node>();
        deque.add(root);
    }

    public boolean hasNext() {
        return !deque.isEmpty();
    }

    public Node next() {
        Node node = deque.pop();
        node.getChildren().sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
        deque.addAll(node.getChildren());
        return node;
    }
}
