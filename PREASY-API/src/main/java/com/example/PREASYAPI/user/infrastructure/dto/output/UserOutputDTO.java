package com.example.PREASYAPI.user.infrastructure.dto.output;

import com.example.PREASYAPI.user.domain.User;
import com.example.PREASYAPI.project.domain.Project;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserOutputDTO implements Serializable {

    private Integer idPerson;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String companyEmail;

    private String personalEmail;

    private String city;

    private boolean active;

    private Date createdDate;

    private Set<Project> projects;

    public UserOutputDTO(User user)  {
        this.idPerson = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.companyEmail = user.getCompanyEmail();
        this.personalEmail = user.getPersonalEmail();
        this.city = user.getCity();
        this.active = user.isActive();
        this.createdDate = user.getCreatedDate();
        this.projects = user.getProjects();
    }
}