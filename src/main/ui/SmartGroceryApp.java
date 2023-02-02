package ui;

import model.Product;
import model.ProductList;

import java.util.Date;
import java.util.Scanner;

// Smart Grocery shopping application
public class SmartGroceryApp {
    private Product product1;
    private Product product2;
    private Product product3;
    private ProductList pl;
    private Scanner input;

    // EFFECTS: runs the smart grocery application
    public SmartGroceryApp() {
        runSmartGrocery();
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    private void runSmartGrocery() {
        boolean keepGoing = true;
        String command = null;

        init();

        System.out.println("Welcome to SmartGrocery!");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            System.out.println(command);
            command = command.toLowerCase();

            if (command.equals("4")) {
                keepGoing = false;
            } else {
                if (command.equals("1")) {
                    printList();
//                } else if (command.equals("2")) {
//                    addToCart();
//                } else if (command.equals("3")) {
//                    addReview();
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        }
        System.out.println("\nThank you for visiting SmartGrocery. Have a nice day!");
    }

    private void printList() {
    }

    // EFFECTS: ask for username
    private void askUserName() {
        System.out.println("\nEnter your username:");
    }

    // EFFECTS: ask for password
    private void askPassword() {
        System.out.println("\nEnter your password:");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> view the grocery list");
        System.out.println("\t2 -> add one product to your cart");
        System.out.println("\t3 -> purchase");
        System.out.println("\t4 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes a list of products
    private void init() {
        product1 = new Product("Apple", 5.2, 100,
                new Date(20230328));
        product2 = new Product("Purdy's Chocolate Box", 35.98, 35,
                new Date(20240615));
        product3 = new Product("Elephant Instant Noodles", 3.82, 17,
                new Date(20240126));
        pl.addProduct(product1);
        pl.addProduct(product2);
        pl.addProduct(product3);
    }


    // EFFECTS: prints all information of a product
    private void printProductInfo(Product product) {
        System.out.println(product.getName() + product.getPrice() + product.getQuantity() + product.getBB());
    }
}