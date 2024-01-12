import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManagementSystem {

    private static final String FILE_PATH = "contacts.txt";

    private static List<Contact> loadContacts() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            List<Contact> contacts = (List<Contact>) ois.readObject();
            ois.close();
            return contacts;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void saveContacts(List<Contact> contacts) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            oos.writeObject(contacts);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addContact(String name, String phone, String email) {
        List<Contact> contacts = loadContacts();
        contacts.add(new Contact(name, phone, email));
        saveContacts(contacts);
    }

    private static void viewContacts() {
        List<Contact> contacts = loadContacts();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    // Implement edit, delete, and search functions

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nContact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    addContact(name, phone, email);
                    break;
                case "2":
                    viewContacts();
                    break;
                case "3":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}