package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage[(int) resumeIndex] = resume;
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage[(int) resumeIndex];
    }

    @Override
    public int getSize() {
        return size;
    }
}
