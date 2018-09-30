package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK index = getNonExistedResume(resume.getUuid());
        insertResume(resume, index);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK index = getExistedResume(resume.getUuid());
        updateResume(resume, index);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK index = getExistedResume(uuid);
        deleteResume(index);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK index = getExistedResume(uuid);
        return getResume(index);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resultList = getAll();
        Collections.sort(resultList);
        return resultList;
    }

    private SK getExistedResume(String uuid) {
        SK resumeIndex = getResumeIndex(uuid);
        if (!isExist(resumeIndex)) {
            LOG.warning("Resume " + uuid + " already exists!");
            throw new NotExistStorageException(uuid);
        }
        return resumeIndex;
    }

    private SK getNonExistedResume(String uuid) {
        SK resumeIndex = getResumeIndex(uuid);
        if (isExist(resumeIndex)) {
            LOG.warning("Resume " + uuid + "do not exists!");
            throw new ExistStorageException(uuid);
        }
        return resumeIndex;
    }

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(SK uuid);

    protected abstract void deleteResume(SK resumeIndex);

    protected abstract SK getResumeIndex(String uuid);

    protected abstract void insertResume(Resume resume, SK resumeIndex);

    protected abstract void updateResume(Resume resume, SK resumeIndex);

    protected abstract Resume getResume(SK resumeIndex);

}
