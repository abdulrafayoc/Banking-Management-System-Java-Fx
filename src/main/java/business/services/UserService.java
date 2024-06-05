package business.services;

import business.models.*;

import persistence.repository.*;

public class UserService {
    private final UserRepositoryImpl userRepository;

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String phoneNumber, String name, String username, String password, UserRole role) {
        User user = new User(generateUserId(), phoneNumber, name, username, password, role);
        return userRepository.save(user);
    }

    public User authenticateUser(String username, String password) {
        // You might want to add more robust authentication logic here
        User user = userRepository.findByUsername(username);
        if (user != null && user.authenticate(username, password)) {
            return user;
        }
        return null; // Or throw an exception for invalid credentials
    }

    // ... other user-related methods (findById, updateUser, etc.)

    private int generateUserId() {
        // Logic to generate a unique user ID
        return 0; // Placeholder
    }
}