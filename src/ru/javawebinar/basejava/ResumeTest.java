package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Map;

public class ResumeTest {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STATCKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        resume.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Position"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("Education project", "Development of Rich Internet Application")));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("Java", "DB", "C/C++", "Python")));
        resume.addSection(SectionType.EXPERIENCE, new CareerSection(
                Arrays.asList(
                        new Career("Java Online Projects", YearMonth.of(2013, 10), YearMonth.now(), "Project author", "Description"),
                        new Career("Wrike", YearMonth.of(2014, 10), YearMonth.of(2016, 01), "Senior Developer", "Description")
                )));
        resume.addSection(SectionType.EDUCATION, new CareerSection(
                Arrays.asList(
                        new Career("Coursera", YearMonth.of(2013, 03), YearMonth.of(2013, 05), "Functional Programming Principles in Scala", "Description"),
                        new Career("Luxoft", YearMonth.of(2011, 03), YearMonth.of(2011, 04), "UML", "Description")
                )));

        System.out.println(resume.getFullName());

        for (Map.Entry<ContactType, String> contact : resume.getContacts().entrySet())
        {
            System.out.println(contact.getKey().getTitle() + ": " + contact.getValue());
        }

        System.out.println();

        for (Map.Entry<SectionType, Section> section : resume.getSections().entrySet()) {
            System.out.println();
            System.out.println(section.getKey().getTitle());
            System.out.println(section.getValue().toString());
        }

    }
}
