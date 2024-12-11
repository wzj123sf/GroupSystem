package org.example.GroupSystem.service.i;

import org.example.GroupSystem.bean.dto.CreateClassDTO;
import org.example.GroupSystem.bean.dto.JoinClassDTO;
import org.example.GroupSystem.bean.dto.SubmitTaskDTO;
import org.example.GroupSystem.bean.entity.ClassInfoDO;
import org.example.GroupSystem.bean.entity.TaskInfoDO;
import org.example.GroupSystem.bean.res.ResultDTO;

import java.util.List;

public interface StudentService {
    ResultDTO<String> joinClass(JoinClassDTO joinClassDTO);
    ResultDTO<List<ClassInfoDO>> getClasses(String username);
    TaskInfoDO getTaskByName(String taskName);
    ResultDTO<String> submitTask(SubmitTaskDTO submitTaskDTO);
    int getScore(String username,String taskName);
}
