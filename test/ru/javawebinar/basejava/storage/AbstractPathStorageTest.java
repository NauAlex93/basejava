package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serializer.ObjectStreamSerializer;

public class AbstractPathStorageTest extends AbstractStorageTest {

    public AbstractPathStorageTest() {
        super(new AbstractPathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}