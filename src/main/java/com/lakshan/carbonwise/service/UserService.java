package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByEmailPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, DigestUtils.sha256Hex(password));
    }

    public void updateUser(User user) {
        if(userRepository.existsById(user.getId()))
            userRepository.save(user);
        else
            throw new RuntimeException("User not found");
    }

    public void deleteUser(int id) {
        if(userRepository.existsById(id))
            userRepository.deleteById(id);
        else
            throw new RuntimeException("User not found");
    }
}
