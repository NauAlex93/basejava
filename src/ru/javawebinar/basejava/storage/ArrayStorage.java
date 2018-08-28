package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_LIMIT = 10000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ru.javawebinar.basejava.storage.Storage is full.");
            return;
        }
        if (getResumeIndex(resume.getUuid()) == -1) {
            storage[size++] = resume;
        } else {
            System.out.println("ru.javawebinar.basejava.model.Resume already in storage!");
        }
    }

    public void update(Resume resume) {
        int resumeId = getResumeIndex(resume.getUuid());

        if (resumeId != -1) {
            storage[resumeId] = resume;
        } else {
            System.out.println("ru.javawebinar.basejava.model.Resume is not in storage!");
        }
    }

    public void delete(String uuid) {
        int resumeId = getResumeIndex(uuid);

        if (resumeId != -1) {
            storage[resumeId] = storage[--size];
            storage[size] = null;
        } else {
            System.out.println("ru.javawebinar.basejava.model.Resume is not in storage!");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }

        return -1;
    }
}
