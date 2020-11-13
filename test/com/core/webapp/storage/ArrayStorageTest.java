package com.core.webapp.storage;

import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
       super(new ArrayStorage());
    }

    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void insertElement() {
    }

    @Test
    public void fillDeletedElement() {
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0,0);
    }
}