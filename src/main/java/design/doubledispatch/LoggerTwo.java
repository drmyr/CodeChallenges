package design.doubledispatch;

public class LoggerTwo extends Logger {
    @Override
    void accept(final RequestModel requestModel) {
        requestModel.accept(this);
    }

    @Override
    protected void log(final String msg) {
        // log however LoggerTwo needs to log
    }
}
