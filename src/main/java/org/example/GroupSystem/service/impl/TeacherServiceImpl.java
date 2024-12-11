package org.example.GroupSystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.bean.dto.CreateClassDTO;
import org.example.GroupSystem.bean.dto.CreateTaskDTO;
import org.example.GroupSystem.bean.entity.*;
import org.example.GroupSystem.bean.res.ResultDTO;
import org.example.GroupSystem.mapper.TeacherMapper;
import org.example.GroupSystem.service.i.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public ResultDTO<String> createClass(CreateClassDTO createClassDTO) {
        int res = teacherMapper.createClass(createClassDTO);
        if(res == 1)
        {
            return ResultDTO.buildSuccess("创建班级成功");
        }else
            return ResultDTO.buildSuccess("创建班级失败");
    }

    @Override
    public ResultDTO<String> createTask(CreateTaskDTO createTaskDTO) {
        int res = teacherMapper.createTask(createTaskDTO);
        if(res == 1)
        {
            return ResultDTO.buildSuccess("创建任务成功");
        }else
            return ResultDTO.buildSuccess("创建任务失败");
    }

    @Override
    public ResultDTO<List<ClassInfoDO>> getClasses(String username) {
        return ResultDTO.buildSuccess(teacherMapper.selectClass(username));
    }

    @Override
    public ResultDTO<List<TaskInfoDO>> getTasks(String className) {
        return ResultDTO.buildSuccess(teacherMapper.selectTask(className));
    }

    @Override
    public ResultDTO<List<StudentDO>> getStudents(SearchStuDO searchStuDO) {
        return ResultDTO.buildSuccess(teacherMapper.selectStudent(searchStuDO));
    }

    @Override
    public ContentDO getContent(SearchContentDO searchContentDO) {
        return teacherMapper.selectContent(searchContentDO);
    }

    @Override
    public ResultDTO<String> updateScore(int submissionId,int score) {
        int res = teacherMapper.updateScore(submissionId,score);
        if(res == 1)
        {
            return ResultDTO.buildSuccess("成功");
        }else
            return ResultDTO.buildSuccess("失败");
    }
}
