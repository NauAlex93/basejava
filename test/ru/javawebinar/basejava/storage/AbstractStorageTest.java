package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTest;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    Storage storage;
    private static final String UUID_1 = ResumeTest.getUuid1();
    private static final String UUID_2 = ResumeTest.getUuid2();
    private static final String UUID_3 = ResumeTest.getUuid3();
    private static final String UUID_4 = ResumeTest.getUuid4();

    private static final Resume RESUME1;
    private static final Resume RESUME2;
    private static final Resume RESUME3;
    private static final Resume RESUME4;

    static {
        RESUME1 = ResumeTest.getRESUME1();
        RESUME2 = ResumeTest.getRESUME2();
        RESUME3 = ResumeTest.getRESUME3();
        RESUME4 = ResumeTest.getRESUME4();
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

    @Test(expected = ExistStorageException.class)
    public void saveExistException() throws Exception {
        storage.save(RESUME1);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1, "f");
        storage.update(resume);
        assertTrue(resume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistException() throws Exception {
        storage.update(RESUME4);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertEquals(2, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
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
        assertEquals(3, storage.getSize());
        assertEquals(Arrays.asList(RESUME1, RESUME2, RESUME3), resumeList);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.getSize());
    }
}