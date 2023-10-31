package com.example.PREASYAPI.task.infrastructure.controller;

import com.example.PREASYAPI.task.application.TaskService;
import com.example.PREASYAPI.task.infrastructure.dto.input.TaskInputDTO;
import com.example.PREASYAPI.task.infrastructure.dto.output.TaskOutputDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutputDTO addTask(@PathVariable Integer projectId, @Valid @RequestBody TaskInputDTO taskInputDTO) {
        return taskService.addTask(projectId, taskInputDTO);
    }

    @GetMapping
    public List<TaskOutputDTO> getAllTasksFromProject(@PathVariable Integer projectId) {
        return taskService.getAllTaskFromProject(projectId);
    }

    @GetMapping("/{taskId}")
    public TaskOutputDTO getTaskByIdFromProject(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        return taskService.getTasksByIdFromProject(projectId, taskId);
    }

    @PutMapping("/{taskId}")
    public TaskOutputDTO updateTask(@PathVariable Integer taskId, @Valid @RequestBody TaskInputDTO taskInputDTO) {
        return taskService.updateTask(taskId, taskInputDTO);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
