package org.esteban.springboot.springmvc.app.services;

import org.esteban.springboot.springmvc.app.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void delete(Long id);

}
