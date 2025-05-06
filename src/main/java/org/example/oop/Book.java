package org.example.oop;

import java.util.Objects;

public class Book extends Publication implements Printable {
    private String ISBN;

    public Book(String title, String author, int year, String ISBN) {
        super(title, author, year);
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ISBN);
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + getTitle() +  '\'' + ", author='" + getAuthor() + '\'' + ", " +
                "year=" + getYear() +  ", ISBN='" + ISBN + '\''  + '}';
    }

    public String getType() {
        return "Book";
    }

    @Override
    public void printDetails() {
        System.out.println("Type: " + getType() + ", ISBN: " + getISBN() + ", title: "
                + getTitle() + ", author: " + getAuthor() + ", year: " + getYear());
    }
}
