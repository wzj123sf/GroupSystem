package org.example.GroupSystem.bean.entity;

import lombok.Data;

import java.security.Timestamp;
import java.util.Date;

@Data
public class TaskInfoDO {
    private String taskName;
    private String description;
    private Date dueDate;
    private Date createdAt;
}
