package persistence;

import model.Account;
import model.AccountMap;
import model.Cart;
import model.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// this class is inspired by JsonSerializationDemo project
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads saved a collection of accounts from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a collection of accounts from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountMap read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        AccountMap accounts = null;
        accounts = parseAccountMap(jsonObject);
        return accounts;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses a collection of accounts from JSON object and returns it
    private AccountMap parseAccountMap(JSONObject jsonObject) {
        AccountMap accounts = new AccountMap();
        Set<String> names = jsonObject.keySet();
        for (String s : names) {
            JSONObject jsonAccount = jsonObject.getJSONObject(s);
            Account a = parseAccount(jsonAccount);
            accounts.addAccount(s, a);
        }
        return accounts;
    }

    // EFFECTS: parses the account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double balance = jsonObject.getDouble("balance");
        JSONObject jsonCart = jsonObject.getJSONObject("cart");

        Cart cart = parseCart(jsonCart); // a helper fn
        Account account = new Account(name, balance, cart);
        return account;
    }

    // EFFECTS: parses the cart from JSON object and returns it
    private Cart parseCart(JSONObject jsonObject) {
        List<Product> wishlist = parseWishlist(jsonObject); // a helper fn
        List<Integer> quantityList = parseQuantities(jsonObject); // a helper fn
        Cart cart = new Cart(wishlist, quantityList);
        return cart;
    }

    // EFFECTS: parses the wishlist of products from JSON object and returns it
    private List<Product> parseWishlist(JSONObject jsonObject) {
        List<Product> wishlist = new ArrayList<>();
        JSONArray jsonData = jsonObject.getJSONArray("wishlist");
        for (Object p : jsonData) {
            JSONObject product = (JSONObject) p; // cast p from object to JSONObject
            wishlist.add(parseProduct(product));
        }
        return wishlist;
    }

    // EFFECTS: parses a product from JSON object and returns it
    private Product parseProduct(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String bb = jsonObject.getString("bb");
        Date date = new Date(Integer.valueOf(bb));
        Product p = new Product(name, price, date);
        return p;
    }

    // EFFECTS: parses the list of quantities from JSON object and returns it
    private List<Integer> parseQuantities(JSONObject jsonObject) {
        List<Integer> quantityList = new ArrayList<>();
        JSONArray jsonData = jsonObject.getJSONArray("quantityList");
        for (Object o : jsonData) {
            int quantity = Integer.parseInt(o.toString());
            quantityList.add(quantity);
        }
        return quantityList;
    }
}
