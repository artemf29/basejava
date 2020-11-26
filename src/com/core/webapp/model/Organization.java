package com.core.webapp.model;

import java.util.Date;
import java.util.Objects;

public class Organization {
    private final String name;
    private final Date start;
    private final Date end;
    private final String position;
    private final String info;

    public Organization(String name, Date start, Date end, String position, String info) {
        Objects.requireNonNull(name, "Name must not be null");
        Objects.requireNonNull(start, "Start must not be null");
        Objects.requireNonNull(end, "End must not be null");
        Objects.requireNonNull(position, "Position must not be null");
        this.name = name;
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
        return Objects.equals(name, that.name) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(position, that.position) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end, position, info);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name: " + name +
                "startDate: " + start +
                "endDate: " + end +
                "position: " + position +
                "info: " + info +
                "}";
    }
}
