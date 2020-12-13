package com.core.webapp.storage;

import com.core.webapp.exception.StorageException;
import com.core.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
   private final Path path;

    public AbstractPathStorage(String dir) {
        path = Paths.get(dir);
        Objects.requireNonNull(path, "path must not be null");
        if (!Files.isDirectory(path) || !Files.isWritable(path)) {
            throw new IllegalArgumentException(dir + " is not path or is not writable");
        }
    }

    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(uuid);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            doWrite(resume, new BufferedOutputStream((OutputStream) path));
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path.getRoot(), path.toString(), e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected void doUpdate(Resume resume, Path Path) {
        try {
            doWrite(resume, new BufferedOutputStream((OutputStream) path));
        } catch (IOException e) {
            throw new StorageException(resume.getUuid(), "Path write error", e);
        }
    }

    @Override
    protected void doDelete(Path Path) {
        if (!path.toFile().delete()) {
            throw new StorageException("Path delete error", path.toString());
        }
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream((InputStream) path));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path Path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] paths = path.toFile().listFiles();
        if (paths == null) {
            throw new StorageException("path read error", null);
        }
        List<Resume> list = new ArrayList<>(paths.length);
        for (File path : paths) {
            list.add(doGet(path.toPath()));
        }
        return list;
    }

    @Override
    public void clear() {
        try {
            Files.list(path).forEach(this::doDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(path.toFile().list(), "path read error").length;
    }

}
