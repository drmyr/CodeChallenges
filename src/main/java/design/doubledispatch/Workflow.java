package design.doubledispatch;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class Workflow {

    private final Map<String, Class<? extends RequestModel>> requestModelIdentifierMap;
    private final List<Logger> loggers;

    public Workflow(final Map<String, Class<? extends RequestModel>> requestModelIdentifierMap, final List<Logger> loggers) {
        this.requestModelIdentifierMap = requestModelIdentifierMap;
        this.loggers = loggers;
    }

    public Object performTask(final InboundMessage inboundMessage) {
        try {
            final RequestModel requestModel = requestModelIdentifierMap.getOrDefault(inboundMessage.getId(), DefaultRequestModel.class)
                    .getConstructor(InboundMessage.class).newInstance(inboundMessage);
            for(final Logger logger : loggers) {
                logger.accept(requestModel);
            }
        } catch (final InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null; // return whatever you should return
    }
}
