package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

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

        RESUME1 = new Resume(UUID_1, "a");
        RESUME2 = new Resume(UUID_2, "b");
        RESUME3 = new Resume(UUID_3, "c");
        RESUME4 = new Resume(UUID_4, "d");
    }

    public AbstractStorageTest(Storage storage) {
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
        Resume resume = new Resume(UUID_1, "f");
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
    public void getAllSorted() throws Exception {
        List<Resume> resumeList = storage.getAllSorted();
        assertEquals(resumeList.get(0), RESUME2);
        assertEquals(resumeList.get(1), RESUME1);
        assertEquals(resumeList.get(2), RESUME3);
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
        storage.save(new Resume(UUID_1, "f"));
    }
}