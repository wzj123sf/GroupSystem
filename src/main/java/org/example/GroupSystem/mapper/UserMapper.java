package org.example.GroupSystem.mapper;

import org.example.GroupSystem.bean.entity.LogDO;
import org.example.GroupSystem.bean.entity.UserDO;
import org.example.GroupSystem.bean.entity.UserInfoDO;

public interface UserMapper {
    int insert(UserDO userDO);
    UserInfoDO select(String username);
}
