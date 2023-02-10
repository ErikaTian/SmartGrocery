package ui;

import model.Account;
import model.Product;
import model.ProductList;

import java.util.Date;
import java.util.Scanner;

// Smart Grocery shopping application
public class SmartGroceryApp {
    private Account account;
    private Scanner input;
//
//    private Product product1;
//    private Product product2;
//    private Product product3;
//    private ProductList pl;
    //Why cannot define above as fields? Run it: NullpointerException

    // EFFECTS: runs the smart grocery application
    public SmartGroceryApp() {
        runSmartGrocery();
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    private void runSmartGrocery() {
        boolean keepGoing = true;
        String command = null;

        initAccount();
//        initList();
        // not sure if I need to call it here...

        System.out.println("Welcome to SmartGrocery!");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            System.out.println(command);
            command = command.toLowerCase();

            if (command.equals("4")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for visiting SmartGrocery. Have a nice day!");
    }


//    // EFFECTS: ask for username
//    private void askUserName() {
//        System.out.println("\nEnter your username:");
//    }
//
//    // EFFECTS: ask for password
//    private void askPassword() {
//        System.out.println("\nEnter your password:");
//    }

    // MODIFIES: this
    // EFFECTS: initializes account
    private void initAccount() {
        account = new Account("Erika", 120, new ProductList());
        // if comment out below two lines, will get nullpointerException. Why?
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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
        System.out.println("\t2 -> view my balance");
        System.out.println("\t3 -> view the shopping list in my cart");
        System.out.println("\t4 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewList();
            addProductToCart();
        } else if (command.equals("2")) {
            viewBalance();
        } else if (command.equals("3")) {
            viewCartList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: print the list of all products in the grocery store
    private void viewList() {
        ProductList pl = initList();
        System.out.println(pl.getFullList());
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: search a product by name and add it to cart if founded
    private void addProductToCart() {
        ProductList pl = initList();
        String name = "";
        System.out.println("Search by product name:");
        name = input.next();
//        String result;
//        selection = selection.toLowerCase();  // no need

        if (!(pl.findProduct(name) == null)) {
            account.getCartList().addProduct(pl.findProduct(name));
        }
//        System.out.print(......);
        // cannot put "System.out.print(result)" after "return statement" !!!
    }

    // EFFECTS: print the balance of account
    private void viewBalance() {
        double balance = account.getBalance();
        System.out.printf("Balance: $%.2f\n", balance);
    }

    // EFFECTS: print the shopping list in the cart of account
    private void viewCartList() {
        ProductList cartList = account.getCartList();
        // Below two lines are for testing
//        Product product1 = new Product("Apple", 5.2, new Date(20230328));
//        cartList.addProduct(product1);
        System.out.println(cartList.getFullList());

    }

//    // EFFECTS: prints all information of a product
//    private void printProductInfo(Product product) {
//        System.out.println(product.getName() + product.getPrice() + product.getBB());
//    }
}