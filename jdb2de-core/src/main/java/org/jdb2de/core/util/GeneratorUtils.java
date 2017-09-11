package org.jdb2de.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rodrigo Tavares
 */
public final class GeneratorUtils {

    /**
     * Instance to log registration
     */
    private static Logger LOG = LoggerFactory.getLogger(GeneratorUtils.class);

    /**
     * Omitting the utils constructor
     */
    private GeneratorUtils() {
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
        ClassLoader classLoader = GeneratorUtils.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    /**
     * @param pattern
     * @param params
     * @return
     * @see java.text.MessageFormat#format(String, Object...)
     */
    public static String messageFormat(String pattern, Object... params) {

        String textPattern = StringUtils.defaultString(pattern);
        if (textPattern.trim().isEmpty()) {
            return textPattern;
        }

        int idx = -1;

        Pattern regexPattern = Pattern.compile("\\{([0-9])+\\}");
        Matcher matcher = regexPattern.matcher(textPattern);
        while (matcher.find()) {
            String str = matcher.group().replace("{", "").replace("}", "");
            try {
                int tmpIdx = Integer.parseInt(str);
                if (tmpIdx > idx) {
                    idx = tmpIdx;
                }
            } catch (Exception e) {
                LOG.error("Error to identify index to format [{}]", e, textPattern);
            }
        }

        idx++;

        while (textPattern.contains("{}")) {
            textPattern = StringUtils.replaceOnce(textPattern, "{}", "{" + idx + "}");
            idx++;
        }

        return MessageFormat.format(textPattern, params);
    }
}
