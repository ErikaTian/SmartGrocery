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

    // EFFECTS: returns an account by searching username
    public Account getAccountByName(String name) {
        return accounts.get(name);
    }

    // MODIFIES: this
    // EFFECTS: returns an account by searching username
    public void addAccount(String name, Account account) {
        accounts.put(name, account);
// Note: there is no need to check if accounts have this account, because we will do either
//       (1) add this account to accounts
//       (2) replace the old account in accounts with this account using the same key
//       both cases use accounts.put(name, account);
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
