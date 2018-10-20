package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new AbstractPathStorage(STORAGE_DIR.getPath(), new JsonStreamSerializer()));
    }
}