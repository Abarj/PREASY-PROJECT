package com.example.PREASYAPI.project.application;

import com.example.PREASYAPI.project.domain.Project;
import com.example.PREASYAPI.project.infrastructure.dto.input.ProjectInputDTO;
import com.example.PREASYAPI.project.infrastructure.dto.output.ProjectOutputDTO;
import com.example.PREASYAPI.project.infrastructure.repository.ProjectRepository;
import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.user.infrastructure.repository.UserRepository;
import com.example.PREASYAPI.util.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private User user;
    private ProjectInputDTO projectInputDTO;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);

        projectInputDTO = new ProjectInputDTO();
        projectInputDTO.setProjectName("Test Project");
    }

    @Test
    public void testAddProjectWhenValidUserIdAndProjectInputDTOThenReturnProjectOutputDTO() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        ProjectOutputDTO projectOutputDTO = projectService.addProject(user.getId(), projectInputDTO);

        verify(projectRepository, times(1)).save(any(Project.class));
        assertThat(projectOutputDTO.getProjectName()).isEqualTo(projectInputDTO.getProjectName());
    }

    @Test
    public void testAddProjectWhenInvalidUserIdThenThrowRuntimeException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.addProject(user.getId(), projectInputDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario con id " + user.getId() + " no encontrado");
    }

    @Test
    public void testGetAllProjectsFromUserWhenValidUserIdThenReturnListOfProjectOutputDTOs() {
        Set<Project> projects = new HashSet<>();
        Project project = new Project();
        project.setProjectName("Test Project");
        projects.add(project);
        user.setProjects(projects);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var projectOutputDTOs = projectService.getAllProjectsFromUser(user.getId());

        verify(userRepository, times(1)).findById(user.getId());
        assertThat(projectOutputDTOs.size()).isEqualTo(1);
        assertThat(projectOutputDTOs.get(0).getProjectName()).isEqualTo(project.getProjectName());
    }

    @Test
    public void testGetAllProjectsFromUserWhenInvalidUserIdThenThrowRuntimeException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.getAllProjectsFromUser(user.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario con id " + user.getId() + " no encontrado");
    }

    @Test
    public void testGetProjectByIdFromUserWhenValidUserIdAndProjectIdThenReturnProjectOutputDTO() {
        Project project = new Project();
        project.setId(1);  // set an ID for the project
        project.setProjectName("Test Project");
        project.setUser(user);

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));

        ProjectOutputDTO projectOutputDTO = projectService.getProjectByIdFromUser(user.getId(), project.getId());

        verify(projectRepository, times(1)).findById(anyInt());
        assertThat(projectOutputDTO.getProjectName()).isEqualTo(project.getProjectName());
    }

    @Test
    public void testGetProjectByIdFromUserWhenProjectDoesNotBelongToUserThenThrowRuntimeException() {
        Project project = new Project();
        project.setId(1);  // set an ID for the project
        project.setProjectName("Test Project");
        User anotherUser = new User();
        anotherUser.setId(2);
        project.setUser(anotherUser);

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));

        assertThatThrownBy(() -> projectService.getProjectByIdFromUser(user.getId(), project.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("El proyecto con id " + project.getId() + " no pertenece al usuario con id " + user.getId());
    }

    @Test
    public void testGetProjectByIdFromUserWhenProjectDoesNotExistThenThrowRuntimeException() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.getProjectByIdFromUser(user.getId(), 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No se ha encontrado ningun proyecto con el id: " + 1);
    }

    @Test
    public void testUpdateProjectWhenValidIdAndProjectInputDTOThenReturnUpdatedProjectOutputDTO() {
        // Arrange
        Project project = new Project();
        project.setId(1);
        project.setProjectName("Old Name");

        ProjectInputDTO projectInputDTO = new ProjectInputDTO();
        projectInputDTO.setProjectName("New Name");  // set a new name for the project

        when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));

        // Act
        ProjectOutputDTO updatedProjectOutputDTO = projectService.updateProject(project.getId(), projectInputDTO);

        // Assert
        verify(projectRepository, times(1)).save(any(Project.class));
        assertThat(updatedProjectOutputDTO.getProjectName()).isEqualTo(projectInputDTO.getProjectName());
    }

    @Test
    public void testUpdateProjectWhenProjectDoesNotExistThenThrowRuntimeException() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.updateProject(1, projectInputDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No se ha encontrado ningún proyecto con el id: " + 1);
    }

    @Test
    public void testDeleteProjectWhenValidProjectIdThenProjectIsDeleted() {
        Project project = new Project();
        project.setId(1);

        when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));

        projectService.deleteProject(project.getId());

        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    public void testDeleteProjectWhenInvalidProjectIdThenRuntimeException() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.deleteProject(1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No se ha encontrado ningún proyecto con el id: " + 1);
    }
}