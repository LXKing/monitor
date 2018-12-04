/**
 * 上传车辆图标
 */
$(function() {
	$('form').on('submit', function(e) {
		e.preventDefault(); // <-- important
		$(this).ajaxSubmit({
			success : function(data) { // 提交成功的回调函数
				var result = common.checkData(data, true, '文件上传成功！');
				if (result === true) {
					parent.marker.refresh();
					setTimeout(function() {
						parent.marker.dialog.close();
					}, 3000);
				}
			}
		});
	});
})