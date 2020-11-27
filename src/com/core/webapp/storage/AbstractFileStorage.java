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

    protected abstract void doWrite(Resume resume, File searchKey) throws IOException;

    protected abstract void doRead(File searchKey) throws IOException;

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

    @Override
    protected void doUpdate(Resume resume, File searchKey) {
        try {
            doWrite(resume, searchKey);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File searchKey) {
        searchKey.deleteOnExit();
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            doRead(searchKey);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
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
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public int size() {
        return (int) file.length();
    }
}
