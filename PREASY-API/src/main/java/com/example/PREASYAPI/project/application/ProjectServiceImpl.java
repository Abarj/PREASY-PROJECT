package com.example.PREASYAPI.project.application;

import com.example.PREASYAPI.project.domain.Project;
import com.example.PREASYAPI.project.infrastructure.dto.input.ProjectInputDTO;
import com.example.PREASYAPI.project.infrastructure.dto.output.ProjectOutputDTO;
import com.example.PREASYAPI.project.infrastructure.repository.ProjectRepository;
import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.user.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ProjectOutputDTO addProject(Integer userId, ProjectInputDTO projectInputDTO) {
        // Encuentra el usuario en la base de datos o lanza una excepción si no existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario con id " + userId + " no encontrado"));

        // Crea un nuevo proyecto y establece sus atributos a partir de projectInputDTO
        Project project = new Project();
        project.setProjectName(projectInputDTO.getProjectName());
        project.setDescription(projectInputDTO.getDescription());
        project.setStartDate(projectInputDTO.getStartDate());
        project.setEstimatedEndDate(projectInputDTO.getEstimatedEndDate());
        project.setStatus(projectInputDTO.getStatus());

        // Asocia el proyecto con el usuario
        project.setUser(user);

        // Guarda el proyecto en la base de datos
        projectRepository.save(project);

        // Devuelve un DTO que representa el proyecto creado
        return new ProjectOutputDTO(project);
    }


    @Override
    public List<ProjectOutputDTO> getAllProjectsFromUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario con id " + userId + " no encontrado"));

        List<ProjectOutputDTO> projectsList = user.getProjects().stream()
                .map(ProjectOutputDTO::new)
                .collect(Collectors.toList());

        return projectsList;
    }

    @Override
    public ProjectOutputDTO getProjectByIdFromUser(Integer userId, Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun proyecto con el id: " + id));

        if (project.getUser() == null || !project.getUser().getId().equals(userId)) {
            throw new RuntimeException("El proyecto con id " + id + " no pertenece al usuario con id " + userId);
        }

        return new ProjectOutputDTO(project);
    }

    @Override
    public ProjectOutputDTO updateProject(Integer id, ProjectInputDTO projectInputDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún proyecto con el id: " + id));
        project.update(projectInputDTO);
        projectRepository.save(project);

        return new ProjectOutputDTO(project);
    }

    @Override
    public void deleteProject(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún proyecto con el id: " + id));
        projectRepository.delete(project);
    }
}
