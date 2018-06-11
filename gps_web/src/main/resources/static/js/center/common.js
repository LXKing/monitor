window.common = {
    debug: function (obj) {
        obj.toString();
    },
    guid: function () {
        var guid = "";
        for (var i = 1; i <= 32; i++) {
            var n = Math.floor(Math.random() * 16.0).toString(16);
            guid += n;
            if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
                guid += "-";
        }
        return guid;
    },
    bitsToNumber: function (bits) {
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
    numberToBits: function (number) {

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
    round: function (number, decimal) {
        return Math.round(number * Math.pow(10, decimal)) / Math.pow(10, decimal);
    },
    /**
     * 将毫秒数转成时段
     */
    timespan: function (milliseconds) {
        var days = Math.floor(milliseconds / 1000 / 60 / 60 / 24);
        var hours = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000) / 1000 / 60 / 60);
        var minutes = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000) / 1000 / 60);
        var seconds = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000 - minutes * 60 * 1000) / 1000);
        var timespans = [];
        if (days > 0)
            timespans.push(days + "");
        if (hours > 0)
            timespans.push(hours + "");
        if (minutes > 0)
            timespans.push(minutes + "");
        if (seconds > 0)
            timespans.push(seconds + "");

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
    tip: function (type, content, seconds) {
        alert("此区域无车辆");
        /*tip = $.ligerDialog.tip({
            type: type,
            content: content
        });*/
        /*    setTimeout(function () {
                tip.close();
            }, seconds * 1000);*/
    },
    form: {
        check: function (op) {
            if ($('#' + op.formName).length <= 0)
                return false;
            var v = $('#' + op.formName).validate(op.rules);
            var result = v.checkForm();
            v.showErrors();
            return result;
        },
        update: function (op) {
            $('#' + op.div).empty();
            $('#' + op.div).html(op.html);
            if (op.formName)
                this.check(op);
            if (op.onloaded) {
                op.onloaded();
            }
        },
        save: function (dialog, data, op) {
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
                    type: 'error',
                    content: data.error
                });
            } else {
                tip = $.ligerDialog.tip({
                    type: 'success',
                    content: '数据保存成功!'
                });
                dialog.close();
                result = true;
            }
            if (tip)
                setTimeout(function () {
                    tip.close();
                }, 3000);

            return result;
        },
        remove: function (data) {
            var tip = undefined;
            var result = false;
            if (data.code > 0) {
                tip = $.ligerDialog.tip({
                    type: 'error',
                    content: data.error
                });
            } else {
                tip = $.ligerDialog.tip({
                    type: 'success',
                    content: '数据删除成功!'
                });
                result = true;
            }
            if (tip)
                setTimeout(function () {
                    tip.close();
                }, 3000);

            return result;
        }
    },
    checkData: function (data, error, success) {
        var tip = undefined;
        var result = false;
        if (data.code > 0 && error) {
            tip = $.ligerDialog.tip({
                type: 'error',
                content: data.error
            });
        } else {
            if (success)
                tip = $.ligerDialog.tip({
                    type: 'success',
                    content: success
                });
            result = true;
        }
        if (tip)
            setTimeout(function () {
                tip.close();
            }, 3000);

        return result;
    },
    showDialog: function (op) {
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
    setCookie: function (name, value) {
        document.cookie = name + "=" + escape(value);
    },
    // 读取cookie
    getCookie: function (name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null)
            return unescape(arr[2]);
        return null;

    },
    // 删除cookie
    delCookie: function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = readCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }

};