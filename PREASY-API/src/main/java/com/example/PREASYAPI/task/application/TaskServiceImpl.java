package com.example.PREASYAPI.task.application;

import com.example.PREASYAPI.task.infrastructure.dto.input.TaskInputDTO;
import com.example.PREASYAPI.task.infrastructure.dto.output.TaskOutputDTO;
import com.example.PREASYAPI.task.infrastructure.repository.TaskRepository;
import com.example.PREASYAPI.project.infrastructure.repository.ProjectRepository;
import com.example.PREASYAPI.user.infrastructure.repository.UserRepository;
import com.example.PREASYAPI.task.domain.Task;
import com.example.PREASYAPI.project.domain.Project;
import com.example.PREASYAPI.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public TaskOutputDTO addTask(Integer projectId, TaskInputDTO taskInputDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setTaskName(taskInputDTO.getTaskName());
        task.setDescription(taskInputDTO.getDescription());
        task.setStartDate(taskInputDTO.getStartDate());
        task.setDueDate(taskInputDTO.getDueDate());
        task.setStatus(taskInputDTO.getStatus());
        task.setPriority(taskInputDTO.getPriority());
        task.setProject(project);

        task = taskRepository.save(task);

        return new TaskOutputDTO(task);
    }

    @Override
    public List<TaskOutputDTO> getAllTaskFromProject(Integer projectId) {
        return taskRepository.findAllByProjectId(projectId).stream()
                .map(TaskOutputDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public TaskOutputDTO getTasksByIdFromProject(Integer projectId, Integer id) {
        Task task = taskRepository.findByIdAndProjectId(id, projectId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return new TaskOutputDTO(task);
    }

    @Override
    public TaskOutputDTO updateTask(Integer taskId, TaskInputDTO taskInputDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTaskName(taskInputDTO.getTaskName());
        task.setDescription(taskInputDTO.getDescription());
        task.setStartDate(taskInputDTO.getStartDate());
        task.setDueDate(taskInputDTO.getDueDate());
        task.setStatus(taskInputDTO.getStatus());
        task.setPriority(taskInputDTO.getPriority());

        task = taskRepository.save(task);

        return new TaskOutputDTO(task);
    }

    @Override
    public void deleteTask(Integer id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
