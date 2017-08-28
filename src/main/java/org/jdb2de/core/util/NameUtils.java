package org.jdb2de.core.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility methods to deal with class and field names.
 *
 * @author  Rodrigo Tavares
 */
public final class NameUtils {

    /**
     * Omitting the utils constructor
     */
    private NameUtils() {
    }

    /**
     *
     * @param value
     * @return
     */
    public static String underscoreToTypeCamelcase(String value) {
        return underscoreToCamelcase(value, CaseFormat.UPPER_CAMEL);
    }

    /**
     *
     * @param value
     * @return
     */
    public static String underscoreToFieldCamelcase(String value) {
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

    public static void main(String[] args) {
        underscoreToCamelcase(null, CaseFormat.LOWER_CAMEL);
    }
}
