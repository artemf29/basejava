package com.core.webapp.model;

public enum SectionType {
    OBJECTIVE("Должность"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toHtml0(Section value) {
        return title + ": " + value.toString();
    }

    public String toHtml(Section value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
