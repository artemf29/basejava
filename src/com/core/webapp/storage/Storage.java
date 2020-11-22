package com.core.webapp.storage;

import com.core.webapp.model.Resume;

import java.util.List;


/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);
    /**
     * @return array, contains only Resumes in storage (without null)
     */
     List<Resume> getAllSorted();

     int size();
}

