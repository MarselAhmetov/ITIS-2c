import Model.Tree;
import Model.Node;
import Model.Type;
import facade.FacadeXML;
import iterators.IteratorBFS;
import iterators.IteratorBFSPriority;
import iterators.IteratorDFS;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree("Russia");
        Node node = (Node) new Node.NodeBuilder()
                .setName("1")
                .setType(Type.SUBEKT)
                .setPriority(5)
                .setParent(tree.getRoot())
                .build();


        Node node1 = (Node) new Node.NodeBuilder()
                .setName("2")
                .setType(Type.SUBEKT)
                .setPriority(3)
                .setParent(tree.getRoot())
                .build();

        Node node2 = (Node) new Node.NodeBuilder()
                .setName("3")
                .setType(Type.SUBEKT)
                .setPriority(4)
                .setParent(tree.getRoot())
                .build();

        Node node3 = (Node) new Node.NodeBuilder()
                .setName("G1")
                .setType(Type.GOROD)
                .setPriority(10)
                .setParent(node1)
                .build();

        Node dom = (Node) new Node.NodeBuilder()
                .setName("dom")
                .setType(Type.DOM)
                .setPriority(1)
                .setParent(node3)
                .build();

        Node node4 = (Node) new Node.NodeBuilder()
                .setName("G2")
                .setType(Type.GOROD)
                .setPriority(5)
                .setParent(node1)
                .build();

        Node node5 = (Node) new Node.NodeBuilder()
                .setName("G3")
                .setType(Type.GOROD)
                .setPriority(3)
                .setParent(node1)
                .build();

        Node node6 = (Node) new Node.NodeBuilder()
                .setName("G4")
                .setType(Type.GOROD)
                .setPriority(11)
                .setParent(node1)
                .build();

        Node node7 = (Node) new Node.NodeBuilder()
                .setName("G6")
                .setType(Type.GOROD)
                .setPriority(5)
                .setParent(node2)
                .build();


        IteratorBFS iteratorBFS = new IteratorBFS(tree.getRoot());
        IteratorDFS iteratorDFS = new IteratorDFS(tree.getRoot());
        IteratorBFSPriority iteratorBFSPriority = new IteratorBFSPriority(tree.getRoot());

        while (iteratorBFS.hasNext()) System.out.println(iteratorBFS.next().toString());
        System.out.println("__________________________________");

        while (iteratorBFSPriority.hasNext()) System.out.println(iteratorBFSPriority.next().toString());
        Interpreter interpreter = new Interpreter(tree.getRoot());
        System.out.println(((ArrayList<Node>) interpreter.dispatch("return-children Russia,G1")));;
        while (iteratorDFS.hasNext()) System.out.println(iteratorDFS.next().toString());
        System.out.println("__________________________________");



    }


}
