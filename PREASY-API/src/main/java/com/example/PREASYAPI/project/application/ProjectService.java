package com.example.PREASYAPI.project.application;

import com.example.PREASYAPI.project.infrastructure.dto.input.ProjectInputDTO;
import com.example.PREASYAPI.project.infrastructure.dto.output.ProjectOutputDTO;

import java.util.List;

public interface ProjectService {
    ProjectOutputDTO addProject(Integer userId, ProjectInputDTO projectInputDTO);
    List<ProjectOutputDTO> getAllProjectsFromUser(Integer userId);
    ProjectOutputDTO getProjectByIdFromUser(Integer userId, Integer id);
    ProjectOutputDTO updateProject(Integer id, ProjectInputDTO projectInputDTO);
    void deleteProject(Integer id);
}
