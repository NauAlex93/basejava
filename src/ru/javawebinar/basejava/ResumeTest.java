package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class ResumeTest {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        ArrayList<String> achList = new ArrayList<>();
        achList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. " +
                "XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий: " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов: " +
                "SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achList.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        ListSection achievement = new ListSection(achList);

        ArrayList<String> qualList = new ArrayList<>();
        qualList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2.");
        qualList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce.");
        qualList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB.");
        qualList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts.");
        qualList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, " +
                "JUnit, Selenium (htmlelements).");
        qualList.add("Python: Django.");
        qualList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, " +
                "JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование Hudson/Jenkins, Ant + custom task," +
                " SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов," +
                " UML, функционального программирования.");
        qualList.add("Родной русский, английский \"upper intermediate\"");
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
        expList.add(new Career(
                "RIT Center",
                "",
                new Career.Position(
                    2012,
                    Month.APRIL,
                    2014,
                    Month.OCTOBER,
                    "Java архитектор",
                    "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, " +
                    "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                    "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, " +
                    "1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN " +
                    "для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                    "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
                    "Unix shell remote scripting via ssh tunnels, PL/Python"
        )));
        expList.add(new Career(
                "Luxoft (Deutsche Bank)",
                "http://www.luxoft.ru/",
                new Career.Position(
                    2012,
                    Month.OCTOBER,
                    2012,
                    Month.APRIL,
                    "Ведущий программист",
                    "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). " +
                    "Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа " +
                    "результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
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
        educationList.add(new Career(
                "Siemens AG",
                "http://www.siemens.ru/",
                new Career.Position(
                    2005,
                    Month.JANUARY,
                    2005,
                    Month.APRIL,
                    "3 месяца обучения мобильным IN сетям",
                    "Берлин"
        )));
        CareerSection education = new CareerSection(educationList);

        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.EDUCATION, education);

        System.out.println(resume.getFullName());

        for (ContactType contact : ContactType.values()) {
            System.out.println(resume.getContact(contact));
        }

        for (SectionType section : SectionType.values()) {
            System.out.println(resume.getSection(section).toString());
        }

    }
}
