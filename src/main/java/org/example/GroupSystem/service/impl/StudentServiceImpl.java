package org.example.GroupSystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.bean.dto.JoinClassDTO;
import org.example.GroupSystem.bean.dto.SubmitTaskDTO;
import org.example.GroupSystem.bean.entity.ClassInfoDO;
import org.example.GroupSystem.bean.entity.TaskInfoDO;
import org.example.GroupSystem.bean.res.ResultDTO;
import org.example.GroupSystem.mapper.StudentMapper;
import org.example.GroupSystem.service.i.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;

    @Override
    public ResultDTO<String> joinClass(JoinClassDTO joinClassDTO) {
        int res = studentMapper.joinClass(joinClassDTO);
        if(res == 1)
        {
            return ResultDTO.buildSuccess("加入班级成功");
        }else
            return ResultDTO.buildSuccess("加入班级失败");
    }

    @Override
    public ResultDTO<List<ClassInfoDO>> getClasses(String username) {
        return ResultDTO.buildSuccess(studentMapper.selectClass(username));
    }

    @Override
    public TaskInfoDO getTaskByName(String taskName) {
        TaskInfoDO taskInfoDO = studentMapper.getTaskByName(taskName);
        return taskInfoDO;
    }

    @Override
    public ResultDTO<String> submitTask(SubmitTaskDTO submitTaskDTO) {
        int res = studentMapper.submitTask(submitTaskDTO);
        if(res == 1)
        {
            return ResultDTO.buildSuccess("作业提交成功");
        }else
            return ResultDTO.buildSuccess("提交失败");
    }

    @Override
    public int getScore(String username,String taskName) {
        return studentMapper.getScore(username,taskName);
    }
}
