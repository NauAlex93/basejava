package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void insertResume(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return storage.get(searchKey);
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
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}
