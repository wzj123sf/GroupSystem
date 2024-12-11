package org.example.GroupSystem.handler.file;

import lombok.extern.slf4j.Slf4j;
import org.example.GroupSystem.mapper.TeacherMapper;
import org.example.GroupSystem.service.i.TeacherService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
@Slf4j
@RequestMapping("/file")
public class FileHandler
{
    @javax.annotation.Resource
    TeacherMapper teacherMapper;
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(HttpSession session) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        Integer submissionId = (Integer) session.getAttribute("submissionId");
        if (submissionId == null) {
            throw new IOException("Submission ID is missing or invalid.");
        }

        // 获取文件路径
        String filePath = teacherMapper.getFilePath(submissionId);
        if (filePath == null || filePath.isEmpty()) {
            throw new IOException("File path not found for submission ID: " + submissionId);
        }

        Path path = Paths.get(filePath).normalize();
        Resource resource = new UrlResource(path.toUri());

        // 检查文件是否存在
        if (!resource.exists()) {
            throw new IOException("File not found at path: " + filePath);
        }

        // 设置下载响应头
        String contentDisposition = "attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")  // 强制设置为二进制流
                .body(resource);
    }

}
