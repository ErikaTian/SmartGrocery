package ui;

import model.Account;
import model.Cart;
import model.Product;
import model.ProductList;
import model.exceptions.NonPositiveException;

import java.util.Date;
import java.util.Scanner;

// Smart Grocery shopping application
public class SmartGroceryApp {
    private Account account;
    private Scanner input;

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
        initList();
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


    // Assume users have already logged into the account when starting the program
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
        account = new Account("Erika", 120, new Cart());
        // if comment out below two lines, will get nullpointerException. Why?
        input = new Scanner(System.in); // give input
        input.useDelimiter("\n");  //separate things by new lines
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
            addProduct();
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
    private void addProduct() {
        ProductList pl = initList();
        String name = "";
        System.out.println("Search by product name:");
        name = input.next();
//        String result;
//        selection = selection.toLowerCase();  // no need
        if (!(pl.findProduct(name) == null)) {
            System.out.println(pl.findProduct(name).toString());
            System.out.println("Do you want to add this item to your cart?");
            String addItem = "";
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

        //Notes:
//          account.getCartList().addProduct(pl.findProduct(name));
            //the above line doesn't work, as getCartList() only returns but
            //not change the cart-list object
//        System.out.println("");
        // cannot put "System.out.print(result)" after "return statement" !!!
    }

    // EFFECTS: print the balance of account
    private void viewBalance() {
        double balance = account.getBalance();
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println("Do you want to top up?");
        String topUp = "";
        topUp = input.next();
        topUp = topUp.toLowerCase();
        if (topUp.equals("yes")) {
            System.out.println("Enter the amount:");
            double amount = input.nextDouble();
//            try {
//                //
//            } catch (InputMismatchException e) {
//                System.out.println("Input not valid ...\n");
//            }
            try {
                account.topUpBalance(amount);
            } catch (NonPositiveException e) {
                System.out.println("Input must be a positive value to be added ...\n");
            }
        } else if (!topUp.equals("no")) {
            System.out.println("Input not valid... here is the main menu:");
        }
    }

    // EFFECTS: print the wishlist in the cart of user account
    private void viewCartList() {
        Cart cart = account.getCart();
        // Below two lines are for testing
//        Product product1 = new Product("Apple", 5.2, new Date(20230328));
//        cart.addProduct(product1);
        if (cart.sizeWishlist() == 0) {
            System.out.println(cart.getFullList());
        } else {
            System.out.println(cart.getFullList());
            System.out.println("Do you want to remove any item from the cart?");
            String remove = "";
            remove = input.next();
            remove = remove.toLowerCase();
            if (remove.equals("yes")) {
                System.out.println("Enter the product name:");
                String name = input.next();
                while ((cart.isProductInCart(name) == false)) {
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


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: remove a product from cart
//    private void removeProduct() {
//        write this method inside which method?
//    }


//    // EFFECTS: prints all information of a product
//    private void printProductInfo(Product product) {
//        System.out.println(product.getName() + product.getPrice() + product.getBB());
//    }
}