package com.core.webapp.storage;

import com.core.webapp.model.Resume;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int searchKey) {
        storage[size] = resume;
    }

    @Override
    protected void fillDeletedElement(int searchKey) {
        storage[searchKey] = storage[size - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

