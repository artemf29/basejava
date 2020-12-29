package com.core.webapp.model;

public enum ContactType {
    NUMBER("Мобильный"),
    MAIL("Электронная почта") {
        @Override
        public String toHtml(String value) {
            return getContact() + ":<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    GITHUB("Профиль GitHub") {
        @Override
        public String toHtml(String value) {
            return getContact() + ": <a href='github:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    SOCIAL("Соц.сети");

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

}
