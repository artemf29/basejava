package com.core.webapp.model;

import java.util.Objects;

public class Section {
    private OrganizationSection organizationSection;
    private TextSection textSection;
    private ListSection listSection;

    public Section(ListSection listSection) {
        Objects.requireNonNull(listSection,"listSection must not be null");
        this.listSection = listSection;
    }
    public Section(OrganizationSection organizationSection) {
        Objects.requireNonNull(organizationSection,"organizationSection must not be null");
        this.organizationSection = organizationSection;
    }
    public Section(TextSection textSection) {
        Objects.requireNonNull(textSection,"textSection must not be null");
        this.textSection = textSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(organizationSection, section.organizationSection) &&
                Objects.equals(textSection, section.textSection) &&
                Objects.equals(listSection, section.listSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationSection, textSection, listSection);
    }

    @Override
    public String toString() {
        String mess = "";
        if(organizationSection != null)
            mess += organizationSection.toString() + "\n";
        if(listSection != null)
            mess += listSection.toString() + "\n";
        if(textSection != null)
            mess += textSection.toString();
        return  mess;
    }
}
