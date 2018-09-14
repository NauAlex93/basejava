package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>(size);

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
        storage.set((int) resumeIndex, resume);
    }

    @Override
    protected void deleteResume(Object resumeIndex) {
        storage.remove(((Integer)resumeIndex).intValue());
    }

    @Override
    protected Resume getResume(Object resumeIndex) {
        return storage.get((int)resumeIndex);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object uuid) {
        return (Integer)uuid != null;
    }
}
