<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>统计</title>
    <script src="assets/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
当前在线人数总计<div id="sum" th:text="${num}" ></div> <br/>
具体人员为：<div id="users" th:text="${users}"></div> <br/>
发送消息：请输入推送内容<input type="text" id="msg"/>  <br/>
请输入发送人昵称<input type="text" id="username"/>
<span>(如果发送多个用户，用户昵称之间用逗号隔开)</span>  <br/>
<input type="button" value="发送" onclick="sendMsg()"/>        <br/>
<input type="button" value="全部发送" onclick="sendAll()"/>
<script type="text/javascript">
    function sendMsg(){
        var user = $("#username").val();
        var msg = $("#msg").val();
        if(msg!=null){
            $.ajax({
                method: 'get',
                url: '/sendmsg',
                data:{
                    username: user,
                    msg:msg
                },
                success:function(data) {
                    console.log(data);
                }
            })
        }else{
            alert("请填写要发送的用户昵称或者发送内容");
        }
    }

    function sendAll(){
        var msg = $("#msg").val();
        if(msg!=null){
            $.ajax({
                method: 'get',
                url: '/sendAll',
                data:{
                    msg:msg
                },
                success:function(data) {
                    console.log(data);
                }
            })
        }else{
            alert("请填写要发送的内容");
        }
    }
</script>
</body>
</html>
