package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator RESUME_COMPARATOR = (Comparator<Resume>) (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
    */

    @Override
    protected Integer getResumeIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void saveImpl(Resume resume, int resumeIndex) {
        int insertIndex = -resumeIndex - 1;

        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected void deleteImpl(int resumeIndex) {
        int resumesToMove = size - resumeIndex - 1;

        if (resumesToMove > 0) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, resumesToMove);
        }
    }
}
