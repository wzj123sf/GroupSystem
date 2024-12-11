package org.example.GroupSystem.bean.dto;

import lombok.Data;
import org.example.GroupSystem.bean.entity.CreateTaskDO;
@Data
public class CreateTaskDTO extends CreateTaskDO {
    private String className;
}
