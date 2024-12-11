<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>任务管理</title>
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
            max-width: 600px;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #6f5ef8;
            color: white;
        }

        td {
            background-color: #f9f9f9;
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
    <h2 class="title">任务列表</h2>

    <div class="class-name">
        班级名称: <span id="className">${sessionScope.className}</span>
    </div>

    <!-- Table to display tasks -->
    <table id="taskTable">
        <thead>
        <tr>
            <th>任务名称</th>
            <th>描述</th>
            <th>截止日期</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <!-- Dynamically generated rows will go here -->
        </tbody>
    </table>

    <button id="goToTaskButton">布置新任务</button>
</div>

<script>
    // 页面加载时请求任务列表
    $(document).ready(function() {
        getTaskList();
    });

    // 获取任务列表
    function getTaskList() {
        $.ajax({
            type: "GET",
            url: "/teacher/getTaskList",  // 后端接口
            success: function(result) {
                console.log("接口返回数据：", result.data); // 调试信息
                try {
                    // 解析 JSON 数据
                    var data = JSON.parse(result.data);

                    if (data.success && data.data && Array.isArray(data.data)) {
                        // 清空原有表格行
                        $('#taskTable tbody').empty();

                        // 遍历任务列表并动态生成表格行
                        data.data.forEach(function(taskItem) {
                            var row = '<tr onclick="GoIntoTask(\'' + taskItem.taskName + '\')">';
                            row += '<td>' + taskItem.taskName + '</td>';
                            row += '<td>' + taskItem.description + '</td>';
                            row += '<td>' + taskItem.dueDate + '</td>';
                            row += '<td>' + taskItem.createdAt + '</td>';
                            row += '</tr>';
                            $('#taskTable tbody').append(row);
                        });

                        console.log("任务列表渲染完成");
                    } else {
                        alert("未能获取任务列表或数据格式错误");
                    }
                } catch (error) {
                    console.error("解析JSON失败", error);
                    alert("解析数据失败");
                }
            },
            error: function(error) {
                console.error("获取任务列表失败", error);
                alert("获取任务列表失败");
            }
        });
    }

    function GoIntoTask(taskName) {
        //const jsonData = JSON.stringify({ className: className });  // 转换为JSON字符串

        $.ajax({
            type: "POST",
            url: "/teacher/submitTaskName",  // 后端接口
            data: taskName,
            contentType: "application/json",
            success: function (response) {
                window.location.href = "/teacher/switchToTask";  // 跳转到课程页面
            }
        });
    }
    // 点击布置新任务按钮，跳转到任务布置页面
    document.getElementById('goToTaskButton').addEventListener('click', function() {
        window.location.href = "/teacher/switchToCreateTask";  // 跳转到任务布置页面
    });
</script>

</body>
</html>
