package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.of;

public class Career {
    private final Link link;
    private List<Position> positions;

    public Career(String name, String url, Position... positions) {
        this.link = new Link(name, url);
        this.positions = Arrays.asList(positions);
    }

    public Link getLink() {
        return link;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(link, career.link) &&
                Objects.equals(positions, career.positions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(link, positions);
    }

    @Override
    public String toString() {
        return link + ", " + positions ;
    }

    public static class Position {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;


        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this.startDate = of(startYear, startMonth);
            this.endDate = of(endYear, endMonth);
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
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
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {

            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return startDate + " - " + endDate + ", " + title + ", " + description;
        }
    }
}

