package org.example.GroupSystem.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.GroupSystem.bean.dto.JoinClassDTO;
import org.example.GroupSystem.bean.dto.SubmitTaskDTO;
import org.example.GroupSystem.bean.entity.ClassInfoDO;
import org.example.GroupSystem.bean.entity.JoinClassDO;
import org.example.GroupSystem.bean.entity.TaskInfoDO;

import java.util.List;

public interface StudentMapper {
    int joinClass(JoinClassDTO joinClassDTO);
    List<ClassInfoDO> selectClass(String username);
    int submitTask(SubmitTaskDTO submitTaskDTO);
    TaskInfoDO getTaskByName(String taskName);
    int getScore(@Param("username") String username, @Param("taskName")String taskName);
}
