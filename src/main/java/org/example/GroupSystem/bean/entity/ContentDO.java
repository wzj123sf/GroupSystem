package org.example.GroupSystem.bean.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ContentDO {
    private int submissionId;
    private Date submissionDate;
    private String content;
    private int score;
    private String fullname;
    private String description;
}
