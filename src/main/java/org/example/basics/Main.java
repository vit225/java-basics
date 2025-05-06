package org.example.basics;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Введите одну из следующих команд:
                add - для добавления контакта,
                getAll - для просмотра всех контактов,
                getOne - для просмотра контакта по имени
                delete - для удаления контакта
                exit - для выхода из программы""");
        String[] names = new String[100];
        long[] number = new long[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name = scanner.nextLine();
            switch (name) {
                case "add":
                    if (index >= names.length) {
                        System.out.println("В контактной книге больше нет места");
                        break;
                    }
                    System.out.println("Введите имя контакта");
                    names[index] = scanner.nextLine();
                    System.out.println("Введите номер телефона контакта");
                    number[index++] = scanner.nextLong();
                    System.out.println("Контакт добавлен");
                    scanner.nextLine();
                    break;
                case "getAll":
                    if (index == 0) {
                        System.out.println("Нет контактов для отображения");
                    } else {
                        for (int i = 0; i < index; i++) {
                            if (names[i] != null) {
                                System.out.println(names[i] + " - " + number[i]);
                            }
                        }
                    }
                    break;
                case "getOne":
                    System.out.println("Введите имя контакта");
                    String getName = scanner.nextLine();
                    boolean find = false;
                    for (int i = 0; i < index; i++) {
                        if (names[i] != null && names[i].equals(getName)) {
                            System.out.println(names[i] + " - " + number[i]);
                            find = true;
                        }
                    }
                    if (!find) {
                        System.out.println("Контакт не найден");
                    }
                    break;
                case "delete":
                    System.out.println("Введите имя контакта, который хотите удалить");
                    String deleteName = scanner.nextLine();
                    boolean del = false;
                    for (int i = 0; i < index; i++) {
                        if (names[i] != null && names[i].equals(deleteName)) {
                            for (int j = i; j < index - 1; j++) {
                                names[j] = names[j + 1];
                                number[j] = number[j + 1];
                            }
                            names[names.length - 1] = null;
                            number[number.length - 1] = 0;
                            del = true;
                            index--;
                            System.out.println("Контакт удален");
                            break;
                        }
                    }
                    if (!del) {
                        System.out.println("Такой контакт не найден");
                    }
                    break;
                case "exit":
                    scanner.close();
                    System.out.println("Вы закрыли программу");
                    return;
                default:
                    System.out.println("Такой команды не существует");
                    break;
            }
        }
    }
}