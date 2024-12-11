<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>提交作业</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f7f6;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .task-info {
            margin-bottom: 20px;
        }

        .task-info label {
            font-weight: bold;
        }

        .task-info p {
            margin: 5px 0;
            color: #555;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
        }

        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }

        .form-group textarea {
            height: 150px;
        }

        .form-group input[type="file"] {
            padding: 5px;
        }

        .form-group button {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #45a049;
        }

        .task-info p span {
            color: #333;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="container">
    <h2>提交作业</h2>

    <!-- Display Task Information -->
    <div class="task-info">
        <label>任务名称:</label>
        <p><span>${taskName}</span></p>

        <label>任务描述:</label>
        <p><span>${description}</span></p>

        <label>截止日期:</label>
        <p><span>${dueDate}</span></p>

        <label>创建时间:</label>
        <p><span>${createdAt}</span></p>

        <label>分数:</label>
        <p><span>${score}</span></p>
    </div>

    <!-- Form to submit the task -->
    <form id="submitTaskForm" enctype="multipart/form-data">
        <div class="form-group">
            <label for="content">作业内容:</label>
            <textarea id="content" name="content" required></textarea>
        </div>

        <div class="form-group">
            <label for="file">选择文件:</label>
            <input type="file" id="file" name="file" required>
        </div>

        <div class="form-group">
            <button type="submit">提交作业</button>
        </div>
    </form>
</div>

<script>
    // 提交表单时触发
    document.getElementById("submitTaskForm").onsubmit = function(event) {
        event.preventDefault(); // 防止默认表单提交

        // 获取作业内容
        const content = document.getElementById('content').value;

        // 获取文件
        const file = document.getElementById('file').files[0];
        if (!file) {
            alert("请选择一个文件！");
            return;
        }

        // 使用 FormData 来处理文件上传和文本数据
        const formData = new FormData();
        formData.append("content", content);  // 添加文本内容
        formData.append("file", file);        // 添加文件

        // 使用 AJAX 提交数据
        $.ajax({
            type: "POST",
            url: "/student/submitTask", // 后端接口
            data: formData,
            processData: false,  // 禁止 jQuery 自动处理数据
            contentType: false,  // 不设置 Content-Type，自动处理文件上传
            success: function(result) {
                alert(result.data); // 显示成功信息
            },
            error: function(xhr, status, error) {
                console.error("上传失败", error);
                alert("上传失败，请重试！");
            }
        });
    };
</script>

</body>
</html>
