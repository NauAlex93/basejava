package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> content;

    public ListSection(List<String> content) {
        this.content = content;
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
