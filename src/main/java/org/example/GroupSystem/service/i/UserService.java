package org.example.GroupSystem.service.i;

import org.example.GroupSystem.bean.dto.LogDTO;
import org.example.GroupSystem.bean.dto.UserDTO;
import org.example.GroupSystem.bean.entity.UserInfoDO;
import org.example.GroupSystem.bean.res.ResultDTO;

public interface UserService {
    ResultDTO<String> addUser(UserDTO userDTO);
    UserInfoDO logIn(LogDTO logDTO);
}
