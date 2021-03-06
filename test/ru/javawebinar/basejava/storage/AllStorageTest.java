package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.javawebinar.basejava.serializer.JsonStreamSerializer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ArrayStorageTest.class,
    SortedArrayStorageTest.class,
    ListStorageTest.class,
    MapUuidStorageTest.class,
    MapResumeStorageTest.class,
    AbstractFileStorageTest.class,
    AbstractPathStorageTest.class,
    XmlPathStorageTest.class,
    JsonPathStorageTest.class,
    DataPathStorageTest.class,
    SqlStorageTest.class
})
public class AllStorageTest {
}
