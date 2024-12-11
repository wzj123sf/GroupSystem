package org.example.GroupSystem.service.i;

import org.example.GroupSystem.bean.dto.CreateClassDTO;
import org.example.GroupSystem.bean.dto.CreateTaskDTO;
import org.example.GroupSystem.bean.entity.*;
import org.example.GroupSystem.bean.res.ResultDTO;

import java.util.List;

public interface TeacherService {
    ResultDTO<String> createClass(CreateClassDTO createClassDTO);
    ResultDTO<String> createTask(CreateTaskDTO createTaskDTO);
    ResultDTO<List<ClassInfoDO>> getClasses(String username);
    ResultDTO<List<TaskInfoDO>> getTasks(String className);
    ResultDTO<List<StudentDO>> getStudents(SearchStuDO searchStuDO);
    ContentDO getContent(SearchContentDO searchContentDO);
    ResultDTO<String> updateScore(int submissionId,int score);
}
