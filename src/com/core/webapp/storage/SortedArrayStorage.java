package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

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
        Resume key = new Resume(uuid,"Anon");
        return Arrays.binarySearch(storage, 0, size, key,RESUME_COMPARATOR);
    }
}
