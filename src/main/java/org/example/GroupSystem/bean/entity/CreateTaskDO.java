package org.example.GroupSystem.bean.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CreateTaskDO {
    private String taskName;
    private String description;
    private Date dueDate;
}
