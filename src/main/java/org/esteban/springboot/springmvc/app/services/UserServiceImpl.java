package org.esteban.springboot.springmvc.app.services;

import org.esteban.springboot.springmvc.app.models.User;
import org.esteban.springboot.springmvc.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

@Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
@Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
@Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
