package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getResumeIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveImpl(Resume resume, Object resumeIndex) {
        int insertIndex = -(int) resumeIndex - 1;

        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected void deleteImpl(Object resumeIndex) {
        int resumesToMove = size - (int) resumeIndex - 1;

        if (resumesToMove > 0) {
            System.arraycopy(storage, (int) resumeIndex + 1, storage, (int) resumeIndex, resumesToMove);
        }
    }
}
