/**
 * 图片
 */
window.multimedia = {
    deviceNumber: null,
    plateNumber: null,
    gridMultimedia: null,
    gridMultimediaParms: {
        deviceNumber: null,
        start: null,
        end: null
    },
    map: null,
    onMapLoaded: function (map) {
        multimedia.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            multimedia.map.convertor(r.lng, r.lat, function (center) {
                multimedia.map.setCenter(center);
                multimedia.map.setZoom(r.zoom);
            });
        });
        multimedia.infoWindow = webMap.createInfoWindow({
            map: map,
            data: undefined,
            width: 260,
            allowQueryAddress: true,
            makeTitle: function () {
                if (!this.data)
                    return '';

                var html = [];
                html.push('<div style="margin:0px;padding:0px">');
                // 第一行
                html.push('<div style="height:18px;margin:2px;">');

                html.push('<b>');
                html.push(this.data.na);
                html.push(this.data.sp > 0 ? '[行驶]' : '[静止]');
                html.push('</b>');

                html.push('</div>');

                return html.join('');
            },
            makeContent: function () {
                var alarm = gpsDataParser.parseAlarm(this.data);
                var html = [];
                html.push('<hr/><div style="margin:0px;padding:0px">');
                // 第一行
                html.push('<div>');
                html.push('<img src="../deviceData/multimedia/get?id=' + this.data.id + '" width="320px" height="240px">');
                html.push("</div>");

                html.push('<div style="margin:2px;">');
                html.push('<b>位置:</b>');
                html.push(this.data.addr);
                html.push('</div>');

                return html.join("");

            }
        });
    },
    showMultimedia: function (row) {
        row.marker = multimedia.vehicle.marker;
        multimedia.map.convertor(row.lng, row.lat, function (point) {
            multimedia.map.queryAddress(point.lng, point.lat, function (address) {
                row.olng = point.lng;
                row.olat = point.lat;
                row.addr = address;
                row.na = multimedia.plateNumber;
                var marker = multimedia.marker;
                if (!marker) {
                    marker = multimedia.marker = webMap.createMarker({
                        map: multimedia.map,
                        data: row,
                        allowRotate: multimedia.vehicle.rotate === 1,
                        allowShowLabel: false,
                        infoWindow: multimedia.infoWindow
                    });
                    marker.openInfoWindow();
                } else {
                    marker.data = row;
                    marker.refresh();
                }

                multimedia.map.panTo(row.olng, row.olat);
            });
        });
    },
    query: function () {
        if (!multimedia.gridMultimedia) {
            multimedia.gridMultimedia = $("#gridMultimedia").ligerGrid({
                columns: [{
                    display: '时间',
                    name: 'gt',
                    align: 'left',
                    width: 120
                }, {
                    display: '媒体id',
                    name: 'mediaId',
                    align: 'left',
                    width: 50
                }, {
                    display: '媒体类型',
                    name: 'mediaType',
                    align: 'left',
                    width: 50
                }, {
                    display: '格式类型',
                    name: 'formatType',
                    align: 'left',
                    width: 50
                }, {
                    display: '事件类型',
                    name: 'eventType',
                    width: 80
                }, {
                    display: '通道',
                    name: 'channelId',
                    width: 50
                }, {
                    display: '速度(km/h)',
                    name: 'sp',
                    width: 60,
                    render: function (row) {
                        if (row.sp === 0)
                            return '0';
                        return common.round(row.sp, 1) + '';
                    }
                }, {
                    display: '方向',
                    name: 'd',
                    width: 40,
                    render: function (row) {
                        return gpsDataParser.parseDirection(row);
                    }
                }, {
                    display: '状态',
                    name: 's',
                    width: 150,
                    render: function (row) {
                        return gpsDataParser.parseStatus(row);
                    }
                }, {
                    display: '报警',
                    name: 'a',
                    width: 80,
                    render: function (row) {
                        return gpsDataParser.parseAlarm(row);
                    }
                }, {
                    display: '操作',
                    isAllowHide: false,
                    width: 100,
                    render: function (row) {
                        var html = [];
                        if (row.formatType == 'JPEG' || row.formatType == 'TIF')
                            html.push('<a href="#" onclick="multimedia.showPhoto(\'' + row.id + '\')">全图</a>');
                        return html.join('');
                    }
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../deviceData/multimedia/query',
                width: '100%',
                height: '100%',
                pageSize: 30,
                rownumbers: true,
                parms: multimedia.gridMultimediaParms,
                onSelectRow: function (row) {
                    multimedia.showMultimedia(row);
                }
            });
        } else {
            multimedia.gridMultimedia.changePage('first');
            multimedia.gridMultimedia.reload();
        }
    },
    showPhoto: function (id) {
        document.getElementById("fullPhoto").src = '../deviceData/multimedia/get?id=' + id;
        multimedia.dialogShowPhoto = $.ligerDialog.open({
            title: '全图',
            width: document.body.clientWidth,
            height: document.body.clientHeight,
            target: $("#dialogShowPhoto")
        });
    },
    layout: function () {
        var height = $(document).height();
        var centerHeight = height * 0.7;
        var bottomHeight = height - centerHeight;
        $('#divCenter').height(centerHeight);
        $('#divBottom').height(bottomHeight);
    }
}
$(function () {
    multimedia.layout();
    multimedia.vehicle = parent.deviceData.vehicle;
    multimedia.deviceNumber = parent.deviceData.deviceNumber;
    multimedia.plateNumber = parent.deviceData.plateNumber;

    webMap.events.onMapLoadCompleted['multimediaMap'] = multimedia.onMapLoaded;
    webMap.createMap("multimediaMap");

    multimedia.gridMultimediaParms.deviceNumber = multimedia.deviceNumber;
    var endDate = new Date().addDays(1);
    ;
    multimedia.gridMultimediaParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
    var startDate = endDate.addDays(-1);
    multimedia.gridMultimediaParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

    multimedia.txtStartDate = $('#txtStartDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        label: '开始时间',
        labelWidth: 65,
        initValue: startDate.toDateTimeString("yyyy-MM-dd")
    });

    multimedia.txtEndDate = $('#txtEndDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        label: '结束时间',
        labelWidth: 65,
        initValue: endDate.toDateTimeString("yyyy-MM-dd")
    });

    multimedia.query();

    $('#btnQuery').click(function () {
        var start = multimedia.txtStartDate.getValue();
        var end = multimedia.txtEndDate.getValue();
        if (start == null || end == null) {
            $.ligerDialog.error('请选择时间段！');
            return;
        }
        multimedia.gridMultimediaParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
        multimedia.gridMultimediaParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
        multimedia.query();
    });
});