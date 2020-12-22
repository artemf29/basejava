package com.core.webapp.model;

import java.util.UUID;

public class ResumeTestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume RESUME_1 = new Resume(UUID_1, "N1");
    public static final Resume RESUME_2 = new Resume(UUID_2, "N2");
    public static final Resume RESUME_3 = new Resume(UUID_3, "N3");
    public static final Resume RESUME_4 = new Resume(UUID_4, "N4");

    static {
        RESUME_1.addContact(ContactType.MAIL, "artem@mail.ru");
        RESUME_1.addContact(ContactType.MOBILE, "12345678");
        RESUME_4.addContact(ContactType.GITHUB, "artemf29");
        RESUME_4.addContact(ContactType.MOBILE, "123");
        /*
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Information(2005, Month.JANUARY, "Information1", "content1"),
                                new Organization.Information(2001, Month.MARCH, 2005, Month.JANUARY, "Information2", "content2"))));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", "",
                                new Organization.Information(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", ""),
                                new Organization.Information(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
         */
        RESUME_2.addContact(ContactType.SOCIAL, "skype2");
        RESUME_2.addContact(ContactType.NUMBER, "22222");
        //  RESUME_3.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
    }

}
