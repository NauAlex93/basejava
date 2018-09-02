package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getResumeIndex(resume.getUuid());

        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full.");
            return;
        }
        if (index < 0) {
            insertResume(resume, index);
            size++;
        } else {
            System.out.println("Resume already in storage!");
        }
    }

    public void update(Resume resume) {
        int index = getResumeIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume is not in storage!");
        }
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);

        if (index >= 0) {
            deleteResume(index);
            storage[--size] = null;
        } else {
            System.out.println("Resume is not in storage!");
        }
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);

        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Resume is not in storage!");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void deleteResume(int resumeIndex);

    protected abstract int getResumeIndex(String uuid);

    protected abstract void insertResume(Resume resume, int resumeIndex);

}
