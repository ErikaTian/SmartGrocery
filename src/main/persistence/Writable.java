package persistence;

import org.json.JSONObject;

// this interface is inspired by JsonSerializationDemo project
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
