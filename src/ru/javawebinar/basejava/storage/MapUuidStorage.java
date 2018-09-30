package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getResumeIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void deleteResume(String resumeIndex) {
        storage.remove(resumeIndex);
    }

    @Override
    protected void insertResume(Resume resume, String resumeIndex) {
        storage.put(resumeIndex, resume);
    }

    @Override
    protected void updateResume(Resume resume, String resumeIndex) {
        storage.put(resumeIndex, resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(String resumeIndex) {
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
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

}
