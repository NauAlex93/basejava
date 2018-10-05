package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Career {
    private final String link;
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private final String description;

    public Career(String link, YearMonth startDdate, YearMonth endDdate, String title, String description) {
        this.link = link;
        this.startDate = startDdate;
        this.endDate = endDdate;
        this.title = title;
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public YearMonth getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(link, career.link) &&
                Objects.equals(startDate, career.startDate) &&
                Objects.equals(endDate, career.endDate) &&
                Objects.equals(title, career.title) &&
                Objects.equals(description, career.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(link, startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return link + ", " + startDate + " - " + endDate + ", " + title + ", " + description;
    }
}
