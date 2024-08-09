package org.sky.flow.designer.parse.exception;

/**
 * Node Parse Exception
 *
 * @author yj
 */
public class NodeParseException extends RuntimeException {
    /**
     * generate
     *
     * @param message exception error message
     */
    public NodeParseException(String message) {
        super(message);
    }
}
