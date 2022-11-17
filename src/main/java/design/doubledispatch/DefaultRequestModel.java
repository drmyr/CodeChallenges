package design.doubledispatch;

public class DefaultRequestModel extends RequestModel {

    public DefaultRequestModel(final InboundMessage inboundMessage) {
        super(inboundMessage);
    }

    @Override
    public void accept(final LoggerOne loggerOne) {
        // how should LoggerOne log a DefaultRequestModel
    }

    @Override
    public void accept(final LoggerTwo loggerTwo) {
        // how should LoggerTwo log a DefaultRequestModel
    }
}
