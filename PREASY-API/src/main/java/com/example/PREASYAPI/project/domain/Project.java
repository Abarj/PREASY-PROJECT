package com.example.PREASYAPI.project.domain;

import com.example.PREASYAPI.project.infrastructure.dto.input.ProjectInputDTO;
import com.example.PREASYAPI.task.domain.Task;
import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.util.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String projectName;

    @Column(length = 200)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedEndDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "project",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = true)
    @JsonIgnore
    private User user;

    public Project(ProjectInputDTO projectInputDTO) {
        this.projectName = projectInputDTO.getProjectName();
        this.description = projectInputDTO.getDescription();
        this.startDate = projectInputDTO.getStartDate();
        this.estimatedEndDate = projectInputDTO.getEstimatedEndDate();
        this.status = projectInputDTO.getStatus();
    }

    public void update(ProjectInputDTO projectInputDTO) {
        if (projectInputDTO.getProjectName() != null && projectInputDTO.getProjectName().length() <= 10) {
            setProjectName(projectInputDTO.getProjectName());
        }
        if (projectInputDTO.getDescription() != null) {
            setDescription(projectInputDTO.getDescription());
        }
        if (projectInputDTO.getStartDate() != null) {
            setStartDate(projectInputDTO.getStartDate());
        }
        if (projectInputDTO.getEstimatedEndDate() != null) {
            setEstimatedEndDate(projectInputDTO.getEstimatedEndDate());
        }
        if (projectInputDTO.getStatus() != null) {
            setStatus(projectInputDTO.getStatus());
        }
    }
}

