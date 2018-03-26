/**
 * 车辆年检到期
 */
window.vehicleAnnualSurveyExpired = {
    query: function (days) {
        $.post('../overview/vehicleAnnualSurveyExpired', {
            days: days
        }, function (list) {
            $('#gridVehicleExpired').ligerGrid({
                columns: [{
                    display: '车牌号',
                    name: 'plateNumber',
                    align: 'left',
                    width: 150
                }, {
                    display: '设备号',
                    name: 'deviceNumber',
                    align: 'left',
                    width: 100
                }, {
                    display: '电话号码',
                    name: 'phoneNumber',
                    align: 'left',
                    width: 100
                }, {
                    display: '车队',
                    name: 'motorcade',
                    align: 'left',
                    width: 150
                }, {
                    display: '安装日期',
                    name: 'installDate',
                    type: 'date',
                    width: 100
                }, {
                    display: '保养日期',
                    name: 'nextMaintainDate',
                    type: 'date',
                    width: 100
                }, {
                    display: '备注',
                    name: 'remark',
                    align: 'left',
                    width: 100
                }],
                data: {
                    rows: list
                },
                root: 'rows',
                width: '100%',
                height: '100%'
            });
        });
    }
}
