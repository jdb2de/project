package org.jdb2de.core.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
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
    private static final Logger LOG = LoggerFactory.getLogger(GeneratorUtils.class);

    /**
     * Regular expression to validate search string, allowing only letters, asterisks and numbers after first letter
     */
    private static final String SEARCH_WILDCARD_REGEX = "^([A-Za-z\\_\\*])+([0-9A-Za-z\\_\\*])*$";

    /**
     * Omitting the utils constructor
     */
    private GeneratorUtils() {
    }

    /**
     *
     * @param value
     * @return
     */
    public static String underscoreToUpperCamelcase(String value) {
        return underscoreToCamelcase(value, CaseFormat.UPPER_CAMEL);
    }

    /**
     *
     * @param value
     * @return
     */
    public static String underscoreToLowerCamelcase(String value) {
        return underscoreToCamelcase(value, CaseFormat.LOWER_CAMEL);
    }

    /**
     * Convert the {@link String} value to a camelcase format
     * @param value A {@link String} value in the underscore format, if <code>null</code> or <code>empty</code> returns
     *              itself
     * @param caseFormat Indicate if return a {@link String} with the first letter uppercase
     *                   {@link CaseFormat#UPPER_CAMEL} or lowercase {@link CaseFormat#LOWER_CAMEL}
     * @return A {@link String} in the camelcase format
     */
    private static String underscoreToCamelcase(String value, CaseFormat caseFormat) {
        Preconditions.checkNotNull(caseFormat, "[caseFormat] can not be null");
        Preconditions.checkArgument(CaseFormat.LOWER_CAMEL.equals(caseFormat) ||
                CaseFormat.UPPER_CAMEL.equals(caseFormat), "[caseFormat] must be LOWER_CAMEL or UPPER_CAMEL");

        if (StringUtils.isEmpty(value)) {
            return value;
        }

        return CaseFormat.LOWER_UNDERSCORE.to(caseFormat, value.toLowerCase());
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

    /**
     * Validate search string, allowing only letters, asterisks and numbers after first letter.
     * If empty or null returns <code>true</code>
     *
     * <code>
     * isValidSearchWildcard(null)      = true
     * isValidSearchWildcard("")        = true
     * isValidSearchWildcard(" ")       = false
     * isValidSearchWildcard("0XXX")    = false
     * isValidSearchWildcard("X00")     = true
     * isValidSearchWildcard("X_XX")    = true
     * isValidSearchWildcard("X_X123X") = true
     * isValidSearchWildcard("*")       = true
     * isValidSearchWildcard("X_*")     = true
     * isValidSearchWildcard("*_X_*")   = true
     * isValidSearchWildcard("*_0X_*")  = true
     * isValidSearchWildcard("*_0X_*")  = true
     * </code>
     *
     * @param searchString Wildcard search string
     * @return If is valid <code>true</code>, otherwise <code>false</code>
     */
    public static boolean isValidSearchWildcard(String searchString) {
        if (StringUtils.isEmpty(searchString)) {
            return true;
        }
        return searchString.matches(SEARCH_WILDCARD_REGEX);
    }

    /**
     * Replace search wildcard (<code>*</code>) to a regex instruction
     *
     * <code>
     * replaceSearchToRegex(null)  = null
     * replaceSearchToRegex("")    = ""
     * replaceSearchToRegex("*")   = "(.)*"
     * replaceSearchToRegex("X_*") = "X_(.)*"
     * replaceSearchToRegex("X_")  = "X_"
     * </code>
     *
     * @param searchString Wildcard search string
     * @return A regular expression instruction
     */
    public static String replaceSearchToRegex(String searchString) {
        if (StringUtils.isEmpty(searchString)) {
            return searchString;
        }

        return StringUtils.replace(searchString, "*", "(.)*");
    }

}
