package ru.home.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.model.User;
import ru.home.respository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {

      userRepository.save(user);

    }

    @Override
    public void editUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public List<User> getAllUsers(){

       List<User> users =  userRepository.findAll();
       List<User> listUser = new ArrayList<>();

       users.forEach(user -> listUser.add(user));

       return listUser;
    }
}
