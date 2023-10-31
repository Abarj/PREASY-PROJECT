package com.example.PREASYAPI.task.infrastructure.dto.input;

import com.example.PREASYAPI.util.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TaskInputDTO implements Serializable {

    private String taskName;

    private String description;

    private Date startDate;

    private Date dueDate;

    private TaskStatus status;

    private Integer priority;
}