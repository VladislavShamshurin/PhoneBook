package ru.vlad.contacts;

import java.io.IOException;
import java.time.LocalDateTime;

public class Organization extends Contact {
    private String address;
    private final LocalDateTime createDate;
    private LocalDateTime lastEdit;

    public Organization(String phoneNumber, String name, String address) {
        super(phoneNumber, name);
        this.address = address;
        this.createDate = LocalDateTime.now();
        this.lastEdit = this.createDate;
    }

   public static void addContact() throws IOException {
        System.out.println("Enter the organization name: ");
        String orgName = MainLogic.bufferedReader.readLine();
        System.out.println("Enter the address: ");
        String orgAddress = MainLogic.bufferedReader.readLine();
        System.out.println("Enter the number: ");
        String orgNumber = MainLogic.bufferedReader.readLine();
        MainLogic.contacts.add(new Organization(orgNumber, orgName, orgAddress));
        System.out.println("The record added.\n");
    }

    @Override
    public void editContact() throws IOException {
        System.out.println("Select a field (name, address, number): ");
        String field = MainLogic.bufferedReader.readLine();
        switch (field.toLowerCase()) {
            case "name":
                System.out.println("Enter name: ");
                String newName = MainLogic.bufferedReader.readLine();
                setName(newName);
                System.out.println("Saved");
                setLastEdit(LocalDateTime.now());
                System.out.println();
                break;
            case "address":
                System.out.println("Enter address: ");
                String newAddress = MainLogic.bufferedReader.readLine();
                setAddress(newAddress);
                System.out.println("Saved");
                setLastEdit(LocalDateTime.now());
                System.out.println();
                break;
            case "number":
                System.out.println("Enter number: ");
                String newNumber = MainLogic.bufferedReader.readLine();
                setPhoneNumber(newNumber);
                System.out.println("Saved");
                setLastEdit(LocalDateTime.now());
                System.out.println();
                break;
        }
    }

    @Override
    public void showInfo() {
        String[] dateCreate = getCreateDate().toString().split("\\.");
        String[] dateLastEdit = getLastEdit().toString().split("\\.");
        System.out.printf("Organization name: %s\n" +
                "Address: %s\n" +
                "Number: %s%n", getName(), getAddress(), getPhoneNumber());
        System.out.println("Time created: " + new StringBuilder(dateCreate[0]).delete(16, 19));
        System.out.println("Time last edit: " + new StringBuilder(dateLastEdit[0]).delete(16, 19) + "\n");
    }

    public String getFullName() {
        return getName();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }


    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
