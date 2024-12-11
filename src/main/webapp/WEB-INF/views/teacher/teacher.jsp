<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            text-align: center;
            position: relative;
        }
        .username-box {
            position: absolute;
            top: 15px;
            right: 20px;
            font-size: 18px;
            color: white;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group a {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            text-decoration: none;
            border-radius: 5px;
        }
        .form-group a:hover {
            background-color: #45a049;
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
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<header>
    <h1>Teacher Dashboard</h1>
    <!-- Teacher's username display -->
    <div class="username-box">
        Welcome, <span id="username">${sessionScope.username}</span>
    </div>
</header>

<div class="container">
    <h2>Class List</h2>

    <!-- Button to create a new class -->
    <div class="form-group">
        <a href="#" id="createClass">创建班级</a>
    </div>

    <!-- Table to display classes -->
    <table id="classTable">
        <thead>
        <tr>
            <th>班级名称</th>
            <th>描述</th>
        </tr>
        </thead>
        <tbody>
        <!-- Dynamically generated table rows will go here -->
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // 页面加载时请求班级列表
    $(document).ready(function () {
        getClassList();
    });

    // 获取班级列表
    function getClassList() {
        $.ajax({
            type: "GET",
            url: "/teacher/getClassList",  // 后端接口
            success: function (result) {
                console.log("接口返回数据：", result.data); // 调试信息

                try {
                    // 尝试将JSON字符串解析为对象
                    var data = JSON.parse(result.data);

                    if (data.success && data.data && Array.isArray(data.data)) {
                        // 清空原有表格行
                        $('#classTable tbody').empty();

                        // 遍历班级列表并动态生成表格行
                        data.data.forEach(function (classItem) {
                            var row = '<tr onclick="goToClass(\'' + classItem.className + '\')">';
                            row += '<td>' + classItem.className + '</td>';
                            row += '<td>' + classItem.description + '</td>';
                            row += '</tr>';
                            $('#classTable tbody').append(row);
                        });

                        console.log("班级列表渲染完成");
                    } else {
                        alert("未能获取班级列表或数据格式错误");
                    }
                } catch (error) {
                    console.error("解析JSON失败", error);
                    alert("解析数据失败");
                }
            },
            error: function (error) {
                console.error("获取班级列表失败", error);
                alert("获取班级列表失败");
            }
        });
    }

    // 点击对应班级的名字，跳转到switchToClass方法，并将班级名称传递到session
    function goToClass(className) {
        //const jsonData = JSON.stringify({ className: className });  // 转换为JSON字符串

        $.ajax({
            type: "POST",
            url: "/teacher/submitClass",  // 后端接口
            data: className,
            contentType: "application/json",
            success: function (response) {
                window.location.href = "/teacher/switchToClass";  // 跳转到课程页面
            }
        });
    }

    // 点击创建班级按钮，跳转到创建班级页面
    document.getElementById("createClass").addEventListener("click", function () {
        window.location.href = "/teacher/switchToCreate";
    });
</script>


</body>
</html>
