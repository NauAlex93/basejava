package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void insertResume(Resume resume, Integer resumeIndex) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Integer resumeIndex) {
        storage.set((Integer) resumeIndex, resume);
    }

    @Override
    protected void deleteResume(Integer resumeIndex) {
        storage.remove(resumeIndex);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Resume getResume(Integer resumeIndex) {
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
    protected boolean isExist(Integer uuid) {
        return uuid != null;
    }
}
