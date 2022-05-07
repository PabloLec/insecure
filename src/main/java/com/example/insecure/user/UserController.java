package com.example.insecure.user;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService UserService;
    private final UserRepository UserRepository;

    @Autowired
    public UserController(UserService UserService, UserRepository UserRepository) {
        this.UserService = UserService;
        this.UserRepository = UserRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return UserService.getUsers();
    }

    @PostMapping(produces = "application/json")
    public String registerNewUser(@RequestBody User user) {
        UserService.addNewUser(user);

        User savedUser = UserRepository.findUserByEmailWithFail(user.getEmail());
        return new Gson().toJson(savedUser.getPassword());
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