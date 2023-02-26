package model;

import java.util.List;

// An interface to manage a list of items
public interface ListManager {

    // EFFECTS: returns a string representation of an item
    int sizeList();

    // EFFECTS: prints the information of all items
    List<String> getFullList();

    // Add more behaviour in the future
}
