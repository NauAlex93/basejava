package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage{

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResumeIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        storage.remove((Resume) resumeIndex).getUuid();
    }

    @Override
    protected void insertResume(Resume resume, Object resumeIndex) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getAll() {
        return Collections.emptyList();
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage.get(resumeIndex);
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
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }
}
