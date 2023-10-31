package com.example.PREASYAPI.task.application;

import com.example.PREASYAPI.task.infrastructure.dto.input.TaskInputDTO;
import com.example.PREASYAPI.task.infrastructure.dto.output.TaskOutputDTO;

import java.util.List;

public interface TaskService {
    TaskOutputDTO addTask(Integer projectId, TaskInputDTO taskInputDTO);
    List<TaskOutputDTO> getAllTaskFromProject(Integer projectId);
    TaskOutputDTO getTasksByIdFromProject(Integer projectId, Integer id);
    TaskOutputDTO updateTask(Integer taskId, TaskInputDTO taskInputDTO);
    void deleteTask(Integer id);
}
