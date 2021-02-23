package models;

public class CriticalEdge<T> {
    private final T criticalNodeId;
    private final T criticalNodeDestinationId;

    public CriticalEdge(final T criticalNodeId, final T criticalNodeDestinationId) {
        this.criticalNodeId = criticalNodeId;
        this.criticalNodeDestinationId = criticalNodeDestinationId;
    }

    public T getCriticalNodeId() {
        return criticalNodeId;
    }

    public T getCriticalNodeDestinationId() {
        return criticalNodeDestinationId;
    }
}
