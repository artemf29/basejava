package com.core.webapp.model;

import java.time.Month;
import java.util.Arrays;
import java.util.UUID;

public class ResumeTestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume RESUME_1 = new Resume(UUID_1, "Artem");
    public static final Resume RESUME_2 = new Resume(UUID_2, "Anna");
    public static final Resume RESUME_3 = new Resume(UUID_3, "Stas");
    public static final Resume RESUME_4 = new Resume(UUID_4, "Thing");

    static {
        RESUME_1.addContact(ContactType.NUMBER, "12345678910");
        RESUME_1.addContact(ContactType.MAIL, "1@gmail.com");
        RESUME_1.addContact(ContactType.GITHUB, "123rtk1");
        RESUME_1.addContact(ContactType.LINKEDIN, "ArtemFilimonov");
        RESUME_1.addContact(ContactType.STACKOVERFLOW, "artemf29");
        RESUME_1.addContact(ContactType.SOCIAL, "vk - @artemf29");

        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("Достижение1", "Достижение2")));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Упорство, Интеллект"));
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Java Developer"));

        RESUME_2.addContact(ContactType.NUMBER, "10987654321");
        RESUME_2.addContact(ContactType.MAIL, "2@gmail.com");
        RESUME_2.addContact(ContactType.GITHUB, "12345rtk1");
        RESUME_2.addContact(ContactType.LINKEDIN, "Anna");
        RESUME_2.addContact(ContactType.STACKOVERFLOW, "123art");
        RESUME_2.addContact(ContactType.SOCIAL, "vk - @anna");

        RESUME_2.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("Достижение1", "Достижение2")));
        RESUME_2.addSection(SectionType.OBJECTIVE, new TextSection("Kotlin Developer"));
        RESUME_2.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("developer","хореограф")));

        RESUME_3.addContact(ContactType.NUMBER, "799999999");
        RESUME_3.addContact(ContactType.MAIL, "3@gmail.com");
        RESUME_3.addContact(ContactType.GITHUB, "qwerty");
        RESUME_3.addContact(ContactType.LINKEDIN, "qwerety");
        RESUME_3.addContact(ContactType.STACKOVERFLOW, "qwertyyuii");
        RESUME_3.addContact(ContactType.SOCIAL, "vk - @qwer");

        RESUME_3.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("Достижение1", "Достижение2")));
        RESUME_3.addSection(SectionType.PERSONAL, new TextSection("Коммуникабельность, что то"));
        RESUME_3.addSection(SectionType.OBJECTIVE, new TextSection("Java Developer"));

        RESUME_4.addContact(ContactType.NUMBER, "000000000000");
        RESUME_4.addContact(ContactType.MAIL, "0@gmail.com");
        RESUME_4.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("Достижение0", "Достижение1")));
    }

}
