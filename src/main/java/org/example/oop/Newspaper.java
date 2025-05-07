package org.example.oop;

import java.util.Objects;

public class Newspaper extends Publication implements Printable {
    private String publicationDay;

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        this.publicationDay = publicationDay;
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Newspaper newspaper = (Newspaper) o;
        return Objects.equals(publicationDay, newspaper.publicationDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationDay);
    }

    @Override
    public String toString() {
        return "Newspaper{" + "title='" + getTitle() + '\'' + ", author='" + getAuthor() + '\'' + ", " +
                "year=" + getYear() + ", publicationDay='" + publicationDay + '\'' + '}';
    }

    public String getType() {
        return "Newspaper";
    }

    @Override
    public void printDetails() {
        System.out.println("Type: " + getType() + ", publication day: " + getPublicationDay() + ", title: "
                + getTitle() + ", author: " + getAuthor() + ", year: " + getYear());
    }
}
