/**
 * 框架调用脚本
 */
$.ajaxSetup({
	cache : false
});
window.common = {
	debug : function(obj) {
		obj.toString();
	},
	guid : function() {
		var guid = "";
		for (var i = 1; i <= 32; i++) {
			var n = Math.floor(Math.random() * 16.0).toString(16);
			guid += n;
			if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
				guid += "-";
		}
		return guid;
	},
	bitsToNumber : function(bits) {
		/**
		 * 将;分隔的开关位合成32位整数
		 */
		if (!bits)
			return 0;
		var flag = 0;
		var list = bits.split(';');
		for (var i = 0; i < list.length; i++) {
			var item = list[i];
			var bit = parseInt(item);
			flag = flag | (1 << bit);
		}

		return flag;
	},
	/**
	 * 将32位整数按位以;分隔组成字符串
	 */
	numberToBits : function(number) {

		var bits = [];
		for (var i = 0; i < 32; i++) {
			if ((number >>> i & 0x01) == 0x01)
				bits.push(i);
		}

		return bits.join(';');
	},
	/**
	 * 取几位小数点
	 */
	round : function(number, decimal) {
		return Math.round(number * Math.pow(10, decimal)) / Math.pow(10, decimal);
	},
	/**
	 * 将毫秒数转成时段
	 */
	timespan : function(milliseconds) {
		var days = Math.floor(milliseconds / 1000 / 60 / 60 / 24);
		var hours = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000) / 1000 / 60 / 60);
		var minutes = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000) / 1000 / 60);
		var seconds = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000 - minutes * 60 * 1000) / 1000);
		var timespans = [];
		if (days > 0)
			timespans.push(days + "天");
		if (hours > 0)
			timespans.push(hours + "小时");
		if (minutes > 0)
			timespans.push(minutes + "分");
		if (seconds > 0)
			timespans.push(seconds + "秒");

		return timespans.join(':');
	},
	/**
	 * 显示提示窗口
	 * 
	 * @param type
	 *            类型：warn、success、error、question
	 * @param content
	 *            内容
	 * @param seconds
	 *            停留秒数
	 */
	tip : function(type, content, seconds) {
		tip = $.ligerDialog.tip({
			type : type,
			content : content
		});
		setTimeout(function() {
			tip.close();
		}, seconds * 1000);
	},
	form : {
		check : function(op) {
			if ($('#' + op.formName).length <= 0)
				return false;
			var v = $('#' + op.formName).validate(op.rules);
			var result = v.checkForm();
			v.showErrors();
			return result;
		},
		update : function(op) {
			$('#' + op.div).empty();
			$('#' + op.div).html(op.html);
			if (op.formName)
				this.check(op);
			if (op.onloaded) {
				op.onloaded();
			}
		},
		save : function(dialog, data, op) {
			var tip = undefined;
			var result = false;
			var type = typeof (data);
			if (type == 'string') {
				if (typeof data == 'string') {
					dialog.frame.document.write(data);
					dialog.frame.document.close()
				}
			} else if (data.code > 0) {
				tip = $.ligerDialog.tip({
					type : 'error',
					content : data.error
				});
			} else {
				tip = $.ligerDialog.tip({
					type : 'success',
					content : '数据保存成功!'
				});
				dialog.close();
				result = true;
			}
			if (tip)
				setTimeout(function() {
					tip.close();
				}, 3000);

			return result;
		},
		remove : function(data) {
			var tip = undefined;
			var result = false;
			if (data.code > 0) {
				tip = $.ligerDialog.tip({
					type : 'error',
					content : data.error
				});
			} else {
				tip = $.ligerDialog.tip({
					type : 'success',
					content : '数据删除成功!'
				});
				result = true;
			}
			if (tip)
				setTimeout(function() {
					tip.close();
				}, 3000);

			return result;
		}
	},
	checkData : function(data, error, success) {
		var tip = undefined;
		var result = false;
		if (data.code > 0 && error) {
			tip = $.ligerDialog.tip({
				type : 'error',
				content : data.error
			});
		} else {
			if (success)
				tip = $.ligerDialog.tip({
					type : 'success',
					content : success
				});
			result = true;
		}
		if (tip)
			setTimeout(function() {
				tip.close();
			}, 3000);

		return result;
	},
	showDialog : function(op) {
		// 添加html的Div
		var divId = common.guid();
		op.div = divId;
		var $divId = '#' + divId;
		var div = '<div id="' + divId + '"></div>';
		$('#dialogs').append($(div));
		// 更新div的内容
		// $($divId).html(o.html);
		op.target = $('#' + divId);

		var dialog = $.ligerDialog.open(op);
		this.form.update(op);

		return dialog;
	},
	setCookie : function(name, value) {
		document.cookie = name + "=" + escape(value);
	},
	// 读取cookie
	getCookie : function(name) {
		var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
		if (arr != null)
			return unescape(arr[2]);
		return null;

	},
	// 删除cookie
	delCookie : function(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = readCookie(name);
		if (cval != null)
			document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	}

};
/*******************************************************************************
 * 
 * 扩展方法
 * 
 ******************************************************************************/
