package ui;

import model.Account;
import model.AccountMap;
import model.Product;
import model.ProductList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static java.awt.Color.*;

public class SmartGroceryGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/account.json";
    private Product product1;
    private Product product2;
    private Product product3;
    private ProductList pl;
    private Account account;
    private AccountMap accounts;

    private JPanel mainMenu;
    private JPanel productListsMenu;
    private JPanel cartMenu;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    private JLabel label;

    // Creates a new JFrame with a menu for the grocery store
    public SmartGroceryGUI() {
        super("SmartGroceryGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);

        initMenu();
        initButtons();
        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);

        initProductList();
    }

    // MODIFIES: this
    // EFFECTS: displays the main menu in the grocery store
    private void initMenu() {
        mainMenu = new JPanel();
        add(mainMenu, BorderLayout.CENTER);
        mainMenu.setBorder(BorderFactory.createBevelBorder(100));
        mainMenu.setBackground(lightGray);
        mainMenu.setLayout(new GridLayout(7,1));
        mainMenu.setVisible(true);
        label = new JLabel();
        label.setText("Welcome to SmartGroceryApp!");
        mainMenu.add(label,BorderLayout.BEFORE_LINE_BEGINS);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons on the main menu
    public void initButtons() {
        button1 = new JButton("view all items in the grocery store");
        button2 = new JButton("view the account balance");
        button3 = new JButton("view the shopping wishlist in the cart");
        button4 = new JButton("Load my account");
        button5 = new JButton("Save my account");
        button6 = new JButton("quit");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);

    }

    // MODIFIES: this
    // EFFECTS: adds a button to the main menu
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.setBackground(Color.blue);
        panel.add(button);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: displays the list of all products in the grocery store
    private void initProductList() {
        product1 = new Product("Apple", 5.2, new Date(20230328));
        product2 = new Product("Purdy's Chocolate Box", 35.98, new Date(20240615));
        product3 = new Product("Elephant Instant Noodles", 3.82, new Date(20240126));
    }

    // MODIFIES: this
    // EFFECTS: displays the list of products in the grocery store
    private void displayProductList() {
        productListsMenu = new JPanel(new GridLayout(10,1));
        add(productListsMenu, BorderLayout.CENTER);
        productListsMenu.setBorder(BorderFactory.createBevelBorder(100));
        productListsMenu.setBackground(lightGray);
        productListsMenu.setLayout(new GridLayout(1,10));
        productListsMenu.setVisible(true);

        DefaultListModel<String> myList = new DefaultListModel<>();
        myList.addElement(product1.toString());
        myList.addElement(product2.toString());
        myList.addElement(product3.toString());
        JList<String> list = new JList<>(myList);
        productListsMenu.add(list,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayProductList();
    }

    public static void main(String[] args) {
        new SmartGroceryGUI();
    }
}
