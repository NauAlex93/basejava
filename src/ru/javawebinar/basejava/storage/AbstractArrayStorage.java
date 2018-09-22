package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected void insertResume(Resume resume, Object resumeIndex) {
        if (isFull()) {
            throw new StorageException("Storage is full.", resume.getUuid());
        }
        saveImpl(resume, (Integer) resumeIndex);
        size++;
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        deleteImpl((Integer) resumeIndex);
        storage[--size] = null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        if (this instanceof SortedArrayStorage) {
            return Arrays.asList(Arrays.copyOf(storage, size));
        }
        Arrays.sort(storage);
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage[(Integer) resumeIndex] = resume;
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage[(Integer) resumeIndex];
    }

    @Override
    public int getSize() {
        return size;
    }

    protected boolean isFull() {
        return size == STORAGE_LIMIT;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return (Integer) uuid >= 0;
    }

    protected abstract void saveImpl(Resume resume, int resumeIndex);

    protected abstract void deleteImpl(int resumeIndex);
}
