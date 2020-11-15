package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int searchKey) {
        int flag = -searchKey - 1;
        System.arraycopy(storage, flag, storage, flag + 1, size - flag);
        storage[flag] = resume;
    }

    @Override
    protected void fillDeletedElement(int searchKey) {
        int flag = size - searchKey - 1;
        if (flag > 0) {
            System.arraycopy(storage, searchKey + 1, storage, searchKey, flag);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume key = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }
}
