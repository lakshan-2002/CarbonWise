package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.annotation.AuthRequired;
import com.lakshan.carbonwise.annotation.CurrentUser;
import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.service.JWTService;
import com.lakshan.carbonwise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;

    @Autowired
    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userService.addNewUser(user);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User inputUser){
//        User dbUser = userService.getUserByEmail(inputUser.getEmail());
//
//        if (dbUser != null && dbUser.getPassword().equals(DigestUtils.sha256Hex(inputUser.getPassword())))
//            return ResponseEntity.ok(dbUser);
//        else
//            return ResponseEntity.status(401).body("Invalid email or password");
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User body){
        var user = userService.getUserByEmailPassword(body.getEmail(), body.getPassword());

        if (user != null) {
            String token = jwtService.generateToken(user.getEmail(), user.getName(), user.getBusiness().getName());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Login failed");
//        if (dbUser != null && dbUser.getPassword().equals(DigestUtils.sha256Hex(inputUser.getPassword())))
//            return ResponseEntity.ok(dbUser);
//        else
//            return ResponseEntity.status(401).body("Invalid email or password");
    }

//    @GetMapping("{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email);
//    }

    @GetMapping
    public List<User> getUsers() {
       return userService.getAllUsers();
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}
