package org.example.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private final List<Book> catalog = new ArrayList<>();

    public void listBooks() {
        if (catalog.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }
        for (Book book : catalog) {
            System.out.println(book);
        }
    }

    public void addBook(Book book) {
        catalog.add(book);
    }

    public void takeBook(String title) throws NoAvailableCopiesException, NoSuchElementException {

        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                if (book.getAvailableCopies() == 0) {
                    throw new NoAvailableCopiesException("Нет свободных экземпляров");

                }
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                System.out.println("Книга выдана.");
                return;
            }
        }
        throw new NoSuchElementException("Книги с таким названием нет в каталоге");
    }

    public void returnBook(String title) {
        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                System.out.println("Книга возвращена.");
                return;
            }
        }
        System.out.println("В каталоге нет книги с таким названием");
        throw new NoSuchElementException();
    }
}
