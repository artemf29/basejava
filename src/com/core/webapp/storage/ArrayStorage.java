package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (Arrays.asList(storage).contains(resume)) {
            System.out.println("Резюме уже добавлено!");
            return;
        }
        if (size > storage.length) {
            System.out.println("Хранилище заполнено!");
            return;
        }
        if (resume == null) {
            System.out.println("Резюме отсутствует!");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                storage[i].setUuid("Update");
                return;
            }
        }
        System.out.println("Резюме не найдено!");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме не найдено!");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println("Резюме не найдено!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}

