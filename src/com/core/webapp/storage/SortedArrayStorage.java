package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            index = (-1 * index) - 1;
            int i = size + 1;
            while (i > index) {
                storage[i] = storage[i - 1];
                i--;
            }
            storage[index] = resume;
            size++;
        }
    }

    /* Я бы вынес и эти два метода в абстракт, но с увеличением сложности возможно это лишнее */
    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            int i = index ;
            while (i < (size - 1)) {
                storage[i] = storage[i + 1];
                i++;
            }
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }
}
