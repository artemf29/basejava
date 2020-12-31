package com.core.webapp.model;

public enum ContactType {
    NUMBER("Мобильный"),
    MAIL("Электронная почта") {
        @Override
        public String toHtml0(String value) {
            return getContact() + ": " + toLink("mailto:" + value, value);
        }
    },
    GITHUB("Профиль GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Профиль Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    SOCIAL("Соц.сети") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String contact;

    ContactType(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public String toHtml0(String value) {
        return contact + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, contact);
    }

    public static String toLink(String href, String contact) {
        return "<a href='" + href + "'>" + contact + "</a>";
    }
}
