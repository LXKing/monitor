var Ren_currentOnlineOfflineCount = function (url) {

    // 基于准备好的dom，初始化echarts实例
    // var myChart = echarts.init(document.getElementById('chart'));

    // 指定图表的配置项和数据
    // option = {
    //     legend: {},
    //     tooltip: {},
    //     dataset: {
    //         source: [
    //             ['product', '2015', '2016', '2017'],
    //             ['Matcha Latte', 43.3, 85.8, 93.7],
    //             ['Milk Tea', 83.1, 73.4, 55.1],
    //             ['Cheese Cocoa', 86.4, 65.2, 82.5],
    //             ['Walnut Brownie', 72.4, 53.9, 39.1]
    //         ]
    //     },
    //     xAxis: {type: 'category'},
    //     yAxis: {},
    //     // Declare several bar series, each will be mapped
    //     // to a column of dataset.source by default.
    //     series: [
    //         {type: 'bar'},
    //         {type: 'bar'},
    //         {type: 'bar'}
    //     ]
    // };

    // 使用刚指定的配置项和数据显示图表。
    // myChart.setOption(option);


    document.getElementById("shitBar").innerHTML = shitbar;
    var table = layui.table;
    console.log(url);


    var form = layui.form;
    form.render();


    var checkShitHistoryOnlineOffline = [];

    // url = filter == null ? url : url + filter;
    // console.log(url)
    // 第一个实例
    table.render({
        id: 'idTest',
        method: "POST",
        elem: '#theTable',
        url: url,
        page: true,
        where: {
            motorcade: '',

        },
        cols: [
            [

                //表头

                {field: 'motorcade', title: '车队', sort: true},

                {field: 'offline', title: '下线数量', sort: true},
                {field: 'offlineRate', title: '下线率', sort: true},
                {field: 'online', title: '上线数量', sort: true},
                {field: 'onlineRate', title: '上线率', sort: true},

                {field: 'total', title: '车辆总数', sort: true},

                {fixed: 'right', align: 'center', toolbar: '#moreBar'}

            ]
        ],
        skin: "nob",
        size: "sm",
        request: {
            pageName: 'pageIndex' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        },
        response: {
            countName: 'total' //数据总数的字段名称，默认：count
            , dataName: 'rows' //数据列表的字段名称，默认：data
        }
        ,
        done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度


            // 基于准备好的dom，初始化echarts实例
           echarts.dispose(document.getElementById('chart'));
            var myChart = echarts.init(document.getElementById('chart'));


            console.log(res.rows);
            var source = [];
            var p = ['product', '上线数量', '下线数量'];
            source[0] = p;
            for (let it of res.rows) {
                let arr = [];
                arr.push(it.motorcade);
                arr.push(it.online);
                arr.push(it.offline);
                source[source.length] = arr;
            }
            console.log(source)
            // 指定图表的配置项和数据
            let option = {
                legend: {},
                tooltip: {},
                dataset: {
                    source: source
                },
                xAxis: {type: 'category'},
                yAxis: {min:'dataMin',   minInterval: 1},
                // Declare several bar series, each will be mapped
                // to a column of dataset.source by default.
                series: [
                    {type: 'bar'},
                    {type: 'bar'},

                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //得到当前页码
            console.log(curr);

            //得到数据总量
            console.log(count);
        }
    });

    // 监听表格复选框选择
    table.on('checkbox(table)', function (obj) {

        obj.checked ? checkShitHistoryOnlineOffline.push(obj) : removeWithoutCopy(checkShitHistoryOnlineOffline, obj.data.id);
        var checkStatus = table.checkStatus('idTest')
            , data = checkStatus.data;
        console.log(data);

        // console.log(checkShitRenderVehicle);

        function removeWithoutCopy(arr, id) {
            for (var i = arr.length - 1; i >= 0; i--) {
                if (arr[i].data.id === id) {
                    arr.splice(i, 1);
                }
            }
            return arr;
        }

        // console.log(obj);
        // console.log(obj.checked); //当前是否选中状态
        // console.log(obj.data); //选中行的相关数据
        // console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
    });


    // 表格内按鈕
    table.on('tool(table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'more') {

            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="detail" lay-filter="detail"></table>',
                offset: 'lt',
                btn: ['导出', '取消'] //只是为了演示
                , yes: function () {

                    var params = {
                        motorcade: data.motorcade,
                        motorcadeId: data.motorcadeId,

                        type: "excel"

                    };
                    jQuery.ajax({
                        url: '/statistics/currentOnlineOfflineDetailExport',
                        type: "POST",
                        data: params,
                        success: function (response, status, request) {
                            var url='/statistics/currentOnlineOfflineDetailExport';
                            var disp = request.getResponseHeader('Content-Disposition');
                            if (disp && disp.search('attachment') != -1) {  //判断是否为文件
                                var form = jQuery('<form method="POST" action="' + url + '">');
                                jQuery.each(params, function (k, v) {
                                    form.append(jQuery('<input type="hidden" name="' + k +
                                        '" value="' + v + '">'));
                                });
                                jQuery('body').append(form);
                                form.submit(); //自动提交
                            }
                        }
                    });
                }
                , btn2: function () {
                    layer.close(jjj);
                },

                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#detail'
                        , url: '/statistics/currentOnlineOfflineDetail', //数据接口
                        page: true, // 开启分页
                        method: "POST",
                        where: {
                            motorcade: data.motorcade,
                            motorcadeId: data.motorcadeId,

                        }
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm",
                        cols: [[ // 表头
                            {field: 'deviceNumber', title: '设备号'}
                            , {field: 'end', title: '结束时间'}
                            , {field: 'motorcade', title: '车队', sort: true}
                            , {field: 'start', title: '开始时间', sort: true}
                            , {field: 'online', title: '在线情况', sort: true}
                            , {field: 'plateNumber', title: '车牌号', sort: true}


                        ]],
                        request: {
                            pageName: 'pageIndex' //页码的参数名称，默认：page
                            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        },
                        response: {
                            countName: 'total' //数据总数的字段名称，默认：count
                            , dataName: 'rows' //数据列表的字段名称，默认：data
                        }
                    });

                    // table.on('checkbox(vehicle)', function (obj) {
                    //
                    //     var checkStatus = table.checkStatus('vehicle');
                    //     fff = checkStatus.data;
                    //     // console.log(mmm)
                    //
                    //     // console.log(obj.data); //选中行的相关数据
                    //
                    //
                    // });
                }
            });
        }
    });
    //监听工具条
    var $ = layui.$, active = {
        reload: function () {
            var tableReload = $('#tableReload');
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    motorcade: tableReload.val(),

                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        console.log(type);
        console.log(active);
    });


};
