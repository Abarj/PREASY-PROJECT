package com.example.PREASYAPI.task.infrastructure.repository;

import com.example.PREASYAPI.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByProjectId(Integer projectId);
    Optional<Task> findByIdAndProjectId(Integer id, Integer projectId);
}