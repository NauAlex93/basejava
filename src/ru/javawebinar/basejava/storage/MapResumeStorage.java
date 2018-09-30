package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume>{

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResumeIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(Resume resumeIndex) {
        storage.remove(resumeIndex).getUuid();
    }

    @Override
    protected void insertResume(Resume resume, Resume resumeIndex) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Resume resumeIndex) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(Resume resumeIndex) {
        return resumeIndex;
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Resume uuid) {
        return uuid != null;
    }
}
