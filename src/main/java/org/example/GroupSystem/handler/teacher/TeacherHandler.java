package org.example.GroupSystem.handler.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.bean.dto.CreateClassDTO;
import org.example.GroupSystem.bean.dto.CreateTaskDTO;
import org.example.GroupSystem.bean.dto.UserDTO;
import org.example.GroupSystem.bean.entity.*;
import org.example.GroupSystem.bean.res.ResultDTO;
import org.example.GroupSystem.convert.UserConvert;
import org.example.GroupSystem.service.i.TeacherService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/teacher")
public class TeacherHandler {
    @Resource
    TeacherService teacherService;
    @Resource
    UserConvert userConvert;
    @PostMapping("/createClass")
    @ResponseBody
    public ResultDTO<String> createClass(@RequestBody CreateClassDO createClassDO, HttpSession session)
    {
        try {
            String username = (String) session.getAttribute("username");
            CreateClassDTO createClassDTO = userConvert.addUsername(createClassDO,username);
            return teacherService.createClass(createClassDTO);
        }catch (Exception e)
        {
            System.out.println(e);
            log.error("创建异常",e);
            return ResultDTO.buildSuccess("创建失败："+e);
        }
    }
    @PostMapping("/createTask")
    @ResponseBody
    public ResultDTO<String> createTask(@RequestBody CreateTaskDO createTaskDO, HttpSession session)
    {
        try {
            String className = (String) session.getAttribute("className");
            CreateTaskDTO createTaskDTO = userConvert.addClassName(createTaskDO,className);
            return teacherService.createTask(createTaskDTO);
        }catch (Exception e)
        {
            System.out.println(e);
            log.error("创建异常",e);
            return ResultDTO.buildSuccess("创建失败："+e);
        }
    }


    @GetMapping("/switchToCreate")
    public String switchToCreate()
    {
        return "teacher/createClass";
    }
    @GetMapping("/switchToCreateTask")
    public String switchToCreateTask()
    {
        return "teacher/createTask";
    }

    @GetMapping("/getClassList")
    @ResponseBody
    public ResultDTO<String> getClassList(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            ResultDTO<List<ClassInfoDO>> res = teacherService.getClasses(username);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRes = objectMapper.writeValueAsString(res);

            // 返回 JSON 字符串包装在 ResultDTO 中
            return ResultDTO.buildSuccess(jsonRes);
        } catch (Exception e) {
            log.error("获取课程列表失败", e);
            return null;
        }
    }
    @GetMapping("/getTaskList")
    @ResponseBody
    public ResultDTO<String> getTaskList(HttpSession session) {
        try {
            String className = (String) session.getAttribute("className");
            ResultDTO<List<TaskInfoDO>> res = teacherService.getTasks(className);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRes = objectMapper.writeValueAsString(res);

            // 返回 JSON 字符串包装在 ResultDTO 中
            return ResultDTO.buildSuccess(jsonRes);
        } catch (Exception e) {
            log.error("获取课程列表失败", e);
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/switchToClass")
    public String switchToClass(HttpSession session, Model model){
        String className = (String) session.getAttribute("className");
        model.addAttribute("className", className);
        return "teacher/teacherClass";
    }
    @GetMapping("/switchToScore")
    public String switchToScore(HttpSession session, Model model){
        String fullname = (String) session.getAttribute("fullname");
        String taskName = (String) session.getAttribute("taskName");
        SearchContentDO searchContentDO = new SearchContentDO();
        searchContentDO.setFullname(fullname);
        searchContentDO.setTaskName(taskName);
        ContentDO contentDO = teacherService.getContent(searchContentDO);
        model.addAttribute("fullname", fullname);
        model.addAttribute("content", contentDO.getContent());
        model.addAttribute("score", contentDO.getScore());
        model.addAttribute("submissionDate", contentDO.getSubmissionDate());
        model.addAttribute("description", contentDO.getDescription());
        session.setAttribute("submissionId",contentDO.getSubmissionId());
        return "teacher/score";
    }
    @PostMapping("submitClass")
    @ResponseBody
    public ResultDTO<String> submitClass(HttpSession session,@RequestBody String className)
    {
        session.setAttribute("className",className);
        return ResultDTO.buildSuccess("上传班级名字成功");
    }
    @PostMapping("submitTaskName")
    @ResponseBody
    public ResultDTO<String> submitTaskName(HttpSession session,@RequestBody String taskName)
    {
        session.setAttribute("taskName",taskName);
        return ResultDTO.buildSuccess("上传任务名称成功");
    }
    @PostMapping("submitFullname")
    @ResponseBody
    public ResultDTO<String> submitFullname(HttpSession session,@RequestBody String fullname)
    {
        session.setAttribute("fullname",fullname);
        return ResultDTO.buildSuccess("上传姓名成功");
    }
    @PostMapping("submitScore")
    @ResponseBody
    public ResultDTO<String> submitScore(HttpSession session,@RequestBody String score)
    {
        try {
            int submissionId = (int)session.getAttribute("submissionId");
            return teacherService.updateScore(submissionId,Integer.parseInt(score));
        }catch (Exception e)
        {
            System.out.println(e);
            log.error("提交分数错误",e);
            return ResultDTO.buildSuccess("wrong");
        }

    }
    @GetMapping("/getStudentList")
    @ResponseBody
    public ResultDTO<String> getStudentList(HttpSession session) {
        try {
            String className = (String) session.getAttribute("className");
            String taskName = (String) session.getAttribute("taskName");
            SearchStuDO searchStuDO = new SearchStuDO();
            searchStuDO.setClassName(className);
            searchStuDO.setTaskName(taskName);
            ResultDTO<List<StudentDO>> res = teacherService.getStudents(searchStuDO);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRes = objectMapper.writeValueAsString(res);

            // 返回 JSON 字符串包装在 ResultDTO 中
            return ResultDTO.buildSuccess(jsonRes);
        } catch (Exception e) {
            log.error("获取课程列表失败", e);
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/switchToTask")
    public String switchToTask()
    {
        return "/teacher/showStudent";
    }
}
