package org.example.GroupSystem.handler.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.bean.dto.CreateClassDTO;
import org.example.GroupSystem.bean.dto.JoinClassDTO;
import org.example.GroupSystem.bean.dto.SubmitTaskDTO;
import org.example.GroupSystem.bean.entity.*;
import org.example.GroupSystem.bean.res.ResultDTO;
import org.example.GroupSystem.convert.UserConvert;
import org.example.GroupSystem.service.i.StudentService;
import org.example.GroupSystem.service.i.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentHandler {
    @Resource
    StudentService studentService;
    @Resource
    UserConvert userConvert;
    @PostMapping("/joinClass")
    @ResponseBody
    public ResultDTO<String> joinClass(@RequestBody JoinClassDO joinClassDO, HttpSession session)
    {
        try {
            String username = (String) session.getAttribute("username");
            JoinClassDTO joinClassDTO = userConvert.addUsernameStu(joinClassDO,username);
            return studentService.joinClass(joinClassDTO);
        }catch (Exception e)
        {
            System.out.println(e);
            log.error("创建异常",e);
            return ResultDTO.buildSuccess("创建失败："+e);
        }
    }


    @GetMapping("/switchToJoin")
    public String switchToJoin()
    {
        return "/student/joinClass";
    }
    @GetMapping("/switchToClass")
    public String switchToClass()
    {
        return "/student/studentClass";
    }
    @GetMapping("/switchToTask")
    public String switchToTask(HttpSession session, Model model)
    {
        String taskName = (String) session.getAttribute("taskName");
        String username = (String) session.getAttribute("username");
        TaskInfoDO taskInfoDO = studentService.getTaskByName(taskName);
        int score = studentService.getScore(username,taskName);
        model.addAttribute("score",score);
        model.addAttribute("taskName", taskName);
        model.addAttribute("description", taskInfoDO.getDescription());
        model.addAttribute("dueDate", taskInfoDO.getDueDate());
        model.addAttribute("createdAt", taskInfoDO.getCreatedAt());
        return "/student/submitTask";
    }

    @GetMapping("/getClassList")
    @ResponseBody
    public ResultDTO<String> getClassList(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            ResultDTO<List<ClassInfoDO>> res = studentService.getClasses(username);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRes = objectMapper.writeValueAsString(res);

            // 返回 JSON 字符串包装在 ResultDTO 中
            return ResultDTO.buildSuccess(jsonRes);
        } catch (Exception e) {
            log.error("获取课程列表失败", e);
            return null;
        }
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
    @PostMapping("/submitTask")
    @ResponseBody//
    public ResultDTO<String> submitTask(@RequestParam("content") String content,@RequestParam("file") MultipartFile file, HttpSession session)
    {
        try {
            String username = (String) session.getAttribute("username");
            String taskName = (String) session.getAttribute("taskName");
            String filePath = saveFile(file);
            //String filePath = "";
            SubmitTaskDO submitTaskDO = new SubmitTaskDO();
            submitTaskDO.setContent(content);
            submitTaskDO.setFileName(file.getOriginalFilename());
            SubmitTaskDTO submitTaskDTO = userConvert.addTaskInfo(submitTaskDO,taskName,username,filePath);
            //SubmitTaskDTO submitTaskDTO = new SubmitTaskDTO();
            return studentService.submitTask(submitTaskDTO);
        }catch (Exception e)
        {
            System.out.println(e);
            log.error("创建异常",e);
            return ResultDTO.buildSuccess("创建失败："+e);
        }
    }
    private String saveFile(MultipartFile file) throws IOException {
        // 设置文件保存目录
        String directory = "./temp"; // 你可以修改这个路径
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs(); // 如果目录不存在，创建目录
        }
        // 获取文件名和路径
        String fileName = file.getOriginalFilename();
        String filePath = directory +'/'+ fileName;

        // 将文件保存到服务器
        File destFile = new File(filePath);
        file.transferTo(destFile);

        return filePath; // 返回文件路径
    }
}
