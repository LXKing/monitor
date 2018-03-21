/**
 * 数据日志
 */
window.datalog = {
    dialogDataLog: null,
    start: null,
    end: null,
    gridDataLog: null,
    gridDataLogParams: {
        number: null,
        start: new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm:ss'),
        end: new Date().addDays(1).toDateTimeString('yyyy-MM-dd hh:mm:ss')
    },
    init: function () {
        var divSelectSubParm = $('#dialogDataLog');
        if (divSelectSubParm.length <= 0) {
            var html = [];
            html.push('<div id="dialogDataLog" style="display: none;">');
            html.push('<div class="mon-toolbar metro-gray">');
            html.push('<div class="title mon-3x"><div class="icon i-16-list"></div><div><b>选择时间段</b></div></div>');
            html
                .push('<div class="prompt editor-field"><input id="txtDataLogStartDate" type="text" /></div><div class="prompt editor-field"><input id="txtDataLogEndDate" type="text" /></div>');
            html.push('<div id="btnDataLogQuery" class="mon-button"><div class="icon i-16-search"></div><span>查询</span></div>');
            html.push('<div class="prompt"><input id="btnLinkDataLog" type="radio" name="datalog"/><label for="btnLinkDataLog">连接日志</label></div>')
            html.push('<div class="prompt"><input id="btnBreakDataLog" type="radio" name="datalog"/><label for="btnBreakDataLog">断开日志</label></div>')
            html.push('</div>');
            html.push('<div id="gridDataLog"></div>');
            html.push('</div>');
            $(document.body).append($(html.join('')));

            datalog.start = $('#txtDataLogStartDate').ligerDateEditor({
                showTime: true,
                format: 'yyyy-MM-dd hh:mm',
                label: '开始时间',
                labelWidth: 60,
                labelAlign: 'left',
                initValue: new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm'),
                onChangeDate: function (value) {
                    var time = (value + ':00').toDate().toDateTimeString('yyyy-MM-dd hh:mm:ss');
                    datalog.gridDataLogParams.start = time;
                }
            });
            datalog.end = $("#txtDataLogEndDate").ligerDateEditor({
                showTime: true,
                format: 'yyyy-MM-dd hh:mm',
                label: '结束时间',
                labelWidth: 60,
                labelAlign: 'left',
                initValue: new Date().addDays(1).toDateTimeString('yyyy-MM-dd hh:mm'),
                onChangeDate: function (value) {
                    var time = (value + ':00').toDate().toDateTimeString('yyyy-MM-dd hh:mm:ss');
                    datalog.gridDataLogParams.end = time;
                }
            });
            $('#btnDataLogQuery').click(function () {
                if (datalog.gridDataLog) {
                    datalog.gridDataLog.reload();
                }
            });
            $("#btnLinkDataLog").click(function () {
                $.post('../common/device/log/enable', {
                    number: datalog.gridDataLogParams.number,
                    enable: true
                }, function (r) {
                    var tip = null;
                    if (r.code === 0)
                        tip = $.ligerDialog.tip({
                            title: '连接数据日志成功',
                            content: '已开启设备日志记录！'
                        });
                    else
                        tip = $.ligerDialog.tip({
                            title: '连接数据日志失败',
                            content: r.error
                        });
                    setTimeout(function () {
                        tip.close();
                    }, 3000);
                });
            });
            $("#btnBreakDataLog").click(function () {
                $.post('../common/device/log/enable', {
                    number: datalog.gridDataLogParams.number,
                    enable: false
                }, function (r) {
                    var tip = null;
                    if (r.code === 0)
                        tip = $.ligerDialog.tip({
                            title: '断开数据日志成功',
                            content: '已断开设备日志记录！'
                        });
                    else
                        tip = $.ligerDialog.tip({
                            title: '断开数据日志失败',
                            content: r.error
                        });
                    setTimeout(function () {
                        tip.close();
                    }, 3000);
                });
            });
        }
    },
    show: function (number) {
        $.post('../common/device/status', {
            number: number
        }, function (device) {
            if (device.debugging === true)
                $("#btnLinkDataLog").attr("checked", "checked");
            else
                $('#btnBreakDataLog').attr("checked", "checked");

        });
        datalog.gridDataLogParams.number = number;
        datalog.dialogDataLog = $.ligerDialog.open({
            target: $('#dialogDataLog'),
            title: '数据日志',
            width: 850,
            height: 550
        });
        if (!datalog.gridDataLog) {
            datalog.gridDataLog = $("#gridDataLog").ligerGrid({
                columns: [{
                    display: '时间',
                    name: 'time',
                    width: 130,
                    frozen: true
                }, {
                    display: '原始数据',
                    align: 'left',
                    name: 'raw',
                    width: 800,
                    render: function (row) {
                        var list = [];
                        var raw = BASE64.decode(row.raw);

                        for (var x = 0; x < raw.length; x++) {
                            var hex = raw.charCodeAt(x).toString(16);
                            hex = hex.length == 1 ? '0' + hex : hex;
                            list.push(hex.toUpperCase());
                        }

                        return list.join("");
                    }
                }],
                pageSize: 30,
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                root: 'rows',
                record: 'total',
                url: '../common/device/log/load',
                parms: datalog.gridDataLogParams,
                height: 450
            });
        }
    }
};
$(function () {
    datalog.init();
});
