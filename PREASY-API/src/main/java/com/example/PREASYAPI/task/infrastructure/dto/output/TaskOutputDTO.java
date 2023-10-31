package com.example.PREASYAPI.task.infrastructure.dto.output;

import com.example.PREASYAPI.task.domain.Task;
import com.example.PREASYAPI.util.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TaskOutputDTO implements Serializable {

    private Integer id;

    private String taskName;

    private String description;

    private Date startDate;

    private Date dueDate;

    private TaskStatus status;

    private Integer priority;

    private Integer projectId;

    public TaskOutputDTO(Task task) {
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.description = task.getDescription();
        this.startDate = task.getStartDate();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.projectId = task.getProject().getId();
    }
}