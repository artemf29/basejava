package com.core.webapp.storage.serializer;

import com.core.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dataOutputStream, contacts.entrySet(), entry -> {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            });
            writeCollection(dataOutputStream, resume.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dataOutputStream.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dataOutputStream.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(
                            dataOutputStream, ((ListSection) section).getList(), dataOutputStream::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeCollection(
                            dataOutputStream, ((OrganizationSection) section).getOrganizations(), organization -> {
                                dataOutputStream.writeUTF(organization.getName().getName());
                                dataOutputStream.writeUTF(organization.getName().getUrl());
                                writeCollection(dataOutputStream, organization.getInformation(), information -> {
                                    writeLocalDate(dataOutputStream, information.getStart());
                                    writeLocalDate(dataOutputStream, information.getEnd());
                                    dataOutputStream.writeUTF(information.getPosition());
                                    dataOutputStream.writeUTF(information.getInfo());
                                });
                            });
                }
            });
        }
    }

    private void writeLocalDate(DataOutputStream dataOutputStream, LocalDate localDate) throws IOException {
        dataOutputStream.writeInt(localDate.getYear());
        dataOutputStream.writeInt(localDate.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dataInputStream) throws IOException {
        return LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), 1);
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dataInputStream, () ->
                    resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF()));
            readItems(dataInputStream, () -> {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                resume.addSection(sectionType, readSection(dataInputStream, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dataInputStream, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                return new TextSection(dataInputStream.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                return new ListSection(readList(dataInputStream, dataInputStream::readUTF));
            }
            case EXPERIENCE, EDUCATION -> {
                return new OrganizationSection(readList(dataInputStream, () -> new Organization(
                        new Link(dataInputStream.readUTF(), dataInputStream.readUTF()),
                        readList(dataInputStream, () -> new Organization.Information(
                                readLocalDate(dataInputStream), readLocalDate(dataInputStream),
                                dataInputStream.readUTF(), dataInputStream.readUTF()
                        )))));
            }
            default -> throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream dataInputStream, ElementReader<T> reader) throws IOException {
        int size = dataInputStream.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void readItems(DataInputStream dataInputStream, ElementProcessor elementProcessor) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            elementProcessor.process();
        }
    }

    private <T> void writeCollection(
            DataOutputStream dataOutputStream, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }
}
