package com.example.PREASYAPI.user.domain;

import com.example.PREASYAPI.project.domain.Project;
import com.example.PREASYAPI.user.infrastructure.dto.input.UserInputDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String companyEmail;

    @Column
    private String personalEmail;

    @Column
    private String city;

    @Column
    private boolean active;

    @Column
    private Date createdDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Project> projects;

    public User(UserInputDTO userInputDTO) {
        this.username = userInputDTO.getUsername();
        this.password = userInputDTO.getPassword();
        this.name = userInputDTO.getName();
        this.surname = userInputDTO.getSurname();
        this.companyEmail = userInputDTO.getCompanyEmail();
        this.personalEmail = userInputDTO.getPersonalEmail();
        this.city = userInputDTO.getCity();
        this.active = userInputDTO.isActive();
        this.createdDate = userInputDTO.getCreateDate();
    }

    public void update(UserInputDTO userInputDto) {
        if (userInputDto.getUsername() != null && userInputDto.getUsername().length() <= 10 && userInputDto.getUsername().length() >= 6) {
            setUsername(userInputDto.getUsername());
        }
        if (userInputDto.getName() != null) {
            setName(userInputDto.getName());
        }
        if (userInputDto.getSurname() != null) {
            setSurname(userInputDto.getSurname());
        }
        if (userInputDto.getPassword() != null) {
            setPassword(userInputDto.getPassword());
        }
        if (userInputDto.getCompanyEmail() != null) {
            setCompanyEmail(userInputDto.getCompanyEmail());
        }
        if (userInputDto.getPersonalEmail() != null) {
            setPersonalEmail(userInputDto.getPersonalEmail());
        }
        if (userInputDto.getCity() != null) {
            setCity(userInputDto.getCity());
        }
    }
}