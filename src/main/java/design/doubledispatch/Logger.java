package design.doubledispatch;

public abstract class Logger {

    abstract void accept(final RequestModel requestModel);

    protected abstract void log(final String msg);
}
