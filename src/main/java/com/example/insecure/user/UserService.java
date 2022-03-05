package com.example.insecure.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public List<User> getUsers() {
        return UserRepository.findAll();
    }

    public User getUser(Long id) {
        return UserRepository.findById(id).get();
    }


    public void addNewUser(User User) {
        Optional<User> UserOptional = UserRepository.findUserByEmail(User.getEmail());
        if (UserOptional.isPresent()) {
            throw new IllegalArgumentException("User with email " + User.getEmail() + " already exists");
        }
        UserRepository.save(User);
    }

    public void deleteUser(Long id) {
        if (!UserRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        UserRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long id, String name, String email) {
        if (!UserRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        Optional<User> UserOptional = UserRepository.findUserByEmail(email);
        if (UserOptional.isPresent()) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        User User = UserRepository.findById(id).get();
        if (name != null && name.length() > 0) {
            User.setName(name);
        }
        if (email != null && email.length() > 0) {
            User.setEmail(email);
        }
    }
}