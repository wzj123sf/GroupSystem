package org.example.GroupSystem.convert;

import org.example.GroupSystem.bean.dto.*;
import org.example.GroupSystem.bean.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserConvert {
    public abstract UserDO dto2do(UserDTO userDTO);
    public CreateClassDTO addUsername(CreateClassDO createClassDO,String username)
    {
        CreateClassDTO createClassDTO = new CreateClassDTO();
        createClassDTO.setClassName(createClassDO.getClassName());
        createClassDTO.setDescription(createClassDO.getDescription());
        createClassDTO.setTeacherUsername(username);
        return createClassDTO;
    }
    public JoinClassDTO addUsernameStu(JoinClassDO joinClassDO,String username)
    {
        JoinClassDTO joinClassDTO = new JoinClassDTO();
        joinClassDTO.setUsername(username);
        joinClassDTO.setClassName(joinClassDO.getClassName());
        return joinClassDTO;
    }
    public CreateTaskDTO addClassName(CreateTaskDO createTaskDO, String className) {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setTaskName(createTaskDO.getTaskName());
        createTaskDTO.setDescription(createTaskDO.getDescription());
        createTaskDTO.setDueDate(createTaskDO.getDueDate());
        createTaskDTO.setClassName(className);
        return createTaskDTO;
    }
    public SubmitTaskDTO addTaskInfo(SubmitTaskDO submitTaskDO,String taskName,String username,String filePath)
    {
        // 创建SubmitTaskDTO对象
        SubmitTaskDTO submitTaskDTO = new SubmitTaskDTO();

        // 将SubmitTaskDO的数据复制到SubmitTaskDTO中
        submitTaskDTO.setTaskName(taskName);           // 设置任务名称
        submitTaskDTO.setContent(submitTaskDO.getContent());  // 设置任务内容
        submitTaskDTO.setFilePath(filePath);  // 设置文件路径
        submitTaskDTO.setFileName(submitTaskDO.getFileName());  // 设置文件名
        submitTaskDTO.setUsername(username);  // 设置用户名

        return submitTaskDTO;
    }

}
