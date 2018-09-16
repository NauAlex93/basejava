package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected void saveImpl(Resume resume, Object resumeIndex) {
        storage[size] = resume;
    }

    @Override
    protected void deleteImpl(Object resumeIndex) {
        storage[(int) resumeIndex] = storage[size - 1];
        storage[size - 1] = null;
    }
}
