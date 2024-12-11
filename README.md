Created BY 李晨光：负责项目定项，初始化了整个项目的结构；负责编写教师模块（包括前端（白板）后端）,后端项目部署环境调试，负责该模块的后端接口测试；项目结项打包.(36%)
   邬宗杰：负责编写学生模块并负责该模块的后端测试；设计数据库，数据库环境的部署；整个项目的图表分析；上传至github（https://github.com/wzj123sf/GroupSystem.git）; 项目文档撰写。(34%)
   魏绍康：负责user模块的前端编写；负责UI设计，前端接口对接；配置tomocat，web部署负责人；美化对应前端白板；项目解说视频录制与剪辑。(30%)
# 项目说明与部署文档

## 项目概述

本项目是一个基于Java的Web应用程序，旨在提供一个在线班级管理系统，包括学生和教师的注册、登录、班级创建、加入班级、任务发布、任务提交和成绩管理等功能。项目使用Spring框架进行开发，并使用MyBatis进行数据库操作。

## 技术栈

- Java
- Spring Framework
- MyBatis
- Maven
- MySQL
- HTML, CSS, JavaScript
- jQuery

## 目录结构

```
GroupSystem
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── example
    │   │           └── GroupSystem
    │   │               ├── bean
    │   │               │   ├── dto
    │   │               │   │   ├── CreateClassDTO.java
    │   │               │   │   ├── CreateTaskDTO.java
    │   │               │   │   ├── JoinClassDTO.java
    │   │               │   │   ├── LogDTO.java
    │   │               │   │   ├── SubmitTaskDTO.java
    │   │               │   │   └── UserDTO.java
    │   │               │   ├── entity
    │   │               │   │   ├── ClassInfoDO.java
    │   │               │   │   ├── ContentDO.java
    │   │               │   │   ├── CreateClassDO.java
    │   │               │   │   ├── CreateTaskDO.java
    │   │               │   │   ├── DetailTaskDO.java
    │   │               │   │   ├── JoinClassDO.java
    │   │               │   │   ├── LogDO.java
    │   │               │   │   ├── SearchContentDO.java
    │   │               │   │   ├── SearchStuDO.java
    │   │               │   │   ├── StudentDO.java
    │   │               │   │   ├── SubmitTaskDO.java
    │   │               │   │   ├── TaskInfoDO.java
    │   │               │   │   ├── UserDO.java
    │   │               │   │   └── UserInfoDO.java
    │   │               │   └── res
    │   │               │       └── ResultDTO.java
    │   │               ├── convert
    │   │               │   └── UserConvert.java
    │   │               ├── handler
    │   │               │   ├── file
    │   │               │   │   └── FileHandler.java
    │   │               │   ├── student
    │   │               │   │   └── StudentHandler.java
    │   │               │   ├── teacher
    │   │               │   │   └── TeacherHandler.java
    │   │               │   └── user
    │   │               │       └── UserHandler.java
    │   │               ├── mapper
    │   │               │   ├── StudentMapper.java
    │   │               │   ├── TeacherMapper.java
    │   │               │   └── UserMapper.java
    │   │               └── service
    │   │                   ├── i
    │   │                   │   ├── StudentService.java
    │   │                   │   ├── TeacherService.java
    │   │                   │   └── UserService.java
    │   │                   └── impl
    │   │                       ├── StudentServiceImpl.java
    │   │                       ├── TeacherServiceImpl.java
    │   │                       └── UserServiceImpl.java
    │   ├── resources
    │   │   ├── application.properties
    │   │   ├── applicationContext.xml
    │   │   ├── dtd
    │   │   │   ├── mybatis-3-config.dtd
    │   │   │   └── mybatis-3-mapper.dtd
    │   │   ├── logback.xml
    │   │   ├── mybatis
    │   │   │   ├── mapper
    │   │   │   │   ├── StudentMapper.xml
    │   │   │   │   ├── TeacherMapper.xml
    │   │   │   │   └── UserMapper.xml
    │   │   │   └── mybatis-config.xml
    │   │   ├── springmvc-servlet.xml
    │   │   └── temp
    │   └── webapp
    │       ├── index.jsp
    │       ├── resources
    │       │   ├── js
    │       │   │   └── api.js
    │       │   └── temp
    │       └── WEB-INF
    │           ├── views
    │           │   ├── student
    │           │   │   ├── joinClass.jsp
    │           │   │   ├── student.jsp
    │           │   │   ├── studentClass.jsp
    │           │   │   └── submitTask.jsp
    │           │   ├── teacher
    │           │   │   ├── createClass.jsp
    │           │   │   ├── createTask.jsp
    │           │   │   ├── score.jsp
    │           │   │   ├── showStudent.jsp
    │           │   │   ├── teacher.jsp
    │           │   │   └── teacherClass.jsp
    │           │   └── user
    │           │       ├── LogIn.jsp
    │           │       └── SignUp.jsp
    │           └── web.xml
    └── test
        └── java
```

## 部署步骤

1. **安装Java Development Kit (JDK)**: 确保你的系统上安装了JDK。

2. **安装Apache Maven**: Maven是一个项目管理和理解工具，用于Java项目构建和依赖管理。

3. **下载项目源代码**: 从版本控制系统（如Git）下载项目源代码。

4. **配置数据库**: 在`application.properties`文件中配置MySQL数据库连接信息。

5. **编译项目**: 在项目根目录下运行以下命令来编译项目：
   ```
   mvn clean package
   ```

6. **部署Web应用程序**: 将生成的`GroupSystem.war`文件部署到支持Java的Web服务器（如Tomcat）。

7. **启动Web服务器**: 启动Tomcat服务器，确保Web应用程序能够正常运行。

8. **访问应用程序**: 在浏览器中访问`http://localhost:8080/GroupSystem`，即可使用应用程序。

## 注意事项

- 确保在部署之前，数据库已经创建并且表结构已经正确。
- 在生产环境中，建议使用更强大的Web服务器和数据库服务器。
- 根据需要，可以修改`application.properties`文件中的配置项来适应不同的环境。

## 联系方式

如有任何问题或需要帮助，请联系项目维护者。

---

以上是项目的说明与部署文档，希望对你有所帮助。
