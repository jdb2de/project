package org.jdb2de.core.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

/**
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
     * @param underscore
     * @return
     */
    public static String underscoreToTypeCamelcase(String underscore) {
        return underscoreToCamelcase(underscore, CaseFormat.UPPER_CAMEL);
    }

    /**
     *
     * @param underscore
     * @return
     */
    public static String underscoreToFieldCamelcase(String underscore) {
        return underscoreToCamelcase(underscore, CaseFormat.LOWER_CAMEL);
    }

    /**
     *
     * @param underscore
     * @param caseFormat
     * @return
     */
    private static String underscoreToCamelcase(String underscore, CaseFormat caseFormat) {
        if (StringUtils.isEmpty(underscore)) {
            return underscore;
        }
        return CaseFormat.LOWER_UNDERSCORE.to(caseFormat, underscore.toLowerCase());
    }

}
