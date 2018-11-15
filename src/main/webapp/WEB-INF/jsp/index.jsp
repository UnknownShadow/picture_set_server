<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script rel="script" src="assets/js/jquery-1.9.1.min.js"></script>
<script src="assets/js/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
<html  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>客户端首页</title>
    <script th:src="@{../jquery.min.js}"></script>
</head>
<body>

请输入您的昵称<input type="text" id="username"/>
<input type="button" value="连接" onclick="connect()"/>   <br/>
请填写要发送的内容<input type="text" id="writeMsg"/>
<input type="button" value="发送" onclick="sendMsg()"/>

<script type="text/javascript">
    var ws = null;
    var username = $("#username").val()
    function connect(){
        if(username!=null){
            if ('WebSocket' in window){
                ws = new WebSocket("ws:192.168.0.111:8080/socketServer/"+$("#username").val());
            }
            else if ('MozWebSocket' in window){
                ws = new MozWebSocket("ws://192.168.0.111:8080/socketServer/"+$("#username").val());
            }
            else{
                alert("该浏览器不支持websocket");
            }


            ws.onmessage = function(evt) {
                alert(evt.data);
            };

            ws.onclose = function(evt) {
                alert("连接中断");
            };

            ws.onopen = function(evt) {
                alert("连接成功");
            };
        }else{
            alert("请输入您的昵称");
        }
    }

    function sendMsg() {
        ws.send($("#writeMsg").val());
    }
</script>
</body>
</html>
