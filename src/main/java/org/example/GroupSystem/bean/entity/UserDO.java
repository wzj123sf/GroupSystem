package org.example.GroupSystem.bean.entity;

import lombok.Data;

@Data
public class UserDO {
    private String username;
    private String fullname;
    private String gender;
    private String phone;
    private String email;
    private String password;
    private String role;
}
