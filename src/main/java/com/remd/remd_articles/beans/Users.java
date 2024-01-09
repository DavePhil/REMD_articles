package com.remd.remd_articles.beans;

import lombok.Data;

@Data
public class Users {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String number;
    private String localisation;
}
