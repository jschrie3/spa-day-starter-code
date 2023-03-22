package org.launchcode.spaday.models;

// TODO 14.6.1(4): Create a User class within org.launchcode.spaday.models
public class User {

    // TODO 14.6.2: Your User class should have a few private fields with getters and setters: username, email, password.
    // FIELDS
    private static int nextId = 1001;
    private int id;

    private String username;
    private String email;
    private String password;

    // CONSTRUCTOR

    // Base Constructor: create a constructor to handle the user id
    public User(){
        this.id = nextId;
        nextId++;
    }

    public User(String username, String email, String password) {
        // initialize userId by calling base constructor
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }


    // GETTERS AND SETTERS
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
