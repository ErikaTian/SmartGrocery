package model;

import model.exceptions.DuplicateAccountException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;

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

    public void addAccount(String name, Account account) throws DuplicateAccountException {
        if (hasAccountWithName(name)) {
            throw new DuplicateAccountException();
        }
        accounts.put(name, account);
    }

    public boolean hasAccountWithName(String name) {
        Account a = null;
        a = getAccountByName(name);
        return a != null;
    }

    @Override
    public JSONObject toJson() {
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONObject json = new JSONObject();
        String key;
        for (Account a : accounts) {
            jsonArray2.put(a.toJson());
            key = a.getName();
        }
        json.put("name", name);
        json.put("balance", balance);
        return json;
    }
}
