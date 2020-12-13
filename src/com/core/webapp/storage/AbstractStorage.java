package com.core.webapp.storage;

import com.core.webapp.exception.*;
import com.core.webapp.model.Resume;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {
   // private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract T getSearchKey(String uuid);

    protected abstract void doSave(Resume resume, T searchKey);

    protected abstract void doUpdate(Resume resume, T searchKey);

    protected abstract void doDelete(T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract boolean isExist(T searchKey);

    protected abstract List<Resume> doCopyAll();

    public void save(Resume resume) {
      //  LOG.info("Save " + resume);
        T searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public void update(Resume resume) {
      //  LOG.info("Update " + resume);
        T searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public void delete(String uuid) {
      //  LOG.info("Delete " + uuid);
        T searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
      //  LOG.info("Get " + uuid);
        T searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private T getExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private T getNotExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
        //    LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
     //   LOG.info("GetAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
