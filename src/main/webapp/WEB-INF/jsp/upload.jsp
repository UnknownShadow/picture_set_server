<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="assets/js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/ajaxfileupload.js"></script>
</head>
<body>


<label class='btn btn-danger' for='upfile'>图片上传</label>
<input type='file' <%--accept='image/png,image/jpeg,image/gif,image/jpg'--%> style='position:absolute;clip:rect(0 0 0 0);' onchange='uploadF(this)'  name='file'  id='upfile' />

<script>
    //图片上传
    function uploadF(obj) {
        $.ajaxFileUpload({
            type:"post",
            url:"small/api/uploadFile",
            secureuri:false,// 一般设置为false
            fileElementId:"upfile",// 文件上传表单的id <input type="file" id="fileUpload" name="file" />
            dataType:"multipart/form-data",
            data:{'id':'1','status':"0"},
            success:function(data){
                data=data.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">","");
                data=data.replace("</pre>","");
                alert(data);
            },
            error:function(data){
                console.log("服务器异常");
            }
        });
        return false;
    }
</script>
</body>
</html>

