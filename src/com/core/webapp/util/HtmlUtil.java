package com.core.webapp.util;

import com.core.webapp.model.Organization;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Organization.Information information) {
        return DateUtil.format(information.getStart()) + " - " + DateUtil.format(information.getEnd());
    }
}
