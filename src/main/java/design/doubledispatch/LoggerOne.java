package design.doubledispatch;

/**
 * A specific {@link Logger}
 */
public class LoggerOne extends Logger {

    @Override
    void accept(final RequestModel requestModel) {
        requestModel.accept(this);
    }

    @Override
    protected void log(final String msg) {
        // log however LoggerOne needs to log
    }
}
