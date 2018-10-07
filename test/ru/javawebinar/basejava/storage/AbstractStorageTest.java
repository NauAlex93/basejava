package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    Storage storage;
    private static final String UUID_1;
    private static final String UUID_2;
    private static final String UUID_3;
    private static final String UUID_4;

    private static final Resume RESUME1;
    private static final Resume RESUME2;
    private static final Resume RESUME3;
    private static final Resume RESUME4;

    static {
        UUID_1 = "uuid1";
        UUID_2 = "uuid2";
        UUID_3 = "uuid3";
        UUID_4 = "uuid4";

        RESUME1 = new Resume(UUID_1, "a");
        RESUME2 = new Resume(UUID_2, "b");
        RESUME3 = new Resume(UUID_3, "c");
        RESUME4 = new Resume(UUID_4, "d");

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        ArrayList<String> achList = new ArrayList<>();
        achList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. " +
                "XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ListSection achievement = new ListSection(achList);

        ArrayList<String> qualList = new ArrayList<>();
        qualList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2.");
        qualList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce.");
        ListSection qualifications = new ListSection(qualList);

        ArrayList<Career> expList = new ArrayList<>();
        expList.add(new Career(
                "Java Online Projects",
                "http://javaops.ru/",
                new Career.Position(
                        2013,
                        Month.OCTOBER,
                        LocalDate.now().getYear(),
                        LocalDate.now().getMonth(),
                        "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."
                )));
        expList.add(new Career(
                "Wrike",
                "https://www.wrike.com/",
                new Career.Position(
                        2014,
                        Month.OCTOBER,
                        2016,
                        Month.JANUARY,
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                                "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
                )));
        CareerSection experience = new CareerSection(expList);

        ArrayList<Career> educationList = new ArrayList<>();
        educationList.add(new Career(
                "Coursera",
                "https://www.coursera.org/course/progfun",
                new Career.Position(
                        2013,
                        Month.MARCH,
                        2013,
                        Month.MAY,
                        "Functional Programming Principles in Scala", "by Martin Odersky"
                )));
        educationList.add(new Career(
                "Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                new Career.Position(
                        2011,
                        Month.MARCH,
                        2011,
                        Month.APRIL,
                        "Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML",
                        "Курс"
                )));
        CareerSection education = new CareerSection(educationList);

        RESUME1.addContact(ContactType.PHONE, "+7(921) 855-0482");
        RESUME1.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME1.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        RESUME1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME1.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        RESUME1.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        RESUME1.addSection(SectionType.OBJECTIVE, objective);
        RESUME1.addSection(SectionType.PERSONAL, personal);
        RESUME1.addSection(SectionType.ACHIEVEMENT, achievement);
        RESUME1.addSection(SectionType.QUALIFICATIONS, qualifications);
        RESUME1.addSection(SectionType.EXPERIENCE, experience);
        RESUME1.addSection(SectionType.EDUCATION, education);
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();

        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getSize());
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME4);
        assertEquals(4, storage.getSize());
        assertEquals(RESUME4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistException() throws Exception {
        storage.save(RESUME1);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1, "f");
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistException() throws Exception {
        storage.update(RESUME4);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertEquals(2, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals(RESUME1, storage.get(UUID_1));
        assertEquals(RESUME2, storage.get(UUID_2));
        assertEquals(RESUME3, storage.get(UUID_3));
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> resumeList = storage.getAllSorted();
        assertEquals(3, storage.getSize());
        assertEquals(Arrays.asList(RESUME1, RESUME2, RESUME3), resumeList);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.getSize());
    }
}