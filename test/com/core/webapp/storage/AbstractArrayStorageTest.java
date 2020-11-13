package com.core.webapp.storage;

import com.core.webapp.exception.NotExistStorageException;
import com.core.webapp.exception.StorageException;
import com.core.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void save() {
        Resume resume = new Resume("save");
        storage.save(resume);
        Assert.assertEquals(resume, storage.get("save"));
    }

    @Test
    public void update() {
        Resume resume = storage.get(UUID_1);
        Assert.assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(UUID_3, storage.get(UUID_3).getUuid());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get(UUID_2).getUuid());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(storage.size(), storage.getAll().length);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void getStorageException() {
        storage.clear();
        try {
            for (int i = 0; i < 10000; i++) {
                storage.save(new Resume());
            }
        }catch (StorageException e) {
            Assert.fail("overflow occurred ahead of time");
        }
        storage.save(new Resume());
    }

}