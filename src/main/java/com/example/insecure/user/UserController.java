package com.example.insecure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService UserService;

    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping
    public List<User> getUsers() {
        return UserService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        UserService.addNewUser(user);
    }

    @DeleteMapping(path = "{UserId}")
    public void deleteUser(@PathVariable("UserId") Long id) {
        UserService.deleteUser(id);
    }

    @PutMapping(path = "{UserId}")
    public void updateUser(@PathVariable("UserId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        UserService.updateUser(id, name, email);
    }

}