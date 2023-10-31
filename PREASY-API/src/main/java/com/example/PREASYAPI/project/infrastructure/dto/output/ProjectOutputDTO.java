package com.example.PREASYAPI.project.infrastructure.dto.output;

import com.example.PREASYAPI.project.domain.Project;
import com.example.PREASYAPI.task.domain.Task;
import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.util.enums.ProjectStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectOutputDTO implements Serializable {

    private Integer id;

    private String projectName;

    private String description;

    private Date startDate;

    private Date estimatedEndDate;

    private ProjectStatus status;

    private Set<Task> tasks;

    private Integer userId;

    public ProjectOutputDTO(Project project) {
       this.id = project.getId();
       this.projectName = project.getProjectName();
       this.description = project.getDescription();
       this.startDate = project.getStartDate();
       this.estimatedEndDate = project.getEstimatedEndDate();
       this.status = project.getStatus();
       this.tasks = project.getTasks();
        if (project.getUser() != null) {
            this.userId = project.getUser().getId();
        }
    }
}

