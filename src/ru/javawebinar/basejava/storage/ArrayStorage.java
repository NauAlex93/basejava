package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected void insertResume(Resume resume, int resumeIndex) {
        storage[size] = resume;
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
    }
}
