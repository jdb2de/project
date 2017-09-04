package org.jdb2de.core.util;

import java.io.File;
import java.io.ObjectStreamClass;

/**
 *
 * @author Rodrigo Tavares
 */
public final class LanguageUtils {

    /**
     * Omitting the utils constructor
     */
    private LanguageUtils() {
    }

    /**
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(T...values) {
        return values;
    }

    /**
     *
     * @return
     */
    public static String generateSerialVersionUUID(String className, String typeName) {
        long serialUid = className.hashCode();
        serialUid += typeName.hashCode();
        return Long.toString(serialUid);
    }

    public static File fileFromResource(String fileName) {
        ClassLoader classLoader = LanguageUtils.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
