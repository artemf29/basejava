package com.core.webapp.storage;

import com.core.webapp.exception.StorageException;
import com.core.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File file;

    public AbstractFileStorage(File file) {
        Objects.requireNonNull(file, "file must not be null");
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file.getAbsolutePath() + " is not directory");
        }
        if (!file.canRead() || !file.canWrite()) {
            throw new IllegalArgumentException(file.getAbsolutePath() + " is not readable/writable");
        }
        this.file = file;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(file, uuid);
    }

    @Override
    protected void doSave(Resume resume, File searchKey) {
        try {
            file.createNewFile();
            doWrite(resume, searchKey);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, File searchKey) throws IOException;

    @Override
    protected void doUpdate(Resume resume, File searchKey) {

    }

    @Override
    protected void doDelete(File searchKey) {

    }

    @Override
    protected Resume doGet(File searchKey) {
        return null;
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
