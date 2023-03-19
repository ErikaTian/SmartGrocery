package ui;

import model.Account;
import model.AccountMap;
import model.Product;
import model.ProductList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.awt.Color.*;

public class SmartGroceryGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/account.json";
    private Product product1;
    private Product product2;
    private Product product3;
    private Account account;
    private AccountMap accounts;

    private JPanel mainMenu;
    private JPanel productLists;
    private JPanel accountInfo;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;

    private JLabel label;

    // Creates a new JFrame with a menu for the grocery store
    public SmartGroceryGUI() {
        super("SmartGroceryGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        initProductList();
        initAccount();
        initMenu();
        addLabel(label, mainMenu);
        initButtons();
        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);
        addButtonEvent();
    }

    // MODIFIES: this
    // EFFECTS: displays the list of all products in the grocery store
    private void initProductList() {
        product1 = new Product("Apple", 5.2, new Date(20230328));
        product2 = new Product("Purdy's Chocolate Box", 35.98, new Date(20240615));
        product3 = new Product("Elephant Instant Noodles", 3.82, new Date(20240126));
    }

    // MODIFIES: this
    // EFFECTS: initializes account of this user and retrieves the collection of accounts in the grocery store
    private void initAccount() {
        account = new Account("Erika");
        accounts = new AccountMap();
    }

    // MODIFIES: this
    // EFFECTS: displays the main menu in the grocery store
    private void initMenu() {
        mainMenu = new JPanel();
        add(mainMenu, BorderLayout.CENTER);
        mainMenu.setBorder(BorderFactory.createBevelBorder(100));
        mainMenu.setBackground(Color.lightGray);
//        mainMenu.setForeground(Color.white);
        mainMenu.setLayout(new GridLayout(7,1));
        mainMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a button to the main menu
    public void addLabel(JLabel label, JPanel panel) {
        label = new JLabel();
        label.setText("Welcome to SmartGroceryApp!");
        label.setFont(new Font("Times New Roman", Font.ITALIC, 20));
//        label.setBackground(Color.black);
        label.setForeground(Color.black);
        panel.add(label);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons on the main menu
    public void initButtons() {
        button1 = new JButton("View all items in the grocery store");
        button2 = new JButton("View the account balance");
        button3 = new JButton("View the shopping wishlist in the cart");
        button4 = new JButton("Load my account");
        button5 = new JButton("Save my account");
        button6 = new JButton("Quit");
        button7 = new JButton("Return to main menu");
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
    // EFFECTS: adds a button to sub-menu
    public void modifySmallButton(JButton button, JPanel panel) {
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(200,750));
        button.setBounds(10,100, getSize().width, getSize().height);
        button.setMargin(new Insets(10,10,10,10));
        button.setBackground(Color.BLACK);
        button.setForeground(red);

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
        button2.setActionCommand("View the account balance");
        button3.addActionListener(this);
        button3.setActionCommand("View the shopping wishlist in the cart");
        button4.addActionListener(this);
        button4.setActionCommand("Load my account");
        button5.addActionListener(this);
        button5.setActionCommand("Save my account");
        button6.addActionListener(this);
        button6.setActionCommand("Quit");
        button7.addActionListener(this);
        button7.setActionCommand("Return to main menu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View all items in the grocery store")) {
            displayProductList();
        } else if (e.getActionCommand().equals("View the account balance")) {
            //
        } else if (e.getActionCommand().equals("View the shopping wishlist in the cart")) {
            viewAccount();
        } else if (e.getActionCommand().equals("Load my account")) {
            //
        } else if (e.getActionCommand().equals("Save my account")) {
            //
        } else if (e.getActionCommand().equals("Quit")) {
            //
        } else if (e.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all products in the grocery store on the screen
    private void displayProductList() {
        productLists = new JPanel();
        add(productLists, BorderLayout.CENTER);
        productLists.setBorder(BorderFactory.createBevelBorder(100));
        productLists.setBackground(white);
        productLists.setLayout(new GridLayout(10,1));
        productLists.setSize(500,400);
        productLists.setVisible(true);
        mainMenu.setVisible(false);

        DefaultListModel<String> myList = new DefaultListModel<>();
        myList.addElement(product1.toString());
        myList.addElement(product2.toString());
        myList.addElement(product3.toString());
        JList<String> list = new JList<>(myList);
        productLists.add(list);

//        productListsMenu.add(btn1);
//        productListsMenu.add(btn2);

        JButton btn1 = new JButton("Add to cart");
        modifySmallButton(btn1, productLists);
        modifySmallButton(button7, productLists);
    }

    // MODIFIES: this
    // EFFECTS: displays the wishlist in the cart on the screen
    private void viewAccount() {
        accountInfo = new JPanel();
        add(accountInfo, BorderLayout.CENTER);
        accountInfo.setBorder(BorderFactory.createBevelBorder(100));
        accountInfo.setBackground(white);
        accountInfo.setLayout(new GridLayout(10,10));
        accountInfo.setSize(500,400);
        accountInfo.setVisible(true);
        mainMenu.setVisible(false);

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
        accountInfo.add(list);

        modifySmallButton(button7, accountInfo);
    }

    // EFFECTS: returns to the main menu
    private void returnToMainMenu() {
        mainMenu.setVisible(true);
        if (productLists.isVisible()) {
            productLists.setVisible(false);
        }
        if (accountInfo.isVisible()) {
            accountInfo.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SmartGroceryGUI();
    }
}
