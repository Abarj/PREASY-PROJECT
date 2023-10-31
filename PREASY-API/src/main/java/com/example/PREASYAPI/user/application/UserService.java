package com.example.PREASYAPI.user.application;

import com.example.PREASYAPI.user.infrastructure.dto.input.UserInputDTO;
import com.example.PREASYAPI.user.infrastructure.dto.output.UserOutputDTO;

import java.util.List;

public interface UserService {
    UserOutputDTO addUser(UserInputDTO userInputDTO);
    List<UserOutputDTO> getAllUsers();
    UserOutputDTO getUserById(Integer id);
    List<UserOutputDTO> getUserByName(String name);
    UserOutputDTO updateUser(Integer id, UserInputDTO userInputDTO);
    void deleteUser(Integer id);
}