package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getResumeIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        storage.remove(resumeIndex);
    }

    @Override
    protected void insertResume(Resume resume, Object resumeIndex) {
        storage.put((String) resumeIndex, resume);
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage.put((String) resumeIndex, resume);
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
    public Resume[] getAll() {
        Resume[] result = storage.values().toArray(new Resume[storage.size()]);
        Arrays.sort(result);
        return result;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }

}
