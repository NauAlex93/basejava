package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object index = getNonExistedResume(resume.getUuid());
        insertResume(resume, index);
    }

    public void update(Resume resume) {
        Object index = getExistedResume(resume.getUuid());
        updateResume(resume, index);
    }

    public void delete(String uuid) {
        Object index = getExistedResume(uuid);
        deleteResume(index);
    }

    public Resume get(String uuid) {
        Object index = getExistedResume(uuid);
        return getResume(index);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resultList = getAll();
        Collections.sort(resultList);
        return resultList;
    }

    private Object getExistedResume(String uuid) {
        Object resumeIndex = getResumeIndex(uuid);
        if (!isExist(resumeIndex)) {
            throw new NotExistStorageException(uuid);
        }
        return resumeIndex;
    }

    private Object getNonExistedResume(String uuid) {
        Object resumeIndex = getResumeIndex(uuid);
        if (isExist(resumeIndex)) {
            throw new ExistStorageException(uuid);
        }
        return resumeIndex;
    }

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(Object uuid);

    protected abstract void deleteResume(Object resumeIndex);

    protected abstract Object getResumeIndex(String uuid);

    protected abstract void insertResume(Resume resume, Object resumeIndex);

    protected abstract void updateResume(Resume resume, Object resumeIndex);

    protected abstract Resume getResume(Object resumeIndex);

}
