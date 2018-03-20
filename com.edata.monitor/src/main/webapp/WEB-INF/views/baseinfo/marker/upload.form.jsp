<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>上传车辆图标</title>
<style type="text/css">
.submit {
	background-color: #FFF;
	border: 1px solid #CDCDCD;
	height: 24px;
	width: 70px;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/marker/upload.js"></script>
</head>
<body>
	<div style="padding: 5px;">
		<form method="POST" enctype="multipart/form-data">
			<div>提示：要求4张32x32像素的图片并排合成一张图片(尺寸：128 × 32)，4张图片分别表示4种状态，按顺序分别为：离线、怠速、行驶、报警。</div>
			<div>示例：</div>
			<img src="../resources/icons/00.png" />
			<div style="height: 20px;"></div>
			<div class="editor-label">请选取图标文件：(*.png、*.jpg、*.gif)</div>
			<div class="editor-field">
				<input type="file" name="file" id="file" style="width: 400px;" />
			</div>
			<div style="height: 20px;"></div>
			<div class="editor-field mon-right">
				<input class="submit" type="submit" value="上传">
			</div>
		</form>
	</div>
</body>
</html>
