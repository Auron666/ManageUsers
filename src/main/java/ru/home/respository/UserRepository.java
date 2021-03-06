package ru.home.respository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
    List<User> findByFirstnameStartsWithIgnoreCase(String firstName);
}
