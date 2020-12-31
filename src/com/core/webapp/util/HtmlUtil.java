package com.core.webapp.util;

import com.core.webapp.model.Organization;

public class HtmlUtil {
    public static String formatDates(Organization.Information information) {
        return DateUtil.format(information.getStart()) + " - " + DateUtil.format(information.getEnd());
    }
}
