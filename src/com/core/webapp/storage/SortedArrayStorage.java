package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        int flag = -index - 1;
        System.arraycopy(storage, flag, storage, flag + 1, size - flag);
        storage[flag] = resume;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int flag = size - index - 1;
        if (flag > 0) {
            System.arraycopy(storage, index + 1, storage, index, flag);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }
}
