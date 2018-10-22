package ru.javawebinar.basejava.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            //TODO implements section
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(sectionType.name());
                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    dos.writeUTF(((TextSection) section).getText());
                }
                if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
                    List<String> content = ((ListSection) section).getContent();
                    int contentSize = content.size();
                    dos.writeInt(contentSize);
                    for (int i = 0; i < contentSize; i++) {
                        dos.writeUTF(content.get(i));
                    }
                }
                if (sectionType == SectionType.EXPERIENCE || sectionType == SectionType.EDUCATION) {
                    List<Career> careerList = ((CareerSection) section).getWorkPlaces();
                    int careerListSize = careerList.size();
                    dos.writeInt(careerListSize);
                    for (int i = 0; i < careerList.size(); i++) {
                        dos.writeUTF((careerList.get(i).getLink().getName()));
                        dos.writeUTF((careerList.get(i).getLink().getUrl()));

                        List<Career.Position> positionList = careerList.get(i).getPositions();
                        int positionListSize = positionList.size();
                        dos.writeInt(positionListSize);
                        for (int j = 0; j < positionListSize; j++) {
                            dos.writeInt(positionList.get(j).getStartDate().getYear());
                            dos.writeInt(positionList.get(j).getStartDate().getMonth().getValue());
                            dos.writeInt(positionList.get(j).getEndDate().getYear());
                            dos.writeInt(positionList.get(j).getEndDate().getMonth().getValue());
                            dos.writeUTF(positionList.get(j).getTitle());
                            dos.writeUTF(positionList.get(j).getDescription());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactsSize = dis.readInt();

            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            //TODO implements section
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());

                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    resume.addSection(sectionType, new TextSection(dis.readUTF()));
                }

                if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
                    int size = dis.readInt();
                    List list = new ArrayList();
                    for (int j = 0; j < size; j++) {
                        list.add(dis.readUTF());
                    }
                    resume.addSection(sectionType, new ListSection(list));
                }

                if (sectionType == SectionType.EXPERIENCE || sectionType == SectionType.EDUCATION) {
                    int size = dis.readInt();
                    List careerList = new ArrayList();
                    for (int k = 0; k < size; k++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        int positionListSize = dis.readInt();
                        List positionList = new ArrayList();
                        for (int l = 0; l < positionListSize; l++) {
                            LocalDate startDate = LocalDate.of(dis.readInt(), dis.readInt(), 1);
                            LocalDate endDate = LocalDate.of(dis.readInt(), dis.readInt(), 1);
                            String title = dis.readUTF();
                            String descripton = dis.readUTF();
                            positionList.add(new Career.Position(startDate, endDate, title, descripton));
                        }

                        careerList.add(new Career(name, url, positionList));
                    }
                    resume.addSection(sectionType, new CareerSection(careerList));
                }
            }
            return resume;
        }
    }
}
