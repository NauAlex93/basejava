package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected void insertResume(Resume resume, Integer resumeIndex) {
        if (isFull()) {
            throw new StorageException("Storage is full.", resume.getUuid());
        }
        saveImpl(resume, resumeIndex);
        size++;
    }

    private boolean isFull() {
        return size == STORAGE_LIMIT;
    }

    @Override
    protected void deleteResume(Integer resumeIndex) {
        deleteImpl(resumeIndex);
        storage[--size] = null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    
    @Override
    protected void updateResume(Resume resume, Integer resumeIndex) {
        storage[resumeIndex] = resume;
    }

    @Override
    protected List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected Resume getResume(Integer resumeIndex) {
        return storage[resumeIndex];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected boolean isExist(Integer uuid) {
        return uuid >= 0;
    }

    protected abstract void saveImpl(Resume resume, int resumeIndex);

    protected abstract void deleteImpl(int resumeIndex);
}
