package com.core.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final Link name;

    private final LocalDate start;
    private final LocalDate end;
    private final String position;
    private final String info;

    public Organization(String name, String url, LocalDate start, LocalDate end, String position, String info) {
        Objects.requireNonNull(name, "Name must not be null");
        Objects.requireNonNull(start, "Start must not be null");
        Objects.requireNonNull(end, "End must not be null");
        Objects.requireNonNull(position, "Position must not be null");
        this.name = new Link(name, url);
        this.start = start;
        this.end = end;
        this.position = position;
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                position.equals(that.position) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end, position, info);
    }

    @Override
    public String toString() {
        return "\nOrganization{" +
                " name: " + name + "\n" +
                " startDate: " + start +
                " endDate: " + end + "\n" +
                " position: " + position +
                " info: " + info +
                "}";
    }
}
