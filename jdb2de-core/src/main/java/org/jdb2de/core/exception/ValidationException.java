package org.jdb2de.core.exception;

import org.jdb2de.core.util.GeneratorUtils;

/**
 * The class {@code ValidationException} and its subclasses are a form of
 * {@code Exception} that indicates conditions that a reasonable
 * application might want to catch.
 *
 * @author Rodrigo Tavares
 */
public class ValidationException extends Exception {

    private static final long serialVersionUID = -7325712290621664246L;

    /**
     *
     * @param message
     * @param params
     */
    public ValidationException(String message, Object...params) {
        super(GeneratorUtils.messageFormat(message, params));
    }
}
