package com.example.PREASYAPI.user.infrastructure.controller;

import com.example.PREASYAPI.user.application.UserService;
import com.example.PREASYAPI.user.infrastructure.dto.input.UserInputDTO;
import com.example.PREASYAPI.user.infrastructure.dto.output.UserOutputDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutputDTO addUser(@Valid @RequestBody UserInputDTO personInputDTO) {
        return userService.addUser(personInputDTO);
    }

    @GetMapping
    public List<UserOutputDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserOutputDTO getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}")
    public List<UserOutputDTO> getUserByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @PutMapping("/{id}")
    public UserOutputDTO updateUser(@PathVariable("id") Integer id, @Valid @RequestBody UserInputDTO personInputDTO) {
        return userService.updateUser(id, personInputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}