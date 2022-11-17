package design.doubledispatch;

/**
 * A {@link Loggable} should know how to interface with all known {@link Logger}s
 */
public interface Loggable {

    void accept(final LoggerOne loggerOne);

    void accept(final LoggerTwo loggerTwo);
}
