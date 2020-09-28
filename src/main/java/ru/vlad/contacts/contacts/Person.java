package ru.vlad.contacts.contacts;

import ru.vlad.contacts.basic.BasicActionsBook;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Contact {
    private String surname;
    private String gender;
    public LocalDate birthday;
    public LocalDateTime createDate;
    public LocalDateTime lastEdit;

    public Person(String phoneNumber, String name, String surname, String date, String gender) {
        super(phoneNumber, name);
        createDate = LocalDateTime.now();
        lastEdit = createDate;
        birthday = LocalDate.parse(date);
        this.surname = surname;
        this.gender = gender;
    }

    @Override
    public void editContact() throws IOException {
        System.out.println("Select a field (name, surname, birth, gender, number): ");
        String field = BasicActionsBook.bufferedReader.readLine();
        //field edit name, surname, number.
        switch (field.toLowerCase()) {
            case "name":
                System.out.println("Enter name: ");
                String newName = BasicActionsBook.bufferedReader.readLine();
                setName(newName);
                System.out.println("Saved");
                System.out.println();
                setLastEdit(LocalDateTime.now());
                break;
            case "surname":
                System.out.println("Enter surname: ");
                String newSurname = BasicActionsBook.bufferedReader.readLine();
                setSurname(newSurname);
                System.out.println("Saved");
                System.out.println();
                setLastEdit(LocalDateTime.now());
                break;
            case "number":
                System.out.println("Enter number: ");
                String newNumber = BasicActionsBook.bufferedReader.readLine();
                setPhoneNumber(newNumber);
                System.out.println("Saved");
                System.out.println();
                setLastEdit(LocalDateTime.now());
                break;
            case "birth":
                System.out.println("Enter birth [YYYY-MM-dd]:");
                String newBirth = BasicActionsBook.bufferedReader.readLine();
                if (newBirth.equals("") || newBirth.length() < 9) {
                    System.out.println("Bad birth date!");
                    newBirth = "1000-10-10";
                }
                setBirthday(newBirth);
                System.out.println("Saved");
                System.out.println();
                setLastEdit(LocalDateTime.now());
                break;
            case "gender":
                System.out.println("Enter gender: ");
                String newGender = BasicActionsBook.bufferedReader.readLine().toUpperCase();
                if (!newGender.equals("M") && !newGender.equals("F")) {
                    System.out.println("Bad gender!");
                    newGender = "[no data]";
                }
                setGender(newGender);
                System.out.println("Saved");
                System.out.println();
                setLastEdit(LocalDateTime.now());
                break;
        }
    }

    public static void addContact() throws IOException {
        System.out.println("Enter the name: ");
        String perName = BasicActionsBook.bufferedReader.readLine();
        System.out.println("Enter the surname: ");
        String perSurname = BasicActionsBook.bufferedReader.readLine();
        System.out.println("Enter the birth date [YYYY-MM-dd]:");
        String perDate = BasicActionsBook.bufferedReader.readLine();
        if (perDate.equals("") || perDate.length() < 9) {
            System.out.println("Bad birth date!");
            perDate = "1000-10-10";
        }
        System.out.println("Enter the gender (M, F): ");
        String gender = BasicActionsBook.bufferedReader.readLine().toUpperCase();
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Bad gender!");
            gender = "[no data]";
        }
        System.out.println("Enter the number: ");
        String perPhoneNumber = BasicActionsBook.bufferedReader.readLine();
        BasicActionsBook.contacts.add(new Person(perPhoneNumber, perName, perSurname, perDate, gender));
        System.out.println("The record added.");
        System.out.println();
    }

    @Override
    public void showInfo() {
        String[] dateCreate = getCreateDate().toString().split("\\.");
        String[] dateLastEdit = getLastEdit().toString().split("\\.");
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        if (birthday.toString().equals("1000-10-10")) {
            System.out.println("Birth date: [no data]");
        } else {
            System.out.println("Birth date: " + birthday);
        }
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + new StringBuilder(dateCreate[0]).delete(16, 19));
        System.out.println("Time last edit: " + new StringBuilder(dateLastEdit[0]).delete(16, 19) + "\n");
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return getName() + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(String date) {
        this.birthday = LocalDate.parse(date);
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

}
