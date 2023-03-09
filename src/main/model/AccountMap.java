package model;

import model.exceptions.DuplicateAccountException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountMap implements Writable {
    private Map<String, Account> accounts;

    // EFFECTS: constructs a set of accounts with a name, and an account
    public AccountMap() {
        accounts = new HashMap<>();
    }

    // Write specifications for this class
    public Account getAccountByName(String name) {
        return accounts.get(name);
    }

    public void addAccount(String name, Account account) {
        if (!hasAccountWithName(name)) {
            accounts.put(name, account);
        }
    }

    public boolean hasAccountWithName(String name) {
        Account a = null;
        a = getAccountByName(name);
        return a != null;
    }

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
