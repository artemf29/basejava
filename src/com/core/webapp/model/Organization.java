package com.core.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link name;
    private final List<Information> information;

    public Organization(Link name, List<Information> information) {
        Objects.requireNonNull(name, "Name must not be null");
        Objects.requireNonNull(information, "Information must not be null");
        this.name = name;
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name= " + name +
                "information= " + information +
                "}";
    }

    public static class Information {
        private final LocalDate start;
        private final LocalDate end;
        private final String position;
        private final String info;

        public Information(LocalDate start, LocalDate end, String position, String info) {
            Objects.requireNonNull(start, "Start must not be null");
            Objects.requireNonNull(end, "End must not be null");
            Objects.requireNonNull(position, "Position must not be null");
            this.start = start;
            this.end = end;
            this.position = position;
            this.info = info;
        }

        public LocalDate getStart() {
            return start;
        }

        public LocalDate getEnd() {
            return end;
        }

        public String getPosition() {
            return position;
        }

        public String getInfo() {
            return info;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Information that = (Information) o;
            return start.equals(that.start) &&
                    end.equals(that.end) &&
                    position.equals(that.position) &&
                    Objects.equals(info, that.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, position, info);
        }

        @Override
        public String toString() {
            return  " startDate: " + start +
                    " endDate: " + end + "\n" +
                    " position: " + position +
                    " info: " + info +
                    "}";
        }
    }
}
