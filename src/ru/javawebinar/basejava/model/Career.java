package ru.javawebinar.basejava.model;

import java.util.Date;
import java.util.Objects;

public class Career {
    private final String link;
    private final Date startDdate;
    private final Date endDdate;
    private final String title;
    private final String description;

    public Career(String link, Date startDdate, Date endDdate, String title, String description) {
        this.link = link;
        this.startDdate = startDdate;
        this.endDdate = endDdate;
        this.title = title;
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public Date getStartDdate() {
        return startDdate;
    }

    public Date getEndDdate() {
        return endDdate;
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
                Objects.equals(startDdate, career.startDdate) &&
                Objects.equals(endDdate, career.endDdate) &&
                Objects.equals(title, career.title) &&
                Objects.equals(description, career.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(link, startDdate, endDdate, title, description);
    }

    @Override
    public String toString() {
        return "Career{" +
                "link='" + link + '\'' +
                ", startDdate=" + startDdate +
                ", endDdate=" + endDdate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
