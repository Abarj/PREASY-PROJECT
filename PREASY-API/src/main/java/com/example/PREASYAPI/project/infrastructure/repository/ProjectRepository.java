package com.example.PREASYAPI.project.infrastructure.repository;

import com.example.PREASYAPI.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
