package me.alextur.rgraph.nodemap;

/**
 * @author Alex Turchynovich
 */
public class NodeMapException extends Exception {

    public NodeMapException() {
    }

    public NodeMapException(String message) {
        super(message);
    }

    public NodeMapException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeMapException(Throwable cause) {
        super(cause);
    }

    public NodeMapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
