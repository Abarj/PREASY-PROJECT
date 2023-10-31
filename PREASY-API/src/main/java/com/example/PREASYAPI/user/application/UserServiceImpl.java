package com.example.PREASYAPI.user.application;

import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.user.infrastructure.dto.input.UserInputDTO;
import com.example.PREASYAPI.user.infrastructure.dto.output.UserOutputDTO;
import com.example.PREASYAPI.user.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserOutputDTO addUser(UserInputDTO userInputDTO) {
        User user = new User(userInputDTO);
        userRepository.save(user);

        return new UserOutputDTO(user);
    }

    public List<UserOutputDTO> getAllUsers() {
        List<UserOutputDTO> userList = new ArrayList<>();
        userRepository.findAll().forEach(user -> userList.add(new UserOutputDTO(user)));

        return userList;
    }

    @Override
    public UserOutputDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna persona con el id: " + id));

        return new UserOutputDTO(user);
    }

    @Override
    public List<UserOutputDTO> getUserByName(String name) {
        List<UserOutputDTO> nameList = new ArrayList<>();
        userRepository.findByName(name).forEach(user -> nameList.add(new UserOutputDTO(user)));

        return nameList;
    }

    @Override
    public UserOutputDTO updateUser(Integer id, UserInputDTO personInputDto) throws RuntimeException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún username con el id: " + id));
        user.update(personInputDto);
        userRepository.save(user);

        return new UserOutputDTO(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún username con el id: " + id));
        userRepository.delete(user);
    }
}
