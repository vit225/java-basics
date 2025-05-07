package org.example.oop;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Publication> publications = new ArrayList<>();


    public void addPublication(Publication pub) {
        publications.add(pub);
        Publication.setPublicationCounter(Publication.getPublicationCounter() + 1);
    }

    public void listPublications() {
        for (Publication pub : publications) {
            System.out.println(pub.toString());
        }
    }

    public void searchByAuthor(String author) {
        for (Publication pub : publications) {
            if (pub.getAuthor().equals(author)) {
                System.out.print(pub);
            }
        }
    }
    public static int getTotalPublications() {
        return Publication.getPublicationCounter();
    }
}
