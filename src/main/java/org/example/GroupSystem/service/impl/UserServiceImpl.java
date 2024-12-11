package org.example.GroupSystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.bean.dto.LogDTO;
import org.example.GroupSystem.bean.dto.UserDTO;
import org.example.GroupSystem.bean.entity.UserDO;
import org.example.GroupSystem.bean.entity.UserInfoDO;
import org.example.GroupSystem.bean.res.ResultDTO;
import org.example.GroupSystem.convert.UserConvert;
import org.example.GroupSystem.mapper.UserMapper;
import org.example.GroupSystem.service.i.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConvert userConvert;
    @Override
    public ResultDTO<String> addUser(UserDTO userDTO) {
        UserDO userDO = userConvert.dto2do(userDTO);
        int res = userMapper.insert(userDO);
        if (res == 1)
        {
            return ResultDTO.buildSuccess("添加学生成功");
        }else
        {
            return ResultDTO.buildSuccess("添加学生失败");
        }
    }

    @Override
    public UserInfoDO logIn(LogDTO logDTO) {
        return userMapper.select(logDTO.getUsername());
    }
}
