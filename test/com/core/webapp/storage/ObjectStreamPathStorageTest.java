package com.core.webapp.storage;

import java.nio.file.Path;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.toPath()));
    }
}
