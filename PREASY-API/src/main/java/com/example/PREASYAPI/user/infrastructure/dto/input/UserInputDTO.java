package com.example.PREASYAPI.user.infrastructure.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserInputDTO implements Serializable {

    private String username;

    private String password;

    private String name;

    private String surname;

    private String companyEmail;

    private String personalEmail;

    private String city;

    private boolean active;

    private Date createDate;
}