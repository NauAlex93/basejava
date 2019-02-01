package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class CareerSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<Career> workPlaces;

    public CareerSection() {
    }

    public CareerSection(Career... workPlaces) {
        this(Arrays.asList(workPlaces));
    }

    public CareerSection(List<Career> workPlaces) {
        Objects.requireNonNull(workPlaces, "workPlaces must not be null");
        this.workPlaces = workPlaces;
    }

    public List<Career> getWorkPlaces() {
        return workPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CareerSection that = (CareerSection) o;
        return Objects.equals(workPlaces, that.workPlaces);
    }

    @Override
    public int hashCode() {

        return Objects.hash(workPlaces);
    }

    @Override
    public String toString() {
        return workPlaces.toString();
    }
}
