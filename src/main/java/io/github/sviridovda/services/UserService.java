package io.github.sviridovda.services;

import io.github.sviridovda.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    void addUser(User user);
    void updateUser(long id, User user);
    void deleteUser(long id);
}
