package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    public void save(Resume resume) {
        Object index = getResumeIndex(resume.getUuid());

        if (getSize() == STORAGE_LIMIT) {
            throw new StorageException("Storage is full.", resume.getUuid());
        }
        if (!isExist(index)) {
            insertResume(resume, index);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        Object index = getResumeIndex(resume.getUuid());

        if (isExist(index)) {
            updateResume(resume, index);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        Object index = getResumeIndex(uuid);

        if (isExist(index)) {
            deleteResume(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        Object index = getResumeIndex(uuid);

        if (isExist(index)) {
            return getResume(index);
        }

        throw new NotExistStorageException(uuid);
    }

    protected abstract boolean isExist(Object uuid);

    public abstract int getSize();

    protected abstract void deleteResume(Object resumeIndex);

    protected abstract Object getResumeIndex(String uuid);

    protected abstract void insertResume(Resume resume, Object resumeIndex);

    protected abstract void updateResume(Resume resume, Object resumeIndex);

    protected abstract Resume getResume(Object resumeIndex);

}
