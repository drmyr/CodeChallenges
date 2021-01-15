package models;

import java.util.List;

public class Node {
    final int id;
    final List<Node> neighbors;
    public Node(final int id, final List<Node> neighbors) {
        this.id = id;
        this.neighbors = neighbors;
    }

    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }
}
