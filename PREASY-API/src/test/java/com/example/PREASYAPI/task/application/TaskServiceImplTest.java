package com.example.PREASYAPI.task.application;

import com.example.PREASYAPI.project.domain.Project;
import com.example.PREASYAPI.project.infrastructure.repository.ProjectRepository;
import com.example.PREASYAPI.task.domain.Task;
import com.example.PREASYAPI.task.infrastructure.dto.input.TaskInputDTO;
import com.example.PREASYAPI.task.infrastructure.dto.output.TaskOutputDTO;
import com.example.PREASYAPI.task.infrastructure.repository.TaskRepository;
import com.example.PREASYAPI.util.enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Project project;
    private Task task;
    private TaskInputDTO taskInputDTO;

    @BeforeEach
    public void setUp() {
        project = new Project();
        project.setId(1);

        task = new Task();
        task.setId(1);
        task.setTaskName("Test Task");
        task.setDescription("Test Description");
        task.setStatus(TaskStatus.TO_DO);
        task.setPriority(1);
        task.setProject(project);

        taskInputDTO = new TaskInputDTO();
        taskInputDTO.setTaskName("Test Task");
        taskInputDTO.setDescription("Test Description");
        taskInputDTO.setStatus(TaskStatus.TO_DO);
        taskInputDTO.setPriority(1);
    }

    @Test
    public void testAddTaskWhenValidProjectIdAndTaskInputDTOThenReturnTaskOutputDTO() {
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task savedTask = invocation.getArgument(0);
            savedTask.setId(1);
            return savedTask;
        });

        TaskOutputDTO result = taskService.addTask(1, taskInputDTO);

        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTaskName(), result.getTaskName());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getStatus(), result.getStatus());
        assertEquals(task.getPriority(), result.getPriority());
    }

    @Test
    public void testAddTaskWhenInvalidProjectIdThenThrowRuntimeException() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.addTask(1, taskInputDTO));
    }

    @Test
    public void testGetAllTaskFromProjectWhenValidProjectIdThenReturnListOfTaskOutputDTOs() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(task));
        when(taskRepository.findAllByProjectId(1)).thenReturn(tasks);

        List<TaskOutputDTO> result = taskService.getAllTaskFromProject(1);

        assertEquals(1, result.size());
        assertEquals(task.getId(), result.get(0).getId());
        assertEquals(task.getTaskName(), result.get(0).getTaskName());
        assertEquals(task.getDescription(), result.get(0).getDescription());
        assertEquals(task.getStatus(), result.get(0).getStatus());
        assertEquals(task.getPriority(), result.get(0).getPriority());
    }

    @Test
    public void testGetAllTaskFromProjectWhenNoTasksInRepositoryThenReturnEmptyList() {
        when(taskRepository.findAllByProjectId(1)).thenReturn(new ArrayList<>());

        List<TaskOutputDTO> result = taskService.getAllTaskFromProject(1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllTaskFromProjectWhenProjectNotFoundThenReturnEmptyList() {
        List<TaskOutputDTO> tasks = taskService.getAllTaskFromProject(1);

        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testGetTasksByIdFromProjectWhenTaskExistsThenReturnTaskOutputDTO() {
        when(taskRepository.findByIdAndProjectId(1, 1)).thenReturn(Optional.of(task));

        TaskOutputDTO result = taskService.getTasksByIdFromProject(1, 1);

        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTaskName(), result.getTaskName());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getStatus(), result.getStatus());
        assertEquals(task.getPriority(), result.getPriority());
    }

    @Test
    public void testGetTasksByIdFromProjectWhenTaskNotFoundThenThrowRuntimeException() {
        when(taskRepository.findByIdAndProjectId(1, 1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.getTasksByIdFromProject(1, 1));
    }

    @Test
    public void testUpdateTaskWhenTaskExistsThenReturnUpdatedTask() {
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        TaskOutputDTO result = taskService.updateTask(1, taskInputDTO);

        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTaskName(), result.getTaskName());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getStatus(), result.getStatus());
        assertEquals(task.getPriority(), result.getPriority());
    }

    @Test
    public void testUpdateTaskWhenTaskNotFoundThenThrowRuntimeException() {
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.updateTask(1, taskInputDTO));
    }

    @Test
    public void testDeleteTaskWhenTaskExistsThenDeleteTask() {
        when(taskRepository.existsById(1)).thenReturn(true);

        taskService.deleteTask(1);

        verify(taskRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteTaskWhenTaskDoesNotExistThenThrowRuntimeException() {
        when(taskRepository.existsById(1)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> taskService.deleteTask(1));
    }
}