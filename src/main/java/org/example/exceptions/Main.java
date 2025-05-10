package org.example.exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("""
                    Выберите операцию:
                    1. Вывести каталог.
                    2. Добавить объект.
                    3. Выдать объект.
                    4. Вернуть объект.
                    5. Выйти из приложения""");
            Library library = new Library();
            while (true) {
                try {
                    int operation = sc.nextInt();
                    sc.nextLine();
                    switch (operation) {
                        case 1:
                            library.listBooks();
                            break;
                        case 2:
                            System.out.print("Введите название книги: ");
                            String title = sc.nextLine();
                            System.out.print("Введите автора книги: ");
                            String author = sc.nextLine();
                            System.out.print("Введите количество доступных копий: ");
                            int availableCopies = sc.nextInt();
                            Book book = new Book(title, author, availableCopies);
                            library.addBook(book);
                            System.out.println("Книга добавлена.");
                            break;
                        case 3:
                            System.out.print("Введите название книги для выдачи: ");
                            String takeTitle = sc.nextLine();
                            library.takeBook(takeTitle);
                            break;
                        case 4:
                            System.out.print("Введите название книги для возврата: ");
                            String returnTitle = sc.nextLine();
                            library.returnBook(returnTitle);
                            break;
                        case 5:
                            sc.close();
                            System.out.println("Вы вышли ииз приложения.");
                            return;
                        default:
                            System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Ошибка ввода: нужно ввести число. Введите номер опции заново");
                    sc.nextLine();
                } catch (NoAvailableCopiesException | NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
