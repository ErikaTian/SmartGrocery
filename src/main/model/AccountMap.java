package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Represents a collection of user accounts
public class AccountMap implements Writable {
    private Map<String, Account> accounts;

    // EFFECTS: constructs a collection of accounts with usernames as keys
    public AccountMap() {
        accounts = new HashMap<>();
    }

    // EFFECTS: returns an account by searching username in this collection
    public Account getAccountByName(String name) {
        return accounts.get(name);
    }

    // EFFECTS: returns the size of this collection
    public int getSize() {
        return accounts.size();
    }

    // MODIFIES: this
    // EFFECTS: if account doesn't exist in this collection, then put it with its username as key
    //          in this collection; otherwise, replace the existing account associated with username
    //          by (current) account; Notice ".put" function will achieve both goals
    public void addAccount(String name, Account account) {
        accounts.put(name, account);
    }

    // EFFECTS: returns true if user's account exists in this collection,
    public boolean hasAccountWithName(String name) {
        Account a = null;
        a = getAccountByName(name);
        return a != null;
    }

    // EFFECTS: convert AccountMap to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        Set<String> keySet = accounts.keySet();
        for (String s : keySet) {
            Account a = accounts.get(s);
            json.put(s, a.toJson());
        }
        return json;
    }
}
