package org.example.collection_framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContactBook {
    List<Contact> contacts = new ArrayList<>();
    Set<Contact> contactSet = new HashSet<>();
    Map<String, List<Contact>> gcMap = new HashMap<>();

    public void add(Contact contact) {
        if (contactSet.add(contact)) {
            contacts.add(contact);
            List<Contact> contacts = gcMap.get(contact.getGroup());
            if (contacts == null) {
                contacts = new ArrayList<>();
                gcMap.put(contact.getGroup(), contacts);
            }
            contacts.add(contact);

            System.out.println("Контакт  успешно добавлен");
        } else System.out.println("Такой контакт был уже раннее добавлен");
    }

    public void delete(String name) {
        Iterator<Contact> it = contacts.iterator();
        Contact contactDel = null;
        while (it.hasNext()) {
            Contact contact = it.next();
            if (contact.getName().equals(name)) {
                contactDel = contact;
                it.remove();
                contactSet = new HashSet<>(contacts);
                List<Contact> contacts = gcMap.get(contact.getGroup());
                if (contacts != null) {
                    contacts.remove(contactDel);
                }
                System.out.println("Контакт  успешно удален");
            }
        }
        if (contactDel == null) {
            System.out.println("Такого контакта нет в телефонной книге");
        }
    }

    public void print() {
        if (contactSet.isEmpty()) {
            System.out.println("Контактная книга пуста");
            return;
        }
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void searchContact(String name) {
        Iterator<Contact> iterator = contacts.iterator();
        Contact foundContact = null;
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equals(name)) {
                foundContact = contact;
                System.out.println(contact);
            }
        }
        if (foundContact == null) {
            System.out.println("Контакт не найден");
        }
    }

    public void searchContactsByGroup(String group) {
        boolean found = false;
        for (Map.Entry<String, List<Contact>> entry : gcMap.entrySet()) {
            if (!(entry.getValue().isEmpty()) && entry.getKey().equals(group)) {
                found = true;
                System.out.println(entry.getValue());
            }
        }
        if (!found) {
            System.out.println("Контактов по введенной вами группе не найдено");
        }
    }

}
