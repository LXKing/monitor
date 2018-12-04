/**
 * 编辑地图图层
 */
function validate() {
	var v = $('form').validate();

	var result = v.checkForm();
	v.showErrors();
	return result;
}