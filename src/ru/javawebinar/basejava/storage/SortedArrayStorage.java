package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume, int resumeIndex) {
        int insertIndex = -resumeIndex - 1;

        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        int resumesToMove = size - resumeIndex - 1;

        if (resumesToMove > 0) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, resumesToMove);
        }
    }
}
