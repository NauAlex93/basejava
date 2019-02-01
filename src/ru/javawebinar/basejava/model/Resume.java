package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Resume EMPTY_RESUME = new Resume();

    static {
        EMPTY_RESUME.addSection(SectionType.OBJECTIVE, TextSection.EMPTY_TEXT_SECTION);
        EMPTY_RESUME.addSection(SectionType.PERSONAL, TextSection.EMPTY_TEXT_SECTION);
        EMPTY_RESUME.addSection(SectionType.ACHIEVEMENT, ListSection.EMPTY_LIST_SECTION);
        EMPTY_RESUME.addSection(SectionType.QUALIFICATIONS, ListSection.EMPTY_LIST_SECTION);
        EMPTY_RESUME.addSection(SectionType.EXPERIENCE, new CareerSection(Career.EMPTY_CAREER_SECTION));
        EMPTY_RESUME.addSection(SectionType.EDUCATION, new CareerSection(Career.EMPTY_CAREER_SECTION));
    }

    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void addContact(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }

    public void addSection(SectionType sectionType, AbstractSection value) {
        sections.put(sectionType, value);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }
}
