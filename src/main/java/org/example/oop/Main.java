package org.example.oop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Введите одну из опций
                 1: Добавить новую публикацию.
                 2: Вывести список всех публикаций.
                 3: Поиск публикации по автору.
                 4: Вывести общее количество публикаций.
                 0: Выход.
                """);
        while (true) {
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("""
                            Выберите тип публикации:
                            1 – Book,
                            2 – Magazine,
                            3 – Newspaper
                            """);
                    int type = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Введите название: ");
                    String title = sc.nextLine();

                    System.out.print("Введите автора: ");
                    String author = sc.nextLine();

                    System.out.print("Введите год: ");
                    String input = sc.nextLine();
                    if (!input.matches("\\d+")) {
                        System.out.println("Нужно ввести число");
                        break;
                    }
                    int year = Integer.parseInt(input);

                    switch (type) {
                        case 1:
                            System.out.print("Введите ISBN: ");
                            String ISBN = sc.nextLine().trim();

                            Publication book = new Book(title, author, year, ISBN);
                            library.addPublication(book);

                            System.out.println("Книга добавлена");
                            break;
                        case 2:
                            System.out.println("Введите номер выпуска");
                            int issueNumber = Integer.parseInt(sc.nextLine().trim());

                            Publication magazine = new Magazine(title, author, year, issueNumber);
                            library.addPublication(magazine);

                            System.out.println("Журнал добавлен");
                            break;
                        case 3:
                            System.out.println("Введите день публикации");
                            String publicationDay = sc.nextLine();

                            Publication newspaper = new Newspaper(title, author, year, publicationDay);
                            library.addPublication(newspaper);

                            System.out.println("Газета добавлена");
                            break;
                        default:
                            System.out.println("Неккоректный тип публикации");
                            break;
                    }
                    break;
                case 2:
                    library.listPublications();
                    break;
                case 3:
                    System.out.print("Введите автора книги: ");
                    String findAuthor = sc.nextLine().trim();
                    library.searchByAuthor(findAuthor);
                    break;
                case 4:
                    System.out.println("Общее количество публикаций: " + Library.getTotalPublications());
                    break;
                case 0:
                    System.out.println("Вы вышли из программы.");
                    sc.close();
                    return;
                default:
                    System.out.println("Некорректный выбор, попробуйте снова.");
                    break;
            }
        }
    }
}