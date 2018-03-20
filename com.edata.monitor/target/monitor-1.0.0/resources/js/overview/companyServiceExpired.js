/**
 * 企业服务到期
 */
window.companyServiceExpired = {
	query : function(days) {
		$.post('../overview/companyServiceExpired', {
			days : days
		}, function(list) {
			$('#gridCompanyExpired').ligerGrid({
				columns : [ {
					display : '简称',
					name : 'shortName',
					align : 'left',
					width : 100
				}, {
					display : '全称',
					name : 'fullName',
					align : 'left',
					width : 200
				}, {
					display : '办公地址',
					name : 'officeAddress',
					align : 'left',
					width : 250
				}, {
					display : '服务开始时间',
					name : 'serviceStartDate',
					align : 'left',
					type : 'date',
					width : 100
				}, {
					display : '服务结束时间',
					name : 'serviceEndDate',
					align : 'left',
					type : 'date',
					width : 100
				}, {
					display : '值班电话',
					name : 'ondutyPhone',
					width : 80
				}, {
					display : '入网时间',
					name : 'createTime',
					type : 'date',
					width : 100
				}, {
					display : '说明',
					name : 'remark',
					align : 'left',
					width : 100
				} ],
				data : {
					rows : list
				},
				root : 'rows',
				width : '100%',
				height : '100%'
			});
		});
	}
}
