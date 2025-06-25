package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        user = userService.getUserByEmail(user.getEmail());

        if (user != null && user.getPassword().equals(user.getPassword()))
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUser(id);
    }

//    @GetMapping("{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email);
//    }

    @GetMapping
    public List<User> getUsers() {
       return userService.getAllUsers();
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}
