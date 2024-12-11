<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>注册</title>
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
      max-width: 450px;
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

    input[type="text"], input[type="password"], input[type="email"], input[type="tel"], select {
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

    .input-wrapper input, .input-wrapper select {
      padding-left: 35px;
    }

    .result-message {
      margin-top: 20px;
      text-align: center;
      font-size: 16px;
      color: #4caf50;
    }
  </style>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="container">
  <h2 class="title">注册账户</h2>

  <!-- Register Form -->
  <form id="signUpForm" method="POST" onsubmit="handleSignUp(event)">
    <div class="form-group">
      <div class="input-wrapper">
        <i class="fas fa-user icon"></i>
        <input type="text" name="username" id="username" placeholder="账号" required>
      </div>
    </div>
    <div class="form-group">
      <div class="input-wrapper">
        <i class="fas fa-user icon"></i>
        <input type="text" name="fullname" id="fullname" placeholder="姓名" required>
      </div>
    </div>

    <div class="form-group">
      <label for="gender">Gender</label>
      <select name="gender" id="gender" required>
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
      </select>
    </div>

    <div class="form-group">
      <div class="input-wrapper">
        <i class="fas fa-phone icon"></i>
        <input type="tel" name="phone" id="phone" placeholder="手机号" required>
      </div>
    </div>

    <div class="form-group">
      <div class="input-wrapper">
        <i class="fas fa-envelope icon"></i>
        <input type="email" name="email" id="email" placeholder="邮箱地址" required>
      </div>
    </div>

    <div class="form-group">
      <div class="input-wrapper">
        <i class="fas fa-lock icon"></i>
        <input type="password" name="password" id="password" placeholder="密码" required>
      </div>
    </div>

    <!-- New Role Selection -->
    <div class="form-group">
      <label for="role">Role</label>
      <select name="role" id="role" required>
        <option value="teacher">Teacher</option>
        <option value="student">Student</option>
      </select>
    </div>

    <div class="form-group">
      <input type="submit" value="注册">
    </div>
  </form>

  <div class="toggle-links">
    <span>已有账户？</span> <a href="#" id="goToLoginButton">登录</a>
  </div>

  <!-- Result Message -->
  <div id="resultMessage" class="result-message"></div>
</div>

<script>
  document.getElementById("goToLoginButton").addEventListener("click", function() {
    // 当点击按钮时，跳转到指定的 URL
    window.location.href = "/user/switchToLog";  // 触发跳转到后端的控制器映射 URL
  });


  function handleSignUp(event) {
    event.preventDefault();  // 阻止默认表单提交

    // 获取表单数据
    const formData = new FormData(document.getElementById('signUpForm'));
    var userData = {
      username: formData.get('username'),
      fullname: formData.get('fullname'),
      gender: formData.get('gender'),
      phone: formData.get('phone'),
      email: formData.get('email'),
      password: formData.get('password'),
      role: formData.get('role'),
    };
    const jsonData = JSON.stringify(userData)
    console.log(jsonData);
    // 使用 $.ajax 发送请求
    $.ajax({
      type:"post",
      url: '/user/signUp',  // 后端接口
      data:jsonData,
      contentType: "application/json",
      success:function(result){
        alert(result.data);
      }
    });
  }

</script>

</body>
</html>
