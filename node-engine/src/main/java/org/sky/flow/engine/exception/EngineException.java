package org.sky.flow.engine.exception;

/**
 * process engine exception
 *
 * @author yj
 */
public class EngineException extends RuntimeException {
    /**
     * Message
     *
     * @param message error
     */
    public EngineException(String message) {
        super(message);
    }
}
