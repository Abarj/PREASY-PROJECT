package com.example.PREASYAPI.project.infrastructure.controller;

import com.example.PREASYAPI.project.application.ProjectService;
import com.example.PREASYAPI.project.infrastructure.dto.input.ProjectInputDTO;
import com.example.PREASYAPI.project.infrastructure.dto.output.ProjectOutputDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users/{userId}/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectOutputDTO addProject(@PathVariable Integer userId, @Valid @RequestBody ProjectInputDTO projectInputDTO) {
        return projectService.addProject(userId, projectInputDTO);
    }

    @GetMapping
    public List<ProjectOutputDTO> getAllProjectsFromUser(@PathVariable Integer userId) {
        return projectService.getAllProjectsFromUser(userId);
    }

    @GetMapping("/{id}")
    public ProjectOutputDTO getProjectById(@PathVariable Integer userId, @PathVariable Integer id) {
        return projectService.getProjectByIdFromUser(userId, id);
    }

    @PutMapping("/{id}")
    public ProjectOutputDTO updateProject(@PathVariable Integer id, @Valid @RequestBody ProjectInputDTO projectInputDTO) {
        return projectService.updateProject(id, projectInputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}