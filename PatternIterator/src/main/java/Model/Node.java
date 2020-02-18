package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.util.Builder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"parent"})
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Node {
    @XmlElement(name = "type")
    private Type type;
    @XmlElement(name = "name")
    private String name;
    @XmlTransient
    public Node parent;
    @XmlElement(name = "children")
    private List<Node> children = new ArrayList<>();
    @XmlElement(name = "priority")
    private Integer priority;

    public Node() {
    }

    public Node(String name, Type type, Node parent, Integer priority) {
        this.type = type;
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<Node>();
        this.priority = priority;
        if (parent != null)
        parent.addChild(this);
    }

    public void addChild(Node node) {
        children.add(node);
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Model.Node{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }

    public static class NodeBuilder implements Builder {
        private Node node;

        public NodeBuilder() {
            this.node = new Node();
        }

        public NodeBuilder setType(Type type) {
            node.setType(type);
            return this;
        }

        public NodeBuilder setName(String name) {
            node.setName(name);
            return this;
        }

        public NodeBuilder setParent(Node parent) {
            node.setParent(parent);
            return this;
        }

        public NodeBuilder setChildren(List<Node> children) {
            node.setChildren(children);
            return this;
        }

        public NodeBuilder setPriority(Integer priority) {
            node.setPriority(priority);
            return this;
        }

        @Override
        public Object build() {
            return node;
        }
    }
}
