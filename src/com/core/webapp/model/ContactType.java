package com.core.webapp.model;

public enum ContactType {
    NUMBER("Номер телефона"),
    MOBILE("Мобильный"),
    MAIL("Электронная почта"),
    GITHUB("Профиль GitHub"),
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
}