Date.prototype.addDays = function(n) {
	// / <summary>
	// / 添加日期天数
	// / </summary>
	return new Date(this - 0 + n * 86400000);
}

Date.prototype.getOffDays = function(end) {
	// / <summary>
	// / 得到时间戳相减 得到以毫秒为单位的差
	// / </summary>

	var mm = Math.abs(end.getTime() - this.getTime());

	// 单位转换为天并返回

	return (mm / 3600000 / 24);

};

Date.prototype.getShortDate = function() {
	// / <summary>
	// / 获取日期
	// / </summary>
	var date = new Date(this.setSeconds(0));
	date = new Date(this.setMinutes(0));
	date = new Date(this.setHours(0));

	return date;
};

Date.prototype.toDateTimeString = function(formart) {
	// / <summary>
	// / 将日期转成指定格式的字符串
	// / 默认为 yyyy-MM-dd hh:mm:ss
	// / </summary>
	var yyyy = this.getFullYear();
	var MM = this.getMonth() + 1;
	MM = MM < 10 ? '0' + MM : MM;

	var dd = this.getDate();
	dd = dd < 10 ? '0' + dd : dd;

	var hh = this.getHours();
	hh = hh < 10 ? '0' + hh : hh;

	var mm = this.getMinutes();
	mm = mm < 10 ? '0' + mm : mm;

	var ss = this.getSeconds();
	ss = ss < 10 ? '0' + ss : ss;

	if (formart)
		return formart.replace("yyyy", yyyy).replace("MM", MM).replace("dd", dd).replace("hh", hh).replace("mm", mm).replace("ss", ss);
	else
		return yyyy + '-' + MM + '-' + dd + ' ' + hh + ':' + mm + ':' + ss
};

Date.prototype.toShortDateString = function(formart) {
	// / <summary>
	// / 将日期转成短日期格式的字符串
	// / 默认为 yyyy-MM-dd
	// / </summary>
	var yyyy = this.getFullYear();
	var MM = this.getMonth() + 1;
	MM = MM < 10 ? '0' + MM : MM;

	var dd = this.getDate();
	dd = dd < 10 ? '0' + dd : dd;

	if (formart)
		return formart.replace("yyyy", yyyy).replace("MM", MM).replace("dd", dd);
	else
		return yyyy + '-' + MM + '-' + dd;
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(m, i) {
		return args[i];
	});
}

String.prototype.toDate = function() {
	return new Date(Date.parse(this.replace(/-/g, "/")));
}

