/**
 * 角色业授权
 */
function resetLigerui(roleId) {
	$.post('../role/authorizes', {
		roleId : roleId
	}, function(list) {
		gridRoleAuthorize = $("#gridRoleAuthorize").ligerTree({
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
					gridRoleAuthorize.selectNode(item);
				}
			}
		});
	});
}

function selectNodeOnly() {
	if (!gridRoleAuthorize)
		return;
	var node = gridRoleAuthorize.getSelected();
	if (node)
		gridRoleAuthorize.selectNode(node.data);
}

function save(id, callback) {
	if (!gridRoleAuthorize)
		return;
	var list = gridRoleAuthorize.getCheckedData();
	var ids = [];
	for (var x = 0; x < list.length; x++) {
		var row = list[x];
		ids.push(row.id);
	}

	$.post('../role/authorize', {
		roleId : id,
		list : ids
	}, function(r) {
		callback(r);
	});
}