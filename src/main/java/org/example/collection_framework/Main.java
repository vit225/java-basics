package org.example.collection_framework;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactBook contactBook = new ContactBook();
        System.out.println("""
                Выберите действие:
                1 - Добавить контакт
                2 - Удалить контакт
                3 - Посмотреть все контакты
                4 - Найти контакт по имени
                5 - Посмотреть контакты по группе
                0 - Выход""");
        while (true) {
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String addName = sc.nextLine();
                    System.out.print("Введите телефон: ");
                    String addPhone = sc.nextLine();
                    System.out.print("Введите email: ");
                    String addEmail = sc.nextLine();
                    System.out.print("Введите группу: ");
                    String addGroup = sc.nextLine();
                    Contact addContact = new Contact(addName, addPhone, addEmail, addGroup);
                    contactBook.add(addContact);
                    break;
                case 2:
                    System.out.print("Введите имя контакта, который вы хотите удалить: ");
                    String delName = sc.nextLine();
                    contactBook.delete(delName);
                    break;
                case 3:
                    contactBook.print();
                    break;
                case 4:
                    System.out.print("Введите имя контакта: ");
                    String findName = sc.nextLine();
                    contactBook.searchContact(findName);
                    break;
                case 5:
                    System.out.print("Введите группу: ");
                    String findGroup = sc.nextLine();
                    contactBook.searchContactsByGroup(findGroup);
                    break;
                case 0:
                    sc.close();
                    System.out.print("Вы вышли из программы");
                    return;
            }
        }
    }
}
