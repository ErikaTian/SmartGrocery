package persistence;

import model.Account;
import model.Cart;
import model.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

// this class is inspired by JsonSerializationDemo project
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads saved accounts from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses the account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double balance = jsonObject.getDouble("balance");
        JSONObject jsonCart = jsonObject.getJSONObject("cart");;
        Cart cart = parseCart(jsonCart); // a helper fn
        Account account = new Account(name, balance, cart);
        return account;
    }

    // EFFECTS: parses the cart from JSON object and returns it
    private Cart parseCart(JSONObject jsonObject) {
//        JSONObject jsonData1 = jsonObject.getJSONObject("wishlist");
//        JSONObject jsonData2 = jsonObject.getJSONObject("quantityList");
        List<Product> wishlist = parseWishlist(jsonObject); // a helper fn
        List<Integer> quantityList = parseQuantities(jsonObject); // a helper fn
        Cart cart = new Cart(wishlist, quantityList);
        return cart;
    }

    // EFFECTS: parses the wishlist of products from JSON object and returns it
    private List<Product> parseWishlist(JSONObject jsonObject) {
        List<Product> wishlist = new ArrayList<>();
        // Note: original
//        JSONObject jsonO = jsonObject.getJSONObject("cart");
//        JSONArray jsonData = jsonO.getJSONArray("wishlist");
        JSONArray jsonData = jsonObject.getJSONArray("wishlist");
        //Note: below line doesn't work, why?
        //Prof: didn't find "cart.wishlist" is a valid key in Json
//        JSONArray jsonData = jsonObject.getJSONArray("cart.wishlist");
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
        // need another function to parse bb to date (20230228)???
        //Note: below are notes for brainstorming
//        String s1 = bb.substring(0,3);
//        String s2 = bb.substring(4,5);
//        String s3 = bb.substring(6,7);  // no need
//        new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Integer.valueOf(bb));
        // not sure if this approach is too trivial (not systematical)
        Product p = new Product(name, price, date);
        return p;
    }

    // EFFECTS: parses the list of quantities from JSON object and returns it
    private List<Integer> parseQuantities(JSONObject jsonObject) {
        List<Integer> quantityList = new ArrayList<>();
        JSONArray jsonData = jsonObject.getJSONArray("quantityList");
        for (Object o: jsonData) {
//            JSONObject quantity = (JSONObject) o; // no need
            int quantity = Integer.parseInt(o.toString());
            quantityList.add(quantity);
        }
        return quantityList;
    }

}
