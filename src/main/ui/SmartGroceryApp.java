package ui;

import model.*;
import model.exceptions.NonPositiveException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Smart Grocery shopping application
// REQUIRES: if this grocery shop doesn't have any existing user accounts, account.json
//           needs an empty {} in it s.t. it's a valid starting file for JsonReader to read;
//           then any user can start to use SmartGroceryApp

public class SmartGroceryApp {
    private static final String JSON_STORE = "./data/account.json";
    private Account account;
    private AccountMap accounts;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs a set of accounts in the grocery store and runs the smart grocery application
    public SmartGroceryApp() throws FileNotFoundException {
        input = new Scanner(System.in); // give input
        input.useDelimiter("\n");  //separate things by new lines

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initAccount();
        runSmartGrocery();
    }

    // MODIFIES: this
    // EFFECTS: initializes account of this user
    private void initAccount() {
        // Ask for user's name
        System.out.println("Enter your name:");
        String name = input.next();
        System.out.println("Hi, " + name + "! Welcome to SmartGrocery!");
        account = new Account(name);
        accounts = new AccountMap();

        try {
            accounts = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    private void runSmartGrocery() {
        boolean keepGoing = true;
        String command;

        initList();
//      Note: initAccountMap(); move initAccountMap() to SmartGroceryApp()

        while (keepGoing) {
            displayMenu();
            command = input.next();
            System.out.println(command);
            command = command.toLowerCase();

            if (command.equals("6")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for visiting SmartGrocery. Have a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a list of products in the grocery store
    private ProductList initList() {
        Product product1 = new Product("Apple", 5.2, new Date(20230328));
        Product product2 = new Product("Purdy's Chocolate Box", 35.98, new Date(20240615));
        Product product3 = new Product("Elephant Instant Noodles", 3.82, new Date(20240126));
        ProductList pl = new ProductList();
        pl.addProduct(product1);
        pl.addProduct(product2);
        pl.addProduct(product3);
        return pl;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> view all items in the grocery store");
        System.out.println("\t2 -> view the account balance");
        System.out.println("\t3 -> view the shopping wishlist in the cart");
        System.out.println("\t4 -> Load my account");
        System.out.println("\t5 -> Save my account");
        System.out.println("\t6 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewList();
            addProduct();
        } else if (command.equals("2")) {
            viewBalance();
        } else if (command.equals("3")) {
            viewCartList();
        } else if (command.equals("4")) {  //want to get rid of "4" "5"
            loadAccount();
        } else if (command.equals("5")) { //let app ask user directly
            saveAccount();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: print the list of all products in the grocery store
    private void viewList() {
        ProductList pl = initList();
        System.out.println(pl.getFullList());
    }

    // MODIFIES: this
    // EFFECTS: see details of a product by searching its name; if founded,
    //          offer the option to add it (with a quantity) in the cart
    private void addProduct() {
        ProductList pl = initList();
        String name;
        System.out.println("Search by product name:");
        name = input.next();
        if (!(pl.findProduct(name) == null)) {
            System.out.println(pl.findProduct(name).toString());
            System.out.println("Do you want to add this item to your cart?");
            String addItem;
            addItem = input.next();
            addItem = addItem.toLowerCase();
            if (addItem.equals("yes")) {
                System.out.println("Enter the quantity:");
                int quantity = input.nextInt();
                account.addProductToCart(pl.findProduct(name), quantity);
                System.out.println("Ok, it has been added!");
            } else if (!addItem.equals("no")) {
                System.out.println("Input not valid... here is the main menu:");
            }
        } else {
            System.out.println("Cannot find this item... ");
        }
    }

    // MODIFIES: this
    // EFFECTS: print the balance of account; offer the option to top up the balance
    private void viewBalance() {
        double balance = account.getBalance();
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println("Do you want to top up?");
        String topUp;
        topUp = input.next();
        topUp = topUp.toLowerCase();
        if (topUp.equals("yes")) {
            System.out.println("Enter the amount:");
            double amount = input.nextDouble();
            try {
                account.topUpBalance(amount);
            } catch (NonPositiveException e) {
                System.out.println("Input must be a positive value to be added ...\n");
            }
        } else if (!topUp.equals("no")) {
            System.out.println("Input not valid... here is the main menu:");
        }
    }

    // MODIFIES: this
    // EFFECTS: print the wishlist in the cart of user account;
    //          offer the option to remove any product from the cart
    private void viewCartList() {
        Cart cart = account.getCart();
        if (cart.sizeWishlist() == 0) {
            System.out.println(cart.getFullList());
        } else {
            System.out.println(cart.getFullList());
            System.out.println("Do you want to remove any item from the cart?");
            String remove;
            remove = input.next();
            remove = remove.toLowerCase();
            if (remove.equals("yes")) {
                System.out.println("Enter the product name:");
                String name = input.next();
                while (!cart.isProductInCart(name)) {
                    System.out.println("Product not found... check the spelling and re-enter the product name:");
                    name = input.next();
                }
                account.removeProductFromCart(name);
                System.out.println("Ok, it has been removed!");
            } else if (!remove.equals("no")) {
                System.out.println("Input not valid... here is the main menu:");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads account from file
    private void loadAccount() {
        if (accounts.hasAccountWithName(account.getName())) {
            account = accounts.getAccountByName(account.getName());
            System.out.println("Loaded " + account.getName() + "'account from " + JSON_STORE);
        } else {
            System.out.println("You have no existing account. Remember to save your account before leaving today!");
        }

//        Note: App already reads the json file at the beginning
//        try {
//            accounts = jsonReader.read();
//            account = accounts.getAccountByName(account.getName());
//            System.out.println("Loaded " + account.getName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
    }

    // EFFECTS: saves the account to file
    private void saveAccount() {
        try {
            accounts.addAccount(account.getName(), account);

            jsonWriter.open();
            jsonWriter.write(accounts);
            jsonWriter.close();
            System.out.println("Saved " + account.getName() + "'account to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}