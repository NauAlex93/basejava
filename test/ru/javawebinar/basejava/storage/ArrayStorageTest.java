package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class ArrayStorageTest {

    Storage storage;
    private static final String UUID_1;
    private static final String UUID_2;
    private static final String UUID_3;
    private static final String UUID_4;

    private static final Resume RESUME1;
    private static final Resume RESUME2;
    private static final Resume RESUME3;
    private static final Resume RESUME4;

    static {
        UUID_1 = "uuid1";
        UUID_2 = "uuid2";
        UUID_3 = "uuid3";
        UUID_4 = "uuid4";

        RESUME1 = new Resume(UUID_1);
        RESUME2 = new Resume(UUID_2);
        RESUME3 = new Resume(UUID_3);
        RESUME4 = new Resume(UUID_4);
    }

    public ArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();

        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getSize());
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME4);
        assertEquals(4, storage.getSize());
        assertEquals(RESUME4, storage.get(UUID_4));
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.get(UUID_1);
        storage.delete(UUID_1);
        assertEquals(2, storage.getSize());
        storage.get(UUID_1);
    }

    @Test
    public void get() throws Exception {
        assertEquals(RESUME1, storage.get(UUID_1));
        assertEquals(RESUME2, storage.get(UUID_2));
        assertEquals(RESUME3, storage.get(UUID_3));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumeArray = {RESUME1, RESUME2, RESUME3};
        assertArrayEquals(resumeArray, storage.getAll());
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistException() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExistException() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void getStorageOverFlow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException ex) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}