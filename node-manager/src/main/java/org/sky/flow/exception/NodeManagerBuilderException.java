package org.sky.flow.exception;

/**
 * Node Manager Build Exception
 *
 * @author yj
 */
public class NodeManagerBuilderException extends RuntimeException {
    /**
     * Message
     *
     * @param message error message
     */
    public NodeManagerBuilderException(String message) {
        super(message);
    }
}
