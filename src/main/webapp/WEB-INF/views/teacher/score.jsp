<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>评分任务</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
    <h1>任务评分页面</h1>

    <!-- 显示学生信息 -->
    <div class="student-info">
        <p><strong>学生姓名：</strong>${fullname}</p>
        <p><strong>提交时间：</strong>${submissionDate}</p>
        <p><strong>任务描述：</strong>${description}</p>
        <p><strong>提交内容：</strong>${content}</p>
        <p><strong>分数：</strong>${score}</p>
    </div>

    <!-- 更新分数 -->
    <div class="form-group">
        <label for="scoreInput">更新分数：</label>
        <input type="number" id="scoreInput" min="0" max="100">
        <button id="submitScoreButton">提交分数</button>
    </div>

    <!-- 文件下载 -->
    <div class="download-file">
        <button id="downloadFileButton">下载文件</button>
    </div>
</div>

<script>
    $(document).ready(function() {

        // 提交分数
        $('#submitScoreButton').click(function() {
            var newScore = $('#scoreInput').val();
            if (!newScore) {
                alert("请输入有效的分数！");
                return;
            }

            $.ajax({
                type: "POST",
                url: "/teacher/submitScore", // 后端接口
                contentType: "application/json",
                data: newScore,
                success: function(response) {
                    alert("分数已更新！");
                    // Optionally, you can update the page with the new score
                    $('#score').text(newScore);
                },
                error: function(error) {
                    console.error("提交失败", error);
                    alert("提交失败，请重试！");
                }
            });
        });

        // 文件下载
        $('#downloadFileButton').click(function() {
            $.ajax({
                type: "GET",
                url: "/file/download", // 后端下载文件接口
                xhrFields: {
                    responseType: 'blob'  // 设置响应类型为二进制数据
                },
                success: function(response) {
                    // 使用 Blob 创建文件链接
                    var blob = response;
                    var link = document.createElement('a');
                    var fileName = 'homeworkFile.zip';  // 默认文件名，可以通过后端动态返回的文件名替代

                    // 创建下载链接并触发下载
                    link.href = URL.createObjectURL(blob);
                    link.download = fileName;
                    link.click();
                },
                error: function(error) {
                    console.error("文件下载失败", error);
                    alert("下载失败: " + (error.responseText || "未知错误"));
                }
            });
        });


    });
</script>
</body>
</html>
