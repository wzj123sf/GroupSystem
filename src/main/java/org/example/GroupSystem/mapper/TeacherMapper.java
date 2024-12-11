package org.example.GroupSystem.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.GroupSystem.bean.dto.CreateClassDTO;
import org.example.GroupSystem.bean.dto.CreateTaskDTO;
import org.example.GroupSystem.bean.entity.*;

import java.util.List;

public interface TeacherMapper {
    int createClass(CreateClassDTO createClassDTO);
    int createTask(CreateTaskDTO createTaskDTO);
    List<ClassInfoDO> selectClass(String username);
    List<TaskInfoDO> selectTask(String className);
    List<StudentDO> selectStudent(SearchStuDO searchStuDO);
    ContentDO selectContent(SearchContentDO searchContentDO);
    int updateScore(@Param("submissionId") int submissionId, @Param("score") int score);
    String getFilePath(int submissionId);
}
