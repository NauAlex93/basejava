package ru.javawebinar.basejava.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            write(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            write(dos, r.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(sectionType.name());

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        write(dos, ((ListSection) section).getContent(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        write(dos, ((CareerSection) section).getWorkPlaces(), career -> {
                                    dos.writeUTF((career.getLink().getName()));
                                    dos.writeUTF((career.getLink().getUrl()));

                                    write(dos, career.getPositions(), position -> {
                                        dos.writeInt(position.getStartDate().getYear());
                                        dos.writeInt(position.getStartDate().getMonth().getValue());
                                        dos.writeInt(position.getEndDate().getYear());
                                        dos.writeInt(position.getEndDate().getMonth().getValue());
                                        dos.writeUTF(position.getTitle());
                                        dos.writeUTF(position.getDescription());
                                    });
                                }
                        );
                        break;
                }
            });
        }
    }

    private interface Consumer<T> {
        void accept(T t) throws IOException;
    }

    private <T> void write(DataOutputStream dos, Collection<T> collection, Consumer<T> consumer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            consumer.accept(item);
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

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int size = dis.readInt();
                        List<String> list = new ArrayList<>();
                        for (int j = 0; j < size; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int careerListSize = dis.readInt();
                        List<Career> careerList = new ArrayList<>();
                        for (int k = 0; k < careerListSize; k++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            int positionListSize = dis.readInt();
                            List<Career.Position> positionList = new ArrayList<>();
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
                        break;
                }
            }
            return resume;
        }
    }
}
