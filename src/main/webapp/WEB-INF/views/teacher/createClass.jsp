<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>创建班级</title>
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

    input[type="text"], textarea {
      width: 100%;
      padding: 12px;
      margin: 8px 0;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 16px;
    }

    button {
      width: 100%;
      padding: 12px;
      background-color: #6f5ef8;
      border: none;
      color: white;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
    }

    button:hover {
      background-color: #7d53cc;
    }
  </style>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
  <h2 class="title">创建班级</h2>

  <form id="createClassForm">
    <div class="form-group">
      <label for="className">班级名称</label>
      <input type="text" id="className" name="className" required>
    </div>

    <div class="form-group">
      <label for="classDescription">班级描述</label>
      <textarea id="classDescription" name="classDescription" rows="4" required></textarea>
    </div>

    <div class="form-group">
      <button type="submit">创建班级</button>
    </div>
  </form>
</div>

<script>
  document.getElementById('createClassForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 防止表单提交刷新页面

    const className = document.getElementById('className').value;
    const classDescription = document.getElementById('classDescription').value;

    if(className && classDescription) {
      // 创建班级的对象
      const createClassDO = {
        className: className,
        description: classDescription
      };

      // 使用 AJAX 将数据发送到服务器
      $.ajax({
        type: "POST",
        url: '/teacher/createClass',  // 后端接口，处理班级创建
        data: JSON.stringify(createClassDO),
        contentType: "application/json",
        success: function(result) {
            alert(result.data);
            // 跳转到班级列表或其他页面
            window.location.href = "/user/switchToTeacher";  // 跳转到教师页面
        },
        error: function() {
          alert("请求失败，请检查网络连接。");
        }
      });
    } else {
      alert("请填写所有字段！");
    }
  });
</script>

</body>
</html>
