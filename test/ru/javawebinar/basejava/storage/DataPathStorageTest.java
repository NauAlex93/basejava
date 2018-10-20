package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new AbstractPathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}