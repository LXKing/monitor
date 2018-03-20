/**
 * 指令窗口
 */

window.instruct = {
	deviceNumber : null,
	deviceNumbers : null,
	features : null,
	currnetPad : null,
	dialogSelectSubParm : null,
	dialogFeatureParms : null,
	gridSelectSubParmParms : {
		pid : null,
		featureId : null
	},
	showFeatures : function(deviceNumber) {
		// / <summary>
		// / 显示指令界面
		// / </summary>
		this.deviceNumber = deviceNumber;
		var me = this;
		$.post('../instruct/features', {
			number : deviceNumber
		}, function(r) {
			if (typeof (r) == 'object') {
				if (r.code && r.code !== 0) {
					var tip = $.ligerDialog.tip({
						type : 'error',
						content : r.error
					});
					setTimeout(function() {
						tip.close();
					}, 3000);

					return;
				}
			}
			if (!r || r.length <= 0) {
				return;
			}
			me.features = {};
			if (!me.currnetPad) {
				me.currnetPad = new me.controller(deviceNumber, r);
				me.currnetPad.init();
			}
			me.currnetPad.features = r;
			me.currnetPad.gridInstructsParms.deviceNumber = deviceNumber;
			me.currnetPad.show();
		});
	},
	showFeatureParms : function(id) {
		var me = this;
		// / <summary>
		// / 显示功能参数界面
		// / </summary>
		var feature = this.features[id];
		if (feature.params <= 0) {
			if (me.deviceNumbers) {
				for ( var deviceNumber in me.deviceNumbers) {
					this.sendInstruct(deviceNumber, feature.id, feature.name, feature.command, null);
				}
			} else
				this.sendInstruct(this.deviceNumber, feature.id, feature.name, feature.command, null);
			return;
		}
		var divParms = $('#divParms');
		divParms.empty();
		this.currnetPad.topViewBlock = new instruct.viewBlock({
			root : divParms,
			border : '0px solid #ccc',
			feature : feature
		});

		instruct.dialogFeatureParms = $.ligerDialog.open({
			target : $('#dialogFeatureParms'),
			title : feature.name,
			width : 800,
			height : 500,
			modal : false,
			buttons : [ {
				text : '发送',
				onclick : function(button, dialog, index) {
					var list = instruct.currnetPad.topViewBlock.getValue();
					var parms = JSON.stringify(list);
					if (me.deviceNumbers) {
						for ( var deviceNumber in me.deviceNumbers) {
							me.sendInstruct(deviceNumber, feature.id, feature.name, feature.command, parms);
						}
					} else
						me.sendInstruct(me.deviceNumber, feature.id, feature.name, feature.command, parms);
				}
			}, {
				text : '关闭',
				onclick : function(button, dialog, index) {
					dialog.hide();
				}
			} ]
		});

	},
	instructDetails : function(id) {
		// / <summary>
		// / 指令详情
		// / </summary>
		var url = '../instruct/details.form';
		$.ligerDialog.open({
			height : 450,
			width : 500,
			url : url,
			urlParms : {
				id : id
			},
			title : '指令详情'
		});
	},
	sendInstruct : function(deviceNumber, featureId, name, command, params) {
		// / <summary>
		// / 发送指令
		// / </summary>
		var me = this;
		this.confirmBeforSend(featureId, function(op) {
			if (op.send === false)
				return;
			var serialNumber = common.guid();
			$.post('../instruct/send', {
				serialNumber : serialNumber,
				deviceNumber : deviceNumber,
				featureId : featureId,
				command : command,
				name : name,
				params : params,
				confirm : op.confirm
			}, function(r) {
				if (r.code) {
					common.tip('error', r.error, 3);
				} else {
					me.currnetPad.gridInstructs.addRow(r, me.currnetPad.gridInstructs.rows[0], true);
					me.currnetPad.gridInstructs.isDataChanged = false;
					common.tip('success', '已发送', 3);
				}
			});
		});
	},
	confirmBeforSend : function(featureId, callbak) {
		// / <summary>
		// / 确认发送
		// / </summary>
		var feature = this.features[featureId];
		if (feature.passwordConfirm === true) {
			$.ligerDialog.prompt('密码确认', function(yes, value) {
				callbak({
					send : yes,
					confirm : value
				});
			});
		} else if (feature.twiceConfirm === true) {
			$.ligerDialog.confirm('真地要发送：' + feature.name, function(yes) {
				callbak({
					send : yes,
					confirm : null
				});
			});
		} else
			callbak({
				send : true,
				confirm : null
			});
	},
	selectSubParm : function(dictionary, callback) {
		// / <summary>
		// / 选择子参数项
		// / 用于字典项、列表
		// / </summary>
		instruct.gridSelectSubParmParms.pid = dictionary.parm.id;
		instruct.gridSelectSubParmParms.featureId = dictionary.parm.featureId;
		var back = callback;
		var divSelectSubParm = $('#dialogSelectSubParm');
		if (divSelectSubParm.length <= 0) {
			var html = [];
			html.push('<div id="dialogSelectSubParm" style="display: none; margin: 3px; margin-right:10px;">');
			html.push('<div class="mon-toolbar">');
			html.push('<div style="float: left; width: 120px;"><div class="icon i-16-list"></div><div><b>参数列表</b></div></div>')
			html.push('<div id="btnSelectSubParm" class="mon-button"><div class="icon i-16-checked"></div><span>选择</span></div>');
			html.push('</div>');
			html.push('<div id="gridSelectSubParm"></div>');
			html.push('</div>');
			$(document.body).append($(html.join('')));
		}
		$('#btnSelectSubParm').unbind('click');
		$('#btnSelectSubParm').click(function() {
			var result = null;
			var gidSelectSubParm = $("#gridSelectSubParm").ligerGetGridManager();
			if (gidSelectSubParm) {
				var row = gidSelectSubParm.getSelected();
				result = row;
			}
			instruct.dialogSelectSubParm.hide();
			if (callback)
				callback(result);
		});
		instruct.dialogSelectSubParm = $.ligerDialog.open({
			target : $('#dialogSelectSubParm'),
			title : '功能列表',
			width : 800,
			height : 550
		});
		$.post('../instruct/params', {
			pid : dictionary.parm.id,
			featureId : dictionary.parm.featureId
		}, function(data) {
			$("#gridSelectSubParm").ligerGrid({
				columns : [ {
					display : '序号',
					name : 'index',
					width : 50
				}, {
					display : '名称',
					name : 'label',
					align : 'left'
				}, {
					display : '描述',
					name : 'description',
					width : 350,
					align : 'left'
				} ],
				data : {
					Rows : data
				},
				width : 'auto',
				height : 440,
				alternatingRow : false,
				usePager : false
			});
		});
	}
};
/* 继承 */
Function.prototype.gnssExtend = function(parent, overrides) {
	if (typeof parent != 'function')
		return this;
	// 保存对父类的引用
	this.base = parent.prototype;
	this.base.constructor = parent;
	// 继承
	var f = function() {
	};
	f.prototype = parent.prototype;
	this.prototype = new f();
	this.prototype.constructor = this;
	// 附加属性方法
	if (overrides)
		$.extend(this.prototype, overrides);
};
instruct.controller = function(deviceNumber, features) {
	// / <summary>
	// / 指令控制器
	// / </summary>
	this.topViewBlock = null;
	this.features = features;
	this.dialogInstruct = null;
	this.gridInstructs = undefined;
	this.gridInstructsParms = {
		deviceNumber : deviceNumber,
		start : new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm:ss'),
		end : new Date().addDays(1).toDateTimeString('yyyy-MM-dd hh:mm:ss'),
	};
	this.instructStartDate = undefined;
	this.instructEndDate = undefined;
	this.init = function() {
		var me = this;
		var divInstruct = $('#dialogInstruct');
		if (divInstruct.length <= 0) {
			var html = [];
			html.push('<div id="dialogInstruct" style="display: none; margin: 3px; margin-right:3px;">');
			html.push('<div id="divButtons" style="height:120px;border:1px solid #ccc;overflow:auto;"></div>');
			html.push('<div style="height:3px;"></div>');
			html.push('<div class="mon-toolbar metro-gray">');
			html.push('<div class="title mon-xxx">历史记录</div>');
			html
					.push('<div class="prompt editor-field"><input id="txtInstructStartDate" type="text" /></div><div class="prompt editor-field"><input id="txtInstructEndDate" type="text" /></div>');
			html.push('<div id="btnQueryIntructs" class="mon-button"><div class="icon i-16-search"></div><span>' + '查询' + '</span></div>');
			html.push('</div>');
			html.push('<div id="gridInstructs"></div>');

			html.push('</div>');

			$(document.body).append($(html.join('')));

			html = [];
			html.push('<div id="dialogFeatureParms" style="display: none; margin: 3px; margin-right:3px;">');
			html.push('<div id="divParms" style="height:430px; border:1px solid #ccc;overflow:auto;">');
			html.push('</div>');
			html.push('</div>');
			$(document.body).append($(html.join('')));
		}
		this.instructStartDate = $('#txtInstructStartDate').ligerDateEditor({
			showTime : true,
			format : 'yyyy-MM-dd hh:mm',
			label : '开始时间',
			labelWidth : 60,
			labelAlign : 'left',
			initValue : new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm')
		});
		this.instructEndDate = $("#txtInstructEndDate").ligerDateEditor({
			showTime : true,
			format : 'yyyy-MM-dd hh:mm',
			label : '结束时间',
			labelWidth : 60,
			labelAlign : 'left',
			initValue : new Date().addDays(1).toDateTimeString('yyyy-MM-dd hh:mm')
		});
		$('#btnQueryIntructs').click(function() {
			var dateStart = new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm:ss');
			var dateEnd = new Date().addDays(1).toDateTimeString('yyyy-MM-dd hh:mm:ss');

			var start = me.instructStartDate.getValue();
			var end = me.instructEndDate.getValue();
			if (start == null || end == null)
				return;
			else {
				dateStart = start;
				dateEnd = end;
			}

			me.gridInstructsParms.start = dateStart.toDateTimeString('yyyy-MM-dd hh:mm:ss');
			me.gridInstructsParms.end = dateEnd.toDateTimeString('yyyy-MM-dd hh:mm:ss');
			if (instruct.deviceNumbers)
				me.gridInstructsParms.deviceNumber = null;
			me.gridInstructs.loadData();
		});
	};
	this.resetButtons = function() {
		var divButtons = $('#divButtons');
		divButtons.empty();
		// 添加命令按钮
		for (var i = 0; i < this.features.length; i++) {
			var feature = this.features[i];
			instruct.features[feature.id] = feature;
			var btnId = 'btn' + feature.id;
			divButtons.append($('<input type="button" id="' + btnId + '" value="' + feature.name + '" style="height:24px;margin:5px;" />'));
			$('#' + btnId).click(function() {
				var id = this.id.substring(3);
				instruct.showFeatureParms(id);
			});
		}
	};
	this.resetGridInstructs = function() {
		// / <summary>
		// / 重设历史指令表格
		// / </summary>
		if (!this.gridInstructs) {
			this.gridInstructs = $("#gridInstructs").ligerGrid({
				columns : [ {
					display : '车牌号',
					isSort : false,
					name : 'plateNumber',
					width : 150
				}, {
					display : '时间',
					isSort : false,
					name : 'sendTime',
					width : 130,
					type : 'date',
					format : 'yyyy-MM-dd hh:mm:ss'
				}, {
					display : '命令',
					isSort : false,
					name : 'name',
					width : 150,
					align : 'left'
				}, {
					display : '结果',
					isSort : false,
					name : 'result',
					width : 300,
					align : 'left'
				}, {
					display : '操作',
					isSort : false,
					isAllowHide : false,
					width : 100,
					render : function(rowdata, rowindex, value) {
						return "<a href=\"javascript:instruct.instructDetails('" + rowdata.id + "')\">" + '详情' + "</a>";
					}
				} ],
				width : 'auto',
				height : 270,
				InWindow : false,
				alternatingRow : false,
				root : 'rows',
				record : 'total',
				pageSize : 10,
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : "../instruct/query",
				parms : this.gridInstructsParms
			});
		} else
			this.gridInstructs.reload();
	};
	this.show = function() {
		// / <summary>
		// / 显示指令界面
		// / </summary>
		if (!this.dialogInstruct)
			this.dialogInstruct = $.ligerDialog.open({
				target : $('#dialogInstruct'),
				title : '指令',
				width : 800,
				height : 500,
				modal : false
			});
		else
			this.dialogInstruct.show();
		this.resetButtons();
		this.resetGridInstructs();
	};
	this.close = function() {
		// / <summary>
		// / 关闭指令界面
		// / </summary>
		if (this.dialogInstruct)
			this.dialogInstruct.close();
	};
	/**
	 * 更新指令结果
	 */
	this.updateInstructResult = function(result) {
		if (!this.gridInstructs)
			return;
		var data = this.gridInstructs.data.rows;
		for (var x = 0; x < data.length; x++) {
			var row = data[x];
			if (row.id = result.data.id) {
				row.result = result.data.result;
				this.gridInstructs.reRender({
					rowdata : row
				});
				break;
			}
		}

	};
}
/* 视图块定义 */
instruct.viewBlock = function(options) {
	// /<summary>
	// / 视图块
	// /</summary>
	options = options || {};
	this.width = options.width || 'auto';
	this.height = options.height || 'auto';
	this.border = options.border || "0px solid #ccc";
	this.root = options.root || $(document.body);
	this.nodes = []; // 子参数
	this.parent = options.parent
	this.id = common.guid(); // 视图块ID
	this.containerID = this.id; // 容器ID
	this.feature = options.feature; // 功能项
	this.allowCheck = options.allowCheck || false; // 是否允许选中
	this.checkedParmID = undefined; // 选中的子参数
	this.checkedBackColor = 'rgb(222, 228, 236)'; // 选中背景色
	this.render();
}
/* 视图块方法 */
instruct.viewBlock.prototype = {
	expand : function() {
		// / <summary>
		// / 展开
		// / </summary>
	},
	contract : function() {
		// / <summary>
		// / 收缩
		// / </summary>
	},
	onSubParmChecked : function(id) {
		// / <summary>
		// / 子参数选中
		// / </summary>
		if (this.parent) {
			this.parent.clearChecked();
			this.parent.checkedParmID = id;
		}
	},
	onSubParmUnchecked : function(id) {
		// / <summary>
		// / 子参数取消选中
		// / </summary>
		if (this.parent) {
			this.parent.checkedParmID = undefined;
		}
	},
	unchecked : function() {
		// / <summary>
		// / 取消选中
		// / </summary>
	},
	clearChecked : function() {
		// / <summary>
		// / 取消已选中的子参数
		// / </summary>
		if (this.parent) {
			this.parent.checkedParmID = undefined;
		}
	},
	makeParms : function(parent, parm, allowcheck) {
		switch (parm.type) {
		case 1: // 普通
		{
			var simple = new instruct.stringBlock({
				parm : parm,
				allowCheck : allowcheck,
				parent : parent
			});
			parent.nodes.push(simple);
		}
			break;
		case 2: // 单选
		{
			var select = new instruct.selectBlock({
				parm : parm,
				allowCheck : allowcheck,
				parent : parent
			});
			parent.nodes.push(select);
		}
			break;
		case 3: // 字典
		{
			var dictionary = new instruct.dictionaryBlock({
				parm : parm,
				allowCheck : allowcheck,
				parent : parent
			});
			parent.nodes.push(dictionary);
		}
			break;
		case 4: // 开关
		{
			var switchs = new instruct.switchBlock({
				parm : parm,
				allowCheck : allowcheck,
				parent : parent
			});
			parent.nodes.push(switchs);
		}
			break;
		case 5: // 列表
		{
			var list = new instruct.listBlock({
				parm : parm,
				allowCheck : allowcheck,
				parent : parent
			});
			parent.nodes.push(list);
		}
			break;
		case 6: // 复合
		{
			var complex = new instruct.complexBlock({
				parm : parm,
				allowCheck : allowcheck,
				parent : parent
			});
			parent.nodes.push(complex);
		}
			break;
		}
	},
	makeParmsByPID : function(parent) {
		// / <summary>
		// / 创建参数项
		// / </summary>
		var me = this;
		var pid = "";
		var featureId = "";
		if (parent && parent.feature)
			featureId = parent.feature.id;
		if (parent && parent.parm)
			pid = parent.parm.id;

		$.post('../instruct/params', {
			pid : pid,
			featureId : featureId
		}, function(data) {
			for (var i = 0; i < data.length; i++) {
				var parm = data[i];
				me.makeParms(parent, parm, false);
			}
		});
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		this.root.append($('<div id="' + this.id + '" style="width:' + this.width + ';height:' + this.height + ';border:' + this.border
				+ '; margin:3px;"></div>'));
		if (!this.feature)
			return;
		// 添加标题
		$('#' + this.id).append($('<div id="Title' + this.id + '"><b>' + this.feature.name + '</b></div>'));
		this.makeParmsByPID(this);
	},
	getValue : function() {
		var value = {};
		for (var i = 0; i < this.nodes.length; i++) {
			var sub = this.nodes[i];
			var obj = {};
			obj[sub.parm.name] = sub.getValue();
			$.extend(value, obj);
		}
		return value;
	},
	setValue : function() {
		// / <summary>
		// / 设置值
		// / </summary>
	}
};
/** **********简单视图块************ */
instruct.stringBlock = function(options) {
	// / <summary>
	// / 简单视图块
	// / </summary>
	this.parm = options.parm;
	this.textbox = undefined;
	instruct.stringBlock.base.constructor.call(this, options);
};
instruct.stringBlock.gnssExtend(instruct.viewBlock, {
	unchecked : function() {
		$('#' + this.id).css('background-color', 'white');
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		var container = $('#' + this.parent.containerID);
		container.append($('<div id="' + this.id + '"title="' + this.parm.description + '" style="width:' + this.width + ';height:' + this.height
				+ ';border:' + this.border + '; margin:3px; padding:3px; cursor: pointer;"></div>'));
		if (this.allowCheck === true) {
			$('#' + this.id).click(function() {
				var bgcolor = $('#' + me.id).css('background-color');
				if (bgcolor == me.checkedBackColor) {
					bgcolor = 'white';
					me.onSubParmUnchecked(me.id);
				} else {
					bgcolor = me.checkedBackColor;
					me.onSubParmChecked(me.id);
				}
				$('#' + me.id).css('background-color', bgcolor);
			});
		}
		// 添加标题
		$('#' + this.id).append($('<div id="Title' + this.id + '"><b>' + this.parm.label + '</b></div>'));
		// 添加文本框
		if (this.parm.rows > 0 && this.parm.columns > 0) {
			$('#' + this.id).append($('<textarea id="Text' + this.id + '" rows="' + this.parm.rows + '" cols="' + this.parm.columns + '" />'));
		} else if (this.parm.columns > 0)
			$('#' + this.id).append($('<input id="Text' + this.id + '" type="text" style="width:' + this.parm.columns + 'px;"/>'));
		else
			$('#' + this.id).append($('<input id="Text' + this.id + '" type="text"/>'));
		this.textbox = $('#Text' + this.id);

		this.textbox.val(this.parm.defaultValue);
	},
	getValue : function() {
		return this.textbox.val();
	},
	setValue : function(value) {
		// / <summary>
		// / 设置值
		// / </summary>
		if (this.textbox)
			return this.textbox.val(value);
	}
});
/** **********单选视图块************ */
instruct.selectBlock = function(options) {
	// / <summary>
	// / 单选视图块
	// / </summary>
	this.parm = options.parm;
	this.seletct = undefined;
	instruct.selectBlock.base.constructor.call(this, options);
};
instruct.selectBlock.gnssExtend(instruct.viewBlock, {
	unchecked : function() {
		$('#' + this.id).css('background-color', 'white');
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		if (!this.parm)
			return;
		var container = $('#' + this.parent.containerID);

		container.append($('<div id="' + me.id + '"title="' + me.parm.description + '" style="width:' + me.width + ';height:' + me.height
				+ ';border:' + me.border + '; margin:3px; padding:3px; cursor: pointer;"></div>'));
		if (me.allowCheck === true) {
			$('#' + me.id).click(function() {
				var bgcolor = $('#' + me.id).css('background-color');
				if (bgcolor == me.checkedBackColor) {
					bgcolor = 'white';
					me.onSubParmUnchecked(me.id);
				} else {
					bgcolor = me.checkedBackColor;
					me.onSubParmChecked(me.id);
				}
				$('#' + me.id).css('background-color', bgcolor);
			});
		}
		// 添加标题
		$('#' + me.id).append($('<div id="Title' + me.id + '"><b>' + me.parm.label + '</b></div>'));

		$.post('../instruct/params', {
			pid : this.parm.id,
			featureId : this.parm.featureId
		}, function(r) {

			// 添加选择框
			var html = [];
			html.push('<select id="List' + me.id + '" name="List' + me.id + '">');
			for (var i = 0; i < r.length; i++) {
				var row = r[i];
				html.push('<option value="' + row.selectValue + '">' + row.label + '</option>');
			}
			html.push('</select>');

			$('#' + me.id).append($(html.join('')));

			me.seletct = $('#List' + me.id);
		});

	},
	getValue : function() {
		return this.seletct.val();
	},
	setValue : function(val) {
		if (this.seletct)
			return this.seletct.val(val);
	}
});
/** **********字典视图块************ */
instruct.dictionaryBlock = function(options) {
	// / <summary>
	// / 字典视图块
	// / </summary>
	this.parm = options.parm;
	this.containerID = undefined;
	instruct.dictionaryBlock.base.constructor.call(this, options);
};
instruct.dictionaryBlock.gnssExtend(instruct.viewBlock, {
	clearChecked : function() {
		if (this.checkedParmID)
			;
		for (var i = 0; i < this.nodes.length; i++) {
			var node = this.nodes[i];
			if (node.id == this.checkedParmID) {
				node.unchecked();
				break;
			}
		}
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		var container = $('#' + this.parent.containerID);
		container.append($('<div id="' + this.id + '" style="width:' + this.width + ';height:' + this.height + ';border:' + this.border
				+ '; margin:3px;"></div>'));

		var html = [];
		html.push('<div id="Title' + this.id + '">');
		html.push('<b>' + this.parm.label + '</b>');
		html.push(' <div style="float:right;">')
		html.push('     <input id="btnAddItem' + this.id + '" type="button" value="' + '添加' + '" style="margin-left:3px;" />');
		html.push('     <input id="btnDelItem' + this.id + '" type="button" value="' + '移除' + '" style="margin-left:3px;" />');
		html.push(' </div>');
		html.push('</div><div style="clear:both;"></div>');
		$('#' + this.id).append($(html.join('')));
		// 添加容器
		$('#' + this.id).append($('<div id="container' + this.id + '" style="height:380px;overflow:auto;border:1px solid #ccc;"></div>'));
		this.containerID = 'container' + this.id;

		$('#btnAddItem' + this.id).click(function() {
			me.addItem();
		});
		$('#btnDelItem' + this.id).click(function() {
			me.removeItem();
		});
	},
	setValue : function(val) {
		// / <summary>
		// / 设置值
		// / </summary>
		if (this.seletct)
			return this.seletct.val(val);
	},
	addItem : function() {
		// / <summary>
		// / 添加字典项
		// / </summary>
		var me = this;
		new instruct.selectSubParm(this, function(row) {
			me.makeParms(me, row, true);
		});
	},
	removeItem : function() {
		// / <summary>
		// / 移除字典项
		// / </summary>
		if (!this.checkedParmID)
			return;
		var node = undefined;
		var index = -1;
		for (var i = 0; i < this.nodes.length; i++) {
			var item = this.nodes[i];
			if (item.id == this.checkedParmID) {
				index = i;
				node = item;
				break;
			}
		}
		if (index > -1)
			this.nodes.splice(index, 1);

		var container = $('#' + this.checkedParmID);
		container.remove();
	},
	getValue : function() {
		var list = [];
		for (var i = 0; i < this.nodes.length; i++) {
			var sub = this.nodes[i];
			var obj = {};
			obj[sub.parm.dictionaryKey] = sub.getValue();
			list.push(obj);
		}
		return list;
	}
});
/** **********开关视图块************ */
instruct.switchBlock = function(options) {
	// / <summary>
	// / 开关视图块
	// / </summary>
	this.parm = options.parm;
	this.containerID = undefined;
	instruct.switchBlock.base.constructor.call(this, options);
};
instruct.switchBlock.gnssExtend(instruct.viewBlock, {
	unchecked : function() {
		// / <summary>
		// / 取消选中
		// / </summary>
		$('#' + this.id).css('background-color', 'white');
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		if (!this.parm)
			return;
		var container = $('#' + this.parent.containerID);

		container.append($('<div id="' + me.id + '"title="' + me.parm.description + '" style="width:' + me.width + ';height:' + me.height
				+ ';border:' + me.border + '; margin:3px; padding:3px; cursor: pointer;"></div>'));
		if (me.allowCheck === true) {
			$('#' + me.id).click(function() {
				var bgcolor = $('#' + me.id).css('background-color');
				if (bgcolor == me.checkedBackColor) {
					bgcolor = 'white';
					me.onSubParmUnchecked(me.id);
				} else {
					bgcolor = me.checkedBackColor;
					me.onSubParmChecked(me.id);
				}
				$('#' + me.id).css('background-color', bgcolor);
			});
		}
		// 添加标题
		$('#' + me.id).append($('<div id="Title' + me.id + '"><b>' + me.parm.label + '</b></div>'));

		$.post('../instruct/params', {
			pid : this.parm.id,
			featureId : this.parm.featureId
		}, function(r) {
			if (r.result && r.result === false) {
				$.ligerDialog.error(r.message);
				return;
			}

			// 添加容器
			$('#' + me.id).append($('<div id="container' + me.id + '" style="border:1px solid #ccc;"></div>'));
			// 添加复选框
			var html = [];
			for (var i = 0; i < r.length; i++) {
				var row = r[i];

				html.push('<label style="margin-left:5px;">');
				html.push('<input name="checkbox' + me.id + '" type="checkbox" value="' + row.switchBit + '" />' + row.label);
				html.push('</label>');
			}

			$('#container' + me.id).append($(html.join('')));
		});
	},
	getValue : function() {
		var switchs = [];
		$('input[name="checkbox' + this.id + '"]:checked').each(function() {
			switchs.push($(this).val());
		});

		return switchs;
	}
});
/** **********列表视图块************ */
instruct.listBlock = function(options) {
	// / <summary>
	// / 列表视图块
	// / </summary>
	this.parm = options.parm;
	this.containerID = undefined;
	instruct.listBlock.base.constructor.call(this, options);
};
instruct.listBlock.gnssExtend(instruct.viewBlock, {
	clearChecked : function() {
		if (this.checkedParmID)
			for (var i = 0; i < this.nodes.length; i++) {
				var node = this.nodes[i];
				if (node.id == this.checkedParmID) {
					node.unchecked();
					break;
				}
			}
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		var container = $('#' + this.parent.containerID);
		container.append($('<div id="' + this.id + '"title="' + me.parm.description + '" style="width:' + this.width + ';height:' + this.height
				+ ';border:' + this.border + '; margin:3px;"></div>'));
		if (me.allowCheck === true) {
			$('#' + me.id).click(function() {
				var bgcolor = $('#' + me.id).css('background-color');
				if (bgcolor == me.checkedBackColor) {
					bgcolor = 'white';
					me.onSubParmUnchecked(me.id);
				} else {
					bgcolor = me.checkedBackColor;
					me.onSubParmChecked(me.id);
				}
				$('#' + me.id).css('background-color', bgcolor);
			});
		}
		var html = [];
		html.push('<div id="Title' + this.id + '">');
		html.push('<b>' + this.parm.label + '</b>');
		html.push(' <div style="float:right;">')
		html.push('     <input id="btnAddItem' + this.id + '" type="button" value="' + '添加' + '" style="margin-left:3px;" />');
		html.push('     <input id="btnDelItem' + this.id + '" type="button" value="' + '移除' + '" style="margin-left:3px;" />');
		html.push(' </div>');
		html.push('</div><div style="clear:both;"></div>');
		$('#' + this.id).append($(html.join('')));
		// 添加容器
		$('#' + this.id).append($('<div id="container' + this.id + '" style="height:345px;overflow:auto;border:1px solid #ccc;"></div>'));
		this.containerID = 'container' + this.id;

		$('#btnAddItem' + this.id).click(function() {
			me.addItem();
		});
		$('#btnDelItem' + this.id).click(function() {
			me.removeItem();
		});
	},
	setValue : function(val) {
		// / <summary>
		// / 设置值
		// / </summary>
		if (this.seletct)
			return this.seletct.val(val);
	},
	addItem : function() {
		// / <summary>
		// / 添加列表项
		// / </summary>
		var me = this;
		$.post('../instruct/params', {
			pid : this.parm.id,
			featureId : this.parm.featureId
		}, function(r) {
			if (r && r.length > 0)
				me.makeParms(me, r[0], true);
		});
	},
	removeItem : function() {
		// / <summary>
		// / 移除列表项
		// / </summary>
		if (!this.checkedParmID)
			return;
		var node = undefined;
		var index = -1;
		for (var i = 0; i < this.nodes.length; i++) {
			var item = this.nodes[i];
			if (item.id == this.checkedParmID) {
				index = i;
				node = item;
				break;
			}
		}
		if (index > -1)
			this.nodes.splice(index, 1);

		var container = $('#' + this.checkedParmID);
		container.remove();
	},
	getValue : function() {
		var list = [];
		for (var i = 0; i < this.nodes.length; i++) {
			var sub = this.nodes[i];
			list.push(sub.getValue());
		}
		return list;
	}
});
/** **********复合视图块************ */
instruct.complexBlock = function(options) {
	// / <summary>
	// / 复合视图块
	// / </summary>
	this.parm = options.parm;
	this.containerID = undefined;
	instruct.complexBlock.base.constructor.call(this, options);
};
instruct.complexBlock.gnssExtend(instruct.viewBlock, {
	unchecked : function() {
		// / <summary>
		// / 取消选中
		// / </summary>
		$('#' + this.id).css('background-color', 'white');
	},
	render : function() {
		// / <summary>
		// / 显示输出
		// / </summary>
		var me = this;
		if (!this.parm)
			return;
		var container = $('#' + this.parent.containerID);
		$.post('../instruct/params', {
			pid : this.parm.id,
			featureId : this.parm.featureId
		}, function(r) {
			if (r.result && r.result === false) {
				$.ligerDialog.error(r.message);
				return;
			}
			container.append($('<div id="' + me.id + '"title="' + me.parm.description + '" style="width:' + me.width + ';height:' + me.height
					+ ';border:' + me.border + '; margin:3px; padding:3px; cursor: pointer;"></div>'));
			if (me.allowCheck === true) {
				$('#' + me.id).click(function() {
					var bgcolor = $('#' + me.id).css('background-color');
					if (bgcolor == me.checkedBackColor) {
						bgcolor = 'white';
						me.onSubParmUnchecked(me.id);
					} else {
						bgcolor = me.checkedBackColor;
						me.onSubParmChecked(me.id);
					}
					$('#' + me.id).css('background-color', bgcolor);
				});
			}
			// 添加标题
			$('#' + me.id).append($('<div id="Title' + me.id + '"><b>' + me.parm.label + '</b></div>'));
			// 添加容器
			$('#' + me.id).append($('<div id="container' + me.id + '" style="border:1px solid #ccc;"></div>'));
			me.containerID = 'container' + me.id;
			for (var i = 0; i < r.length; i++) {
				var row = r[i];

				me.makeParms(me, row, false);
			}
		});
	},
	getValue : function() {
		var obj = {};
		for (var i = 0; i < this.nodes.length; i++) {
			var sub = this.nodes[i];
			obj[sub.parm.name] = sub.getValue();
		}

		return obj;
	}
});