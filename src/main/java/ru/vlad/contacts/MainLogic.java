package ru.vlad.contacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainLogic {
    public static final List<Contact> contacts = new ArrayList<>();
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void selectAction() {
        System.out.println("open phonebook.db");
        boolean isExit = false;
        while(!isExit) {
            try {
                System.out.println("[menu] Enter action (add, list, search, count, exit): ");
                String answer = bufferedReader.readLine();
                switch (answer.toLowerCase()) {
                    case "add": addContact(); break;
                    case "count": System.out.printf("The Phone Book has %d records.\n", contacts.size()); break;
                    case "list": if (contacts.size() != 0) list();
                    else System.out.println("Records not added!"); break;
                    case "exit": isExit = true; clearData(); break;
                    case "search": search(); break;
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    public void search() throws IOException {
        printTxt("Enter search query: ");
            String search = bufferedReader.readLine().toLowerCase();
            int count = 0;
            for (Contact contact : contacts) {
                if (contact.getFullName().toLowerCase().contains(search)) {
                    count++;
                }
            }
            System.out.printf("Found %d results: \n", count);
            count = 0;
            for (Contact contact : contacts) {
                if (contact.getFullName().toLowerCase().contains(search)) {
                    System.out.printf(" %d. %s\n", (count + 1), contact.getFullName());
                    count++;
                }
            }
            searchAction();
    }

    public void searchAction() throws IOException {
        printTxt("\n[search] Enter action ([number], back, again): ");
        String choose = bufferedReader.readLine().toLowerCase();
        int chooseInt = Integer.parseInt(choose) - 1;
        if (!choose.equals("back")) {
            if (choose.equals("again")) {
                search();
            } else {
                try {
                    contacts.get(chooseInt).showInfo();
                    recordAction(chooseInt);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    printTxt("Error!");
                }
            }
        }
    }

    public void recordAction(int index) throws IOException {
        printTxt("[record] Enter action (edit, delete, menu): ");
        String select = bufferedReader.readLine().toLowerCase();
        if (select.equals("edit")) {
            editContact(index);
        } else if (select.equals("delete")) {
            removeContact(index);
        }
    }

    public void list() throws IOException {
        showList();
        printTxt("[list] Enter action ([number], back): ");
        String choose = bufferedReader.readLine().toLowerCase();
        if (!choose.equals("back")) {
            try {
                int index = Integer.parseInt(choose) - 1;
                info(index);
                recordAction(index);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                printTxt("Error!");
            }
        }
    }

    public void info(int index) {
        try {
            contacts.get(index).showInfo();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printTxt("Error!");
        }
    }

    public void editContact(int index) throws IOException {
        contacts.get(index).editContact();
    }

    public void addContact() throws IOException {
        printTxt("[Add] Enter the type (person, organization, back): ");
        String type = bufferedReader.readLine();
        switch (type.toLowerCase()) {
            case "person": Person.addContact(); break;
            case "organization": Organization.addContact(); break;
            case "back": break;
        }
    }

    public void removeContact(int index) {
        contacts.remove(index);
        printTxt("The record removed!");
    }


    public void showList() {
        int i = 1;
        for (Contact contact : contacts) {
            System.out.printf(" %d. %s\n", i, contact.getFullName());
                i++;
        }
        printTxt("\n");
    }

    public void clearData() throws IOException {
        bufferedReader.close();
        contacts.clear();
    }

    public static void printTxt(String text) {
        System.out.println(text);
    }
}
