package org.example.GroupSystem.bean.entity;

import lombok.Data;

import java.util.Date;
@Data
public class StudentDO {
    private String fullname;
    private Date submissionDate;
    private int score;
}
