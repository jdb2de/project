package org.jdb2de.core.exception;

import org.jdb2de.core.util.GeneratorUtils;

public class GeneratorException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3450542778826496736L;

    public GeneratorException(String message, Object...params) {
        super(GeneratorUtils.messageFormat(message, params));
    }

    public GeneratorException(String message, Throwable cause, Object...params) {
        super(GeneratorUtils.messageFormat(message, params), cause);
    }
}
