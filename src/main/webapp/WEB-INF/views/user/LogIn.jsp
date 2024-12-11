<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录 / 注册</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #6f5ef8, #7d53cc);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 400px;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .title {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%;
            padding: 12px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #6f5ef8;
            border: none;
            color: #fff;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #7d53cc;
        }

        .toggle-links {
            text-align: center;
            margin-top: 10px;
        }

        .toggle-links a {
            color: #6f5ef8;
            text-decoration: none;
            font-size: 14px;
        }

        .toggle-links a:hover {
            text-decoration: underline;
        }

        .icon {
            position: absolute;
            top: 15px;
            left: 12px;
            color: #aaa;
        }

        .input-wrapper {
            position: relative;
        }

        .input-wrapper input {
            padding-left: 35px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
    <h2 class="title">欢迎来到我们的平台</h2>

    <!-- Login Form -->
    <form id="loginForm" action="#" method="POST" onsubmit="handleLogIn(event)">
        <div class="form-group">
            <div class="input-wrapper">
                <i class="fas fa-user icon"></i>
                <input type="text" name="username" placeholder="用户名" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-wrapper">
                <i class="fas fa-lock icon"></i>
                <input type="password" name="password" placeholder="密码" required>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" value="登录">
        </div>
    </form>

    <div class="toggle-links">
        <span>还没有账户？</span> <a href="#" id="goToSignButton">注册</a>
    </div>
</div>
<script>
    document.getElementById("goToSignButton").addEventListener("click", function() {
        // 当点击按钮时，跳转到指定的 URL
        window.location.href = "/user/switchToSign";  // 触发跳转到后端的控制器映射 URL
    });

    function handleLogIn(event) {
        event.preventDefault();  // 阻止默认表单提交

        // 获取表单数据
        const formData = new FormData(document.getElementById('loginForm'));
        var userData = {
            username: formData.get('username'),
            password: formData.get('password'),
        };
        const jsonData = JSON.stringify(userData)
        console.log(jsonData);
        // 使用 $.ajax 发送请求
        $.ajax({
            type:"post",
            url: '/user/logIn',  // 后端接口
            data:jsonData,
            contentType: "application/json",
            success: function(result) {
                if (result.data === "teacher") {
                    window.location.href = "/user/switchToTeacher";  // 跳转到教师页面
                } else if (result.data === "student") {
                    window.location.href = "/user/switchToStudent";  // 跳转到学生页面
                } else {
                    alert(result.data);  // 如果 result.data 不是 "teacher" 或 "student"，则弹出结果数据
                }
            }
        });
    }
</script>
</body>
</html>
