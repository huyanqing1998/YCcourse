<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	img{
		width: 300px;
	}
</style>
</head>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function uploadImgs(){
		var formData = new FormData(ff);
		$.ajax({
			type : "POST",
			url : 'upload_ajax.do',
			data : formData,
			processData : false,
			contentType : false,
			success : function(data){
				img1.src = data[0];
				img2.src = data[1];
				img3.src = data[2];
			}
		});
	}	
</script>
<body>
     	<h1>Please upload a file</h1>
        <form id="ff" method="post" action="upload.do" enctype="multipart/form-data">
            <input type="text" name="name"/>
            <input type="file" name="file" multiple="multiple"/>            
            <input type="submit" value="表单文件上传"/>
            <input type="button" value="AJAX文件上传" onclick="uploadImgs()">
        </form>
        
        <img id="img1" alt="" src="">
        <img id="img2" alt="" src="">
        <img id="img3" alt="" src="">
</body>
</html>