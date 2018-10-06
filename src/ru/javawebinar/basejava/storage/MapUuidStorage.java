package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void insertResume(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected void updateResume(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
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
