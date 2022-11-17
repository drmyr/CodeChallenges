package design.doubledispatch;

public class SpecificRequestModel extends DefaultRequestModel {

    public SpecificRequestModel(final InboundMessage inboundMessage) {
        super(inboundMessage);
    }

    @Override
    public void accept(final LoggerOne loggerOne) {
        // SpecificRequestModel needs a specific override for LoggerOne, but can use all other defaults
    }
}
