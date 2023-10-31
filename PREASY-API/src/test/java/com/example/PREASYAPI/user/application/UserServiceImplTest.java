package com.example.PREASYAPI.user.application;

import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.user.infrastructure.dto.input.UserInputDTO;
import com.example.PREASYAPI.user.infrastructure.dto.output.UserOutputDTO;
import com.example.PREASYAPI.user.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    User user;
    UserInputDTO userInputDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userInputDTO = new UserInputDTO();
        userInputDTO.setUsername("testUser");
        userInputDTO.setPassword("testPassword");
        userInputDTO.setName("testName");
        userInputDTO.setSurname("testSurname");
        userInputDTO.setCompanyEmail("test@company.com");
        userInputDTO.setPersonalEmail("test@personal.com");
        userInputDTO.setCity("testCity");
        userInputDTO.setActive(true);
        userInputDTO.setCreateDate(new Date());

        user = new User(userInputDTO);
    }

    @Test
    public void testAddUserWhenValidUserInputThenReturnUserOutput() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserOutputDTO userOutputDTO = userService.addUser(userInputDTO);

        assertEquals(user.getUsername(), userOutputDTO.getUsername());
        assertEquals(user.getPassword(), userOutputDTO.getPassword());
        assertEquals(user.getName(), userOutputDTO.getName());
        assertEquals(user.getSurname(), userOutputDTO.getSurname());
        assertEquals(user.getCompanyEmail(), userOutputDTO.getCompanyEmail());
        assertEquals(user.getPersonalEmail(), userOutputDTO.getPersonalEmail());
        assertEquals(user.getCity(), userOutputDTO.getCity());
        assertEquals(user.isActive(), userOutputDTO.isActive());
        assertEquals(user.getCreatedDate(), userOutputDTO.getCreatedDate());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsersWhenUsersExistThenReturnUserOutputDTOList() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserOutputDTO> userOutputDTOs = userService.getAllUsers();

        assertEquals(1, userOutputDTOs.size());
        UserOutputDTO userOutputDTO = userOutputDTOs.get(0);
        assertEquals(user.getUsername(), userOutputDTO.getUsername());
        assertEquals(user.getPassword(), userOutputDTO.getPassword());
        assertEquals(user.getName(), userOutputDTO.getName());
        assertEquals(user.getSurname(), userOutputDTO.getSurname());
        assertEquals(user.getCompanyEmail(), userOutputDTO.getCompanyEmail());
        assertEquals(user.getPersonalEmail(), userOutputDTO.getPersonalEmail());
        assertEquals(user.getCity(), userOutputDTO.getCity());
        assertEquals(user.isActive(), userOutputDTO.isActive());
        assertEquals(user.getCreatedDate(), userOutputDTO.getCreatedDate());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllUsersWhenNoUsersExistThenReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserOutputDTO> userOutputDTOs = userService.getAllUsers();

        assertTrue(userOutputDTOs.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserByIdWhenUserExistsThenReturnUserOutputDTO() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserOutputDTO userOutputDTO = userService.getUserById(id);

        assertEquals(user.getUsername(), userOutputDTO.getUsername());
        assertEquals(user.getPassword(), userOutputDTO.getPassword());
        assertEquals(user.getName(), userOutputDTO.getName());
        assertEquals(user.getSurname(), userOutputDTO.getSurname());
        assertEquals(user.getCompanyEmail(), userOutputDTO.getCompanyEmail());
        assertEquals(user.getPersonalEmail(), userOutputDTO.getPersonalEmail());
        assertEquals(user.getCity(), userOutputDTO.getCity());
        assertEquals(user.isActive(), userOutputDTO.isActive());
        assertEquals(user.getCreatedDate(), userOutputDTO.getCreatedDate());

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testGetUserByIdWhenUserDoesNotExistThenThrowRuntimeException() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> userService.getUserById(id));

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testGetUserByNameWhenUserExistsThenReturnUserOutputDTOList() {
        String name = "testName";
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findByName(name)).thenReturn(users);

        List<UserOutputDTO> userOutputDTOs = userService.getUserByName(name);

        assertEquals(1, userOutputDTOs.size());
        UserOutputDTO userOutputDTO = userOutputDTOs.get(0);
        assertEquals(user.getUsername(), userOutputDTO.getUsername());
        assertEquals(user.getPassword(), userOutputDTO.getPassword());
        assertEquals(user.getName(), userOutputDTO.getName());
        assertEquals(user.getSurname(), userOutputDTO.getSurname());
        assertEquals(user.getCompanyEmail(), userOutputDTO.getCompanyEmail());
        assertEquals(user.getPersonalEmail(), userOutputDTO.getPersonalEmail());
        assertEquals(user.getCity(), userOutputDTO.getCity());
        assertEquals(user.isActive(), userOutputDTO.isActive());
        assertEquals(user.getCreatedDate(), userOutputDTO.getCreatedDate());

        verify(userRepository, times(1)).findByName(name);
    }

    @Test
    public void testGetUserByNameWhenNoUserExistsThenReturnEmptyList() {
        String name = "testName";
        when(userRepository.findByName(name)).thenReturn(new ArrayList<>());

        List<UserOutputDTO> userOutputDTOs = userService.getUserByName(name);

        assertTrue(userOutputDTOs.isEmpty());
        verify(userRepository, times(1)).findByName(name);
    }

    @Test
    public void testUpdateUserWhenUserExistsThenReturnUpdatedUser() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserOutputDTO userOutputDTO = userService.updateUser(id, userInputDTO);

        assertEquals(user.getUsername(), userOutputDTO.getUsername());
        assertEquals(user.getPassword(), userOutputDTO.getPassword());
        assertEquals(user.getName(), userOutputDTO.getName());
        assertEquals(user.getSurname(), userOutputDTO.getSurname());
        assertEquals(user.getCompanyEmail(), userOutputDTO.getCompanyEmail());
        assertEquals(user.getPersonalEmail(), userOutputDTO.getPersonalEmail());
        assertEquals(user.getCity(), userOutputDTO.getCity());
        assertEquals(user.isActive(), userOutputDTO.isActive());
        assertEquals(user.getCreatedDate(), userOutputDTO.getCreatedDate());

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUserWhenUserDoesNotExistThenThrowRuntimeException() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> userService.updateUser(id, userInputDTO));

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteUserWhenUserExistsThenDoNothing() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.deleteUser(id);

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).delete(any(User.class));
    }

    @Test
    public void testDeleteUserWhenUserDoesNotExistThenThrowRuntimeException() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> userService.deleteUser(id));

        verify(userRepository, times(1)).findById(id);
    }
}