package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.awt.Color.*;
import static java.util.Objects.isNull;

// Smart Grocery shopping application GUI
// REQUIRES: if this grocery shop doesn't have any existing user accounts, account.json
//           needs an empty {} in it s.t. it's a valid starting file for JsonReader to read;
//           then any user can start to use SmartGroceryApp

public class SmartGroceryGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/account.json";
    private Product product1;
    private Product product2;
    private Product product3;
    private ProductList pl;
    private Account account;
    private AccountMap accounts;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainMenu;
    private JPanel productLists;
    private JPanel accountInfo;
    private JPanel accountPanel;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;

    private JList<String> productJList;
    private JLabel welcomeLabel;
    private JLabel nameLabel;
    private JTextField nameField;

    // MODIFIES: this
    // EFFECTS: Creates a new JFrame displaying the grocery store
    public SmartGroceryGUI() {
        super("SmartGroceryGUI");
        splashWindow();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initProductList();
        initButtons();
        initAccount();
        initMainMenu();
        addButtonEvent();
    }

    // EFFECTS: Displays a splash screen for 3 seconds
    private void splashWindow() {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(800, 600);
        splashScreen.setLocationRelativeTo(null);
        splashScreen.add(new JLabel(new ImageIcon("./data/Welcome to SmartGroceryApp.png")));
        splashScreen.setVisible(true);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            splashScreen.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the list of all products in the grocery store
    private void initProductList() {
        product1 = new Product("Apple", 5.2, new Date(20230328));
        product2 = new Product("Purdy's Chocolate Box", 35.98, new Date(20240615));
        product3 = new Product("Elephant Instant Noodle", 3.82, new Date(20240126));
        pl = new ProductList();
        pl.addProduct(product1);
        pl.addProduct(product2);
        pl.addProduct(product3);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons on the main menu
    public void initButtons() {
        button1 = new JButton("View all items in the grocery store");
        button2 = new JButton("Add to cart");
        button3 = new JButton("View the cart");
        button4 = new JButton("Load your account");
        button5 = new JButton("Save your account");
        button6 = new JButton("Quit");
        button7 = new JButton("Return to main menu");
        button8 = new JButton("Search");
        button9 = new JButton("Remove an item");
    }

    // MODIFIES: this
    // EFFECTS: initializes an account and retrieves the collection of accounts in the grocery store
    private void initAccount() {
        account = new Account("");
        accounts = new AccountMap();
        try {
            accounts = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the main menu in the grocery store
    private void initMainMenu() {
        mainMenu = new JPanel();
        add(mainMenu, BorderLayout.CENTER);
        mainMenu.setBorder(BorderFactory.createBevelBorder(100));
        mainMenu.setBackground(Color.lightGray);
        mainMenu.setLayout(new GridLayout(7,1));
        mainMenu.setVisible(true);

        addLabel(mainMenu);
        addButton(button1, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);
    }

    // MODIFIES: this
    // EFFECTS: adds a welcome label to the main menu
    public void addLabel(JPanel panel) {
        welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to SmartGroceryApp!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        welcomeLabel.setForeground(Color.black);
        panel.add(welcomeLabel);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: adds a button to the main menu
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.setBackground(Color.BLACK);
        button.setForeground(blue);
        panel.add(button);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: sets corresponding event to each button on the main menu
    public void addButtonEvent() {
        button1.addActionListener(this);
        button1.setActionCommand("View all items in the grocery store");
        button2.addActionListener(this);
        button2.setActionCommand("Add to cart");
        button3.addActionListener(this);
        button3.setActionCommand("View the cart");
        button4.addActionListener(this);
        button4.setActionCommand("Load your account");
        button5.addActionListener(this);
        button5.setActionCommand("Save your account");
        button6.addActionListener(this);
        button6.setActionCommand("Quit");
        button7.addActionListener(this);
        button7.setActionCommand("Return to main menu");
        button8.addActionListener(this);
        button8.setActionCommand("myAccount");
        button9.addActionListener(this);
        button9.setActionCommand("Remove an item");
    }

    // MODIFIES: this
    // EFFECTS: sets actions for all ActionListeners
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View all items in the grocery store")) {
            displayProductList();
        } else if (e.getActionCommand().equals("Add to cart")) {
            addItem();
        } else if (e.getActionCommand().equals("View the cart")) {
            viewAccount();
        } else if (e.getActionCommand().equals("Load your account")) {
            loadAccount();
        } else if (e.getActionCommand().equals("Save your account")) {
            saveAccount();
        } else if (e.getActionCommand().equals("Quit")) {
            setVisible(false);
            dispose();
            printLog();
            System.exit(0);
        } else if (e.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (e.getActionCommand().equals("myAccount")) {
            popUpWindow();
        } else if (e.getActionCommand().equals("Remove an item")) {
            removeItem();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all products in the grocery store on the screen
    private void displayProductList() {
        productLists = new JPanel();
        add(productLists, BorderLayout.CENTER);
        productLists.setBorder(BorderFactory.createBevelBorder(100));
        productLists.setBackground(white);
        productLists.setLayout(new FlowLayout());
        productLists.setSize(500,400);
        productLists.setVisible(true);
        mainMenu.setVisible(false);

        DefaultListModel<String> myList = new DefaultListModel<>();
        myList.addElement(product1.toString());
        myList.addElement(product2.toString());
        myList.addElement(product3.toString());
        productJList = new JList<>(myList);
        productLists.add(productJList);

        addButton(button2, productLists);
        addButton(button7, productLists);
    }

    // MODIFIES: this
    // EFFECTS: pops up new window and asks the quantity for selected item
    private void addItem() {
        String selectedProduct = productJList.getSelectedValue();
        String kept = selectedProduct.substring(0, selectedProduct.indexOf(","));
        String answer = JOptionPane.showInputDialog("Enter the quantity: ");
        int quantity = Integer.parseInt(answer);
        account.addProductToCart(pl.findProduct(kept), quantity);
    }

    // MODIFIES: this
    // EFFECTS: displays account information with a wishlist in the cart on the screen
    private void viewAccount() {
        accountInfo = new JPanel();
        add(accountInfo, BorderLayout.CENTER);
        accountInfo.setBorder(BorderFactory.createBevelBorder(100));
        accountInfo.setBackground(white);
        accountInfo.setLayout(new FlowLayout());
        accountInfo.setSize(500,400);
        accountInfo.setVisible(true);
        mainMenu.setVisible(false);

        JList<String> account = updateAccount();
        accountInfo.add(account);

        addButton(button9, accountInfo);
        addButton(button7, accountInfo);
    }

    // MODIFIES: this
    // EFFECTS: updates account information
    private JList<String> updateAccount() {
        DefaultListModel<String> myAccount = new DefaultListModel<>();
        myAccount.addElement("Name: " + account.getName());
        myAccount.addElement("Balance: " + String.format("%.2f", account.getBalance()));
        List<String> fullList = account.getCart().getFullList();
        String result = "";
        for (String s : fullList) {
            result = result + "\n" + s;
        }
        result = "Cart: " + result;
        myAccount.addElement(result);
        JList<String> list = new JList<>(myAccount);
        return list;
    }

    // MODIFIES: this
    // EFFECTS: pops up new window, asks which item to remove and removes the item
    private void removeItem() {
        String removeName = JOptionPane.showInputDialog("Remove an item (name): ");
        if (!account.getCart().isProductInCart(removeName)) {
            String s1 = "Product not found... please check the spelling!";
            JOptionPane.showMessageDialog(null, s1, "Reminder", JOptionPane.WARNING_MESSAGE);
        } else {
            account.removeProductFromCart(removeName);
            String s2 = "Ok, the product has been removed!";
            JOptionPane.showMessageDialog(null, s2, "Reminder", JOptionPane.PLAIN_MESSAGE);
            accountInfo.setVisible(false);
            viewAccount();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads existing account for this user from data file
    private void loadAccount() {
        accountPanel = new JPanel();
        add(accountPanel, BorderLayout.CENTER);
        accountPanel.setBorder(BorderFactory.createBevelBorder(100));
        accountPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        accountPanel.setLayout(new FlowLayout());
        accountPanel.setVisible(true);
        mainMenu.setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);

        nameLabel = new JLabel("Enter your name");
        nameField = new JTextField(5);
        accountPanel.add(nameLabel);
        accountPanel.add(nameField);
        accountPanel.add(button8);
        accountPanel.add(button7);
    }

    // MODIFIES: this
    // EFFECTS: pops up new window and displays messages
    private void popUpWindow() {
        account = new Account(nameField.getText());
        if (accounts.hasAccountWithName(account.getName())) {
            account = accounts.getAccountByName(account.getName());
            String s1 = "Loaded " + account.getName() + "'account from " + JSON_STORE;
            JOptionPane.showMessageDialog(null, s1, "Reminder", JOptionPane.PLAIN_MESSAGE);
        } else {
            String s2 = "You have no existing account. Remember to save your account before leaving today!";
            JOptionPane.showMessageDialog(null, s2, "Reminder", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the account to data file
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

    // MODIFIES: this
    // EFFECTS: returns to the main menu
    private void returnToMainMenu() {
        mainMenu.setVisible(true);
        if (!isNull(productLists) && productLists.isVisible()) {
            productLists.setVisible(false);
        }
        if (!isNull(accountInfo) && accountInfo.isVisible()) {
            accountInfo.setVisible(false);
        }
        if (!isNull(accountPanel) && accountPanel.isVisible()) {
            accountPanel.setVisible(false);
        }
    }

    // EFFECTS: prints log
    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: runs SmartGroceryGUI class
    public static void main(String[] args) {
        new SmartGroceryGUI();
    }
}
