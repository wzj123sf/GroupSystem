package org.example.GroupSystem.bean.dto;

import lombok.Data;
import org.example.GroupSystem.bean.entity.SubmitTaskDO;
@Data
public class SubmitTaskDTO extends SubmitTaskDO {
    private String taskName;
    private String username;
    private String filePath;
}
