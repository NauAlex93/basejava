package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected void saveImpl(Resume resume, int resumeIndex) {
        storage[size] = resume;
    }

    @Override
    protected void deleteImpl(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
    }
}
