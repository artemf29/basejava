package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}