var BASE64 = {
	/**
	 * 此变量为编码的key，每个字符的下标相对应于它所代表的编码。
	 */
	enKey : 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
	/**
	 * 此变量为解码的key，是一个数组，BASE64的字符的ASCII值做下标，所对应的就是该字符所代表的编码值。
	 */
	deKey : new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1,
			2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,
			32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1),
	/**
	 * 编码
	 */
	encode : function(src) {
		// 用一个数组来存放编码后的字符，效率比用字符串相加高很多。
		var str = new Array();
		var ch1, ch2, ch3;
		var pos = 0;
		// 每三个字符进行编码。
		while (pos + 3 <= src.length) {
			ch1 = src.charCodeAt(pos++);
			ch2 = src.charCodeAt(pos++);
			ch3 = src.charCodeAt(pos++);
			str.push(this.enKey.charAt(ch1 >> 2), this.enKey.charAt(((ch1 << 4) + (ch2 >> 4)) & 0x3f));
			str.push(this.enKey.charAt(((ch2 << 2) + (ch3 >> 6)) & 0x3f), this.enKey.charAt(ch3 & 0x3f));
		}
		// 给剩下的字符进行编码。
		if (pos < src.length) {
			ch1 = src.charCodeAt(pos++);
			str.push(this.enKey.charAt(ch1 >> 2));
			if (pos < src.length) {
				ch2 = src.charCodeAt(pos);
				str.push(this.enKey.charAt(((ch1 << 4) + (ch2 >> 4)) & 0x3f));
				str.push(this.enKey.charAt(ch2 << 2 & 0x3f), '=');
			} else {
				str.push(this.enKey.charAt(ch1 << 4 & 0x3f), '==');
			}
		}
		// 组合各编码后的字符，连成一个字符串。
		return str.join('');
	},
	/**
	 * 解码。
	 */
	decode : function(src) {
		// 用一个数组来存放解码后的字符。
		var str = new Array();
		var ch1, ch2, ch3, ch4;
		var pos = 0;
		// 过滤非法字符，并去掉'='。
		src = src.replace(/[^A-Za-z0-9\+\/]/g, '');
		// decode the source string in partition of per four characters.
		while (pos + 4 <= src.length) {
			ch1 = this.deKey[src.charCodeAt(pos++)];
			ch2 = this.deKey[src.charCodeAt(pos++)];
			ch3 = this.deKey[src.charCodeAt(pos++)];
			ch4 = this.deKey[src.charCodeAt(pos++)];
			str.push(String.fromCharCode((ch1 << 2 & 0xff) + (ch2 >> 4), (ch2 << 4 & 0xff) + (ch3 >> 2), (ch3 << 6 & 0xff) + ch4));
		}
		// 给剩下的字符进行解码。
		if (pos + 1 < src.length) {
			ch1 = this.deKey[src.charCodeAt(pos++)];
			ch2 = this.deKey[src.charCodeAt(pos++)];
			if (pos < src.length) {
				ch3 = this.deKey[src.charCodeAt(pos)];
				str.push(String.fromCharCode((ch1 << 2 & 0xff) + (ch2 >> 4), (ch2 << 4 & 0xff) + (ch3 >> 2)));
			} else {
				str.push(String.fromCharCode((ch1 << 2 & 0xff) + (ch2 >> 4)));
			}
		}
		// 组合各解码后的字符，连成一个字符串。
		return str.join('');
	}
};
common.converter = {
	PI : 3.14159265358979324,
	x_pi : 3.14159265358979324 * 3000.0 / 180.0,
	delta : function(lat, lng) {
		// Krasovsky 1940
		//
		// a = 6378245.0, 1/f = 298.3
		// b = a * (1 - f)
		// ee = (a^2 - b^2) / a^2;
		var a = 6378245.0;
		var ee = 0.00669342162296594323;
		var dLat = this.transformLat(lng - 105.0, lat - 35.0);
		var dLng = this.transformLng(lng - 105.0, lat - 35.0);
		var radLat = lat / 180.0 * this.PI;
		var magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		var sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * this.PI);
		dLng = (dLng * 180.0) / (a / sqrtMagic * Math.cos(radLat) * this.PI);
		return {
			'lat' : dLat,
			'lng' : dLng
		};
	},
	// WGS-84 to GCJ-02
	gcj_encrypt : function(wgsLat, wgsLng) {
		if (this.outOfChina(wgsLat, wgsLng))
			return {
				'lat' : wgsLat,
				'lng' : wgsLng
			};

		var d = this.delta(wgsLat, wgsLng);
		return {
			'lat' : wgsLat + d.lat,
			'lng' : wgsLng + d.lng
		};
	},
	// GCJ-02 to WGS-84
	gcj_decrypt : function(gcjLat, gcjLng) {
		if (this.outOfChina(gcjLat, gcjLng))
			return {
				'lat' : gcjLat,
				'lng' : gcjLng
			};

		var d = this.delta(gcjLat, gcjLng);
		return {
			'lat' : gcjLat - d.lat,
			'lng' : gcjLng - d.lng
		};
	},
	// GCJ-02 to WGS-84 exactly
	gcj_decrypt_exact : function(gcjLat, gcjLng) {
		var initDelta = 0.01;
		var threshold = 0.000000001;
		var dLat = initDelta, dLng = initDelta;
		var mLat = gcjLat - dLat, mLng = gcjLng - dLng;
		var pLat = gcjLat + dLat, pLng = gcjLng + dLng;
		var wgsLat, wgsLng, i = 0;
		while (1) {
			wgsLat = (mLat + pLat) / 2;
			wgsLng = (mLng + pLng) / 2;
			var tmp = this.gcj_encrypt(wgsLat, wgsLng)
			dLat = tmp.lat - gcjLat;
			dLng = tmp.lng - gcjLng;
			if ((Math.abs(dLat) < threshold) && (Math.abs(dLng) < threshold))
				break;

			if (dLat > 0)
				pLat = wgsLat;
			else
				mLat = wgsLat;
			if (dLng > 0)
				pLng = wgsLng;
			else
				mLng = wgsLng;

			if (++i > 10000)
				break;
		}
		// console.log(i);
		return {
			'lat' : wgsLat,
			'lng' : wgsLng
		};
	},
	// GCJ-02 to BD-09
	bd_encrypt : function(gcjLat, gcjLng) {
		var x = gcjLng, y = gcjLat;
		var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * this.x_pi);
		var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * this.x_pi);
		bdLng = z * Math.cos(theta) + 0.0065;
		bdLat = z * Math.sin(theta) + 0.006;
		return {
			'lat' : bdLat,
			'lng' : bdLng
		};
	},
	/**
	 * gps坐标转百度坐标
	 */
	wgs84_gcj02_db09 : function(wgsLat, wgsLng) {
		var p = this.gcj_encrypt(wgsLat, wgsLng);
		p = this.bd_encrypt(p.lat, p.lng);
		return p;
	},
	// BD-09 to GCJ-02
	bd_decrypt : function(bdLat, bdLng) {
		var x = bdLng - 0.0065, y = bdLat - 0.006;
		var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * this.x_pi);
		var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * this.x_pi);
		var gcjLng = z * Math.cos(theta);
		var gcjLat = z * Math.sin(theta);
		return {
			'lat' : gcjLat,
			'lng' : gcjLng
		};
	},
	/**
	 * 百度坐标转gps坐标
	 */
	bd09_gcj02_wgs84 : function(dbLat, dbLng) {
		var p = this.bd_decrypt(dbLat, dbLng);
		p = this.gcj_decrypt_exact(p.lat, p.lng);
		return p;
	},
	distance : function(latA, logA, latB, logB) {
		var earthR = 6371000;
		var x = Math.cos(latA * Math.PI / 180) * Math.cos(latB * Math.PI / 180) * Math.cos((logA - logB) * Math.PI / 180);
		var y = Math.sin(latA * Math.PI / 180) * Math.sin(latB * Math.PI / 180);
		var s = x + y;
		if (s > 1)
			s = 1;
		if (s < -1)
			s = -1;
		var alpha = Math.acos(s);
		var distance = alpha * earthR;
		return distance;
	},
	outOfChina : function(lat, lng) {
		if (lng < 72.004 || lng > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	},
	transformLat : function(x, y) {
		var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * this.PI) + 40.0 * Math.sin(y / 3.0 * this.PI)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * this.PI) + 320 * Math.sin(y * this.PI / 30.0)) * 2.0 / 3.0;
		return ret;
	},
	transformLng : function(x, y) {
		var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * this.PI) + 40.0 * Math.sin(x / 3.0 * this.PI)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * this.PI) + 300.0 * Math.sin(x / 30.0 * this.PI)) * 2.0 / 3.0;
		return ret;
	}
};
$(function() {
	$("#btnLogout").click(function() {
		$.ligerDialog.confirm("真地要退出系统吗？", function(type) {
			if (type === false)
				return;
			$('#LogoutForm').submit();
		});
	});
	$('#btnMyinfo').click(function() {
		var url = $('#rootPath').val() + '/myinfo.form';
		var op = {
			url : url,
			width : 500,
			height : 380,
			isHidden : false,
			title : '个人信息',
			onLoaded : function() {
				common.myinfoDialog.frame.window.validate();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.validate() == false) {
						return;
					}

					var form = $('form', dialog.frame.document);
					var formData = form.serialize();
					$.post(url, formData, function(data) {
						common.form.save(dialog, data, op);
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ],
		};
		common.myinfoDialog = $.ligerDialog.open(op);
	});
	$('#btnMykey').click(function() {
		var url = $('#rootPath').val() + '/mykey.form';
		var op = {
			url : url,
			width : 480,
			height : 230,
			isHidden : false,
			title : '密码设置',
			onLoaded : function() {
				common.mykeyDialog.frame.window.validate();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.validate() == false) {
						return;
					}

					var form = $('form', dialog.frame.document);
					var formData = form.serialize();
					$.post(url, formData, function(data) {
						common.form.save(dialog, data, op);
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		common.mykeyDialog = $.ligerDialog.open(op);
	});
})