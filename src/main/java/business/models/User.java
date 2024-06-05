package business.models;

public class User {
    private int userId;
    private String phoneNumber;
    private String name;
    private String username;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    private UserRole role; // Enum for roles (ADMIN, TELLER, CUSTOMER)

    public User(int userId, String phoneNumber, String name, String username, String password, UserRole role) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // ... other methods as needed
}

