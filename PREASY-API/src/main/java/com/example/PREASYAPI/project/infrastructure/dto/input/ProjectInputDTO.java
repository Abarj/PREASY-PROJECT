package com.example.PREASYAPI.project.infrastructure.dto.input;

import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.util.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ProjectInputDTO implements Serializable {

    private String projectName;

    private String description;

    private Date startDate;

    private Date estimatedEndDate;

    private ProjectStatus status;
}