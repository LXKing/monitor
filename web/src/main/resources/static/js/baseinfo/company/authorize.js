/**
 * 企业授权
 */
function resetLigerui(id) {
	$.post('../company/authorizes', {
		companyId : id
	}, function(list) {
		gridCompanyAuthorize = $("#gridCompanyAuthorize").ligerTree({
			url : '../common/authorizes',
			idFieldName : 'id',
			parentIDFieldName : 'pid',
			textFieldName : 'name',
			checkbox : true,
			enabledCompleteCheckbox : false,
			topParentIDValue : '',
			nodeWidth : 'auto',
			onSuccess : function(data) {
				for (var x = 0; x < list.length; x++) {
					var item = list[x];
					gridCompanyAuthorize.selectNode(item);
				}
			}
		});
	});
}

function save(id, callback) {
	if (!gridCompanyAuthorize)
		return;
	var list = gridCompanyAuthorize.getCheckedData();
	var ids = [];
	for (var x = 0; x < list.length; x++) {
		var row = list[x];
		ids.push(row.id);
	}

	$.post('../company/authorize', {
		companyId : id,
		list : ids
	}, function(r) {
		callback(r);
	});
}