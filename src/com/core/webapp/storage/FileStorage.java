package com.core.webapp.storage;

import com.core.webapp.exception.StorageException;
import com.core.webapp.model.Resume;
import com.core.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File file;
    private final StreamSerializer streamSerializerStrategy;

    public FileStorage(File file, StreamSerializer streamSerializerStrategy) {
        Objects.requireNonNull(file, "file must not be null");
        this.streamSerializerStrategy = streamSerializerStrategy;
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
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            streamSerializerStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            streamSerializerStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException(resume.getUuid(), "File write error", e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = file.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File f : Objects.requireNonNull(file.listFiles())) {
            doDelete(f);
        }

    }

    @Override
    public int size() {
        return Objects.requireNonNull(file.list(), "Directory read error").length;
    }
}
