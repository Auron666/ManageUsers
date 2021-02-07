package ru.home.service;

import ru.home.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    void editUser();
    void deleteUser();
    List<User> getAllUsers();


}
