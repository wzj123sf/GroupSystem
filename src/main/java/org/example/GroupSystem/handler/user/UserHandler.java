package org.example.GroupSystem.handler.user;

import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.bean.dto.LogDTO;
import org.example.GroupSystem.bean.dto.UserDTO;
import org.example.GroupSystem.bean.entity.UserInfoDO;
import org.example.GroupSystem.bean.res.ResultDTO;
import org.example.GroupSystem.service.i.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserHandler {
    @Resource
    private UserService userService;

    @PostMapping("/signUp")
    @ResponseBody
    public ResultDTO<String> signUp(@RequestBody UserDTO dto)
    {
        try {
            return userService.addUser(dto);
        }catch (Exception e)
        {
            log.error("注册异常",e);
            return ResultDTO.buildSuccess("注册失败"+e);
        }
    }
    @PostMapping("/logIn")
    @ResponseBody
    public ResultDTO<String> LogIn(@RequestBody LogDTO dto, HttpSession session)
    {
        UserInfoDO userInfoDO = userService.logIn(dto);
        if (dto.getPassword().equals(userInfoDO.getPassword()))
        {
            session.setAttribute("username",dto.getUsername());
            session.setAttribute("role",userInfoDO.getRole());
            if(userInfoDO.getRole().equals("student"))
            {
                return ResultDTO.buildSuccess("student") ;
            }
            else
            {
                return ResultDTO.buildSuccess("teacher") ;
            }
        }else {
            //return ResultDTO.buildSuccess(dto.getPassword()+' '+userInfoDO.getPassword()) ;
            return ResultDTO.buildSuccess("用户名不存在或密码错误") ;
        }

    }
    @GetMapping("/switchToLog")
    public String switchToLog()
    {
        return "user/LogIn";
    }
    @GetMapping("/switchToSign")
    public String switchToSign()
    {
        return "user/SignUp";
    }
    @GetMapping("/switchToTeacher")
    public String switchToTeacher(HttpSession session, Model model)
    {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "teacher/teacher";
    }
    @GetMapping("/switchToStudent")
    public String switchToStudent(HttpSession session, Model model)
    {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "student/student";
    }
}
