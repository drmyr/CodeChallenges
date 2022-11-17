package design.doubledispatch;

public abstract class RequestModel implements Loggable {
    protected final InboundMessage inboundMessage;

    public RequestModel(final InboundMessage inboundMessage) {
        this.inboundMessage = inboundMessage;
    }
}
