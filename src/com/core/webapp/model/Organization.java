package com.core.webapp.model;

import com.core.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.core.webapp.util.DateUtil.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private Link name;
    private List<Information> information = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Information... information) {
        this(new Link(name, url), Arrays.asList(information));
    }

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
        return "\nOrganization{" +
                "name= " + name +
                "information= " + information +
                "}";
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Information implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate start;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate end;
        private String position;
        private String info;

        public Information() {
        }

        public Information(int start, Month startM, String position, String info) {
            this(of(start, startM), NOW, position, info);
        }

        public Information(int start, Month startM, int end, Month endM, String position, String info) {
            this(of(start, startM), of(end, endM), position, info);
        }

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
            return " startDate: " + start +
                    " endDate: " + end +
                    " position: " + position +
                    " info: " + info +
                    "}";
        }
    }
}
