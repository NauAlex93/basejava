package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    private StreamSerializer streamSerializer;

    protected AbstractPathStorage(String dir, StreamSerializer streamSerializer) {
        directory = Paths.get(dir);
        this.streamSerializer = streamSerializer;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException((dir + " is not a directory or is not writable"));
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", null);
        }
    }

    @Override
    public int getSize() {
        int size;
        try {
            size = (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("IO error ", e.getMessage());
        }
        return size;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new File(directory.toFile(), uuid).toPath();
    }

    @Override
    protected void updateResume(Resume resume, Path file) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error ", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    protected void insertResume(Resume resume, Path file) {
        try {
            Files.createFile(file);
            streamSerializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error ", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getResume(Path file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getFileName().toString(), e);
        }
    }

    @Override
    protected void deleteResume(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>();
        try {
            list = Files.list(directory).map(this::getResume).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("IO error ", e.getMessage());
        }
        return list;
    }
}
