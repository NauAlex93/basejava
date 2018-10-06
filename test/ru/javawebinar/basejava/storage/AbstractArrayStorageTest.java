package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void getStorageOverFlow() throws Exception {
        try {
            for (int i = storage.getSize(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("AN"));
            }
        } catch (StorageException ex) {
            Assert.fail("StorageException was caught");
        }
        storage.save(new Resume("AN"));
    }
}