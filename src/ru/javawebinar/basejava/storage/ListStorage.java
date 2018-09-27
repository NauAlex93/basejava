package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getResumeIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void insertResume(Resume resume, Object resumeIndex) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Object resumeIndex) {
        storage.set((Integer) resumeIndex, resume);
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        storage.remove(resumeIndex);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage.get((Integer)resumeIndex);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object uuid) {
        return uuid != null;
    }
}
