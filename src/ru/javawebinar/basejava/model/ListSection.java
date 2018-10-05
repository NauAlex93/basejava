package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> infoSections;

    public ListSection(List<String> infoSections) {
        this.infoSections = infoSections;
    }

    public List<String> getInfoSections() {
        return infoSections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(infoSections, that.infoSections);
    }

    @Override
    public int hashCode() {

        return Objects.hash(infoSections);
    }

    @Override
    public String toString() {
        return infoSections.toString();
    }
}
