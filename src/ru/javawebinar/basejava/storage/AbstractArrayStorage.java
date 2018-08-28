package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int resumeId = getResumeIndex(uuid);

        if (resumeId != -1) {
            return storage[resumeId];
        }

        System.out.println("ru.javawebinar.basejava.model.Resume is not in storage!");
        return null;
    }

    protected abstract int getResumeIndex(String uuid);

}
