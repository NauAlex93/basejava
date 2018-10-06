package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume>{

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    protected void insertResume(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateResume(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
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
