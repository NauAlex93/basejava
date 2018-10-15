package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serializer.ObjectStreamSerializer;

public class AbstractFileStorageTest extends AbstractStorageTest {

    public AbstractFileStorageTest() {
        super(new AbstractFileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}