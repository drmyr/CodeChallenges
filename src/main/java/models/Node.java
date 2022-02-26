package models;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public record Node(int id, List<Node> neighbors) {

    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return unmodifiableList(neighbors);
    }
}
