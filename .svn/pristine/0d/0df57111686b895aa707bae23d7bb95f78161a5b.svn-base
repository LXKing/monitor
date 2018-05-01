var renderDriver = function (url) {

    var mmp = `
     <form class="layui-form layui-form-pane" action="" style="color: black">
	<div class="layui-form-item layui-inline pane" style="display: none">
		<label class="layui-form-label"></label>
		<div class="layui-input-block">
			<input type="text" id="id" name="id" class="layui-input"/>
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">姓名</label>
		<div class="layui-input-block">
			<input type="text" name="name" id="name" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">电话</label>
		<div class="layui-input-block">
			<input type="text" name="phone" id="phone" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item pane">
		<label class="layui-form-label">性别</label>
		<div class="layui-input-block">
			<input type="radio" name="sex" value="男" title="男" checked>
			<input type="radio" name="sex" value="女" title="女">
		</div>
	</div>
	<div class="layui-form-item layui-form-text pane">
		<label class="layui-form-label">备注</label>
		<div class="layui-input-block">
			<textarea name="remark" id="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">证件类型</label>
		<div class="layui-input-block">
			<select name="idType" id="idType">
			</select>
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">证件号</label>
		<div class="layui-input-block">
			<input type="text" name="idNumber" id="idNumber" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">驾驶证号</label>
		<div class="layui-input-block">
			<input type="text" name="drivingLicenseNumber" id="drivingLicenseNumber" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">驾驶证有效日期</label>
		<div class="layui-input-block">
			<input type="text" name="drivingLicenseExpiryDate" id="drivingLicenseExpiryDate" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item pane">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
    
    `;


    function getYourDICK(type, ele) {
        var shit;

        jQuery.ajax({
            url: "/dictionary/list", type: "POST", async: false, data: {kind: type, grid: false},
            success: function (res) {
                shit = res;
                jQuery.each(res, function (index, data) {
                    var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                    // console.log(ele)
                    // console.log(Option)
                    // console.log(ele.append);
                    ele.append(Option);
                });
                // console.log(res);
            }
        });
        return shit;
    }


    document.getElementById("shitBar").innerHTML = shitbar;
    var table = layui.table;
    console.log(url);


    var checkShitRenderDriver = [];

    // url = filter == null ? url : url + filter;
    // console.log(url)
    // 第一个实例
    table.render({
        id: 'idTest',
        elem: '#theTable',
        url: url,
        page: true,
        where: {filter: ''},
        cols: [
            [
                {type: 'checkbox'},
                //表头

                {field: 'name', title: '姓名', sort: true},
                {field: 'sex', title: '性别', sort: true},
                {field: 'phone', title: '电话', sort: true},
                {field: 'drivingLicenseNumber', title: '驾驶证号', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {fixed: 'right', align: 'center', minWidth: 300, toolbar: '#renderDriverBar'}
            ]
        ],
        skin: "nob",
        size: "sm"
    });

    // 监听表格复选框选择
    table.on('checkbox(table)', function (obj) {

        obj.checked ? checkShitRenderDriver.push(obj) : removeWithoutCopy(checkShitRenderDriver, obj.data.id);
        var checkStatus = table.checkStatus('idTest')
            , data = checkStatus.data;
        console.log(data)

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
        if (obj.event === 'setCar') {
            var theVehicles = [];
            var fff;
            // var DeviceFree = getDeviceFree();
            // var did = jQuery("#deviceId");
            // var mmp = this;
            // console.log(DeviceFree);
            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="vehicle" lay-filter="vehicle"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);
                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theVehicles.push(fff[i].id);
                    }
                    console.log(theVehicles);
                    // return;
                    jQuery.ajax({
                        url: "/driver/addVehicles", type: "POST", async: false, traditional: true,
                        data: {
                            driverId: data.id,
                            vehicles: theVehicles
                        },
                        success: function (res) {
                            console.log(res);
                            // jQuery.each(res, function (index, data) {
                            //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //     // console.log(ele)
                            //     // console.log(Option)
                            //     // console.log(ele.append);
                            //     ele.append(Option);
                            // });
                            // console.log(res);
                            layer.close(jjj);
                        }
                    });
                }
                , btn2: function () {
                    layer.close(jjj);
                },
                btn3: function () {
                    var eee = layer.open({
                        type: 1 //Page层类型
                        , content: '<table id="vehicleShow" lay-filter="vehicleShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                            // // return;
                            // jQuery.ajax({
                            //     url: "/vehicle/owners",   async: false, traditional: true,
                            //     data: {
                            //         vehicleId: data.id
                            //     },
                            //     success: function (res) {
                            //         console.log(res);
                            //         // jQuery.each(res, function (index, data) {
                            //         //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //         //     // console.log(ele)
                            //         //     // console.log(Option)
                            //         //     // console.log(ele.append);
                            //         //     ele.append(Option);
                            //         // });
                            //         // console.log(res);
                            //         layer.close(ccc);
                            //     }
                            // });
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            //第一个实例
                            table.render({
                                elem: '#vehicleShow'
                                , url: 'driver/vehicles', //数据接口
                                page: true, // 开启分页
                                where: {driverId: data.id}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {field: 'annualSurveyDate', title: '年检日期'}

                                    , {field: 'deviceNumber', title: '设备号'}
                                    , {field: 'installDate', title: '安装日期'}
                                    , {field: 'motorcade', title: '车队', sort: true}
                                    , {field: 'phoneNumber', title: '电话号码', sort: true}
                                    , {field: 'plateColor', title: '车牌颜色', sort: true}
                                    , {field: 'plateNumber', title: '车牌号', sort: true}
                                    , {field: 'remark', title: '备注', sort: true}
                                    , {fixed: 'right', align: 'center', toolbar: '#DriverVehicleBar'}
                                ]],
                                // request: {
                                //     pageName: 'pageIndex' //页码的参数名称，默认：page
                                //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                // },
                                response: {
                                    countName: 'total' //数据总数的字段名称，默认：count
                                    , dataName: 'rows' //数据列表的字段名称，默认：data
                                }
                            });


                            table.on('tool(vehicleShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        console.log(vehicle);
                                        jQuery.ajax({
                                            url: "/driver/removeVehicle", async: false, type: "POST", data: {
                                                driverId: data.id,
                                                vehicleId: vehicle.id
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                table.reload('vehicleShow', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });
                            //
                            // table.on('checkbox(ownerShow)', function (obj) {
                            //
                            //     var checkStatus = table.checkStatus('ownerShow');
                            //
                            //     // console.log(mmm)
                            //
                            //     // console.log(obj.data); //选中行的相关数据
                            //
                            //
                            // });
                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#vehicle'
                        , url: '/vehicle/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ // 表头
                            {type: 'checkbox'},
                            //表头

                            {field: 'annualSurveyDate', title: '年检日期'}

                            , {field: 'deviceNumber', title: '设备号'}
                            , {field: 'installDate', title: '安装日期'}
                            , {field: 'motorcade', title: '车队', sort: true}
                            , {field: 'phoneNumber', title: '电话号码', sort: true}
                            , {field: 'plateColor', title: '车牌颜色', sort: true}
                            , {field: 'plateNumber', title: '车牌号', sort: true}
                            , {field: 'remark', title: '备注', sort: true}

                        ]],
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(vehicle)', function (obj) {

                        var checkStatus = table.checkStatus('vehicle');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });
        }
        else if (obj.event === 'setDrivingLisence') {
            var theDrivingLisence = [];
            var fff;
            // var DeviceFree = getDeviceFree();
            // var did = jQuery("#deviceId");
            // var mmp = this;
            // console.log(DeviceFree);
            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theDrivingLisence" lay-filter="theDrivingLisence"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);
                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theDrivingLisence.push(fff[i].id);
                    }
                    console.log(theDrivingLisence);
                    // return;
                    jQuery.ajax({
                        url: "/driver/addDriverdl", type: "POST", async: false, traditional: true,
                        data: {
                            driverId: data.id,
                            dl: theDrivingLisence
                        },
                        success: function (res) {
                            console.log(res);
                            // jQuery.each(res, function (index, data) {
                            //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //     // console.log(ele)
                            //     // console.log(Option)
                            //     // console.log(ele.append);
                            //     ele.append(Option);
                            // });
                            // console.log(res);
                            layer.close(jjj);
                        }
                    });
                }
                , btn2: function () {
                    layer.close(jjj);
                },
                btn3: function () {
                    var eee = layer.open({
                        type: 1 //Page层类型
                        , content: '<table id="DrivingLisenceShow" lay-filter="DrivingLisenceShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                            // // return;
                            // jQuery.ajax({
                            //     url: "/vehicle/owners",   async: false, traditional: true,
                            //     data: {
                            //         vehicleId: data.id
                            //     },
                            //     success: function (res) {
                            //         console.log(res);
                            //         // jQuery.each(res, function (index, data) {
                            //         //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //         //     // console.log(ele)
                            //         //     // console.log(Option)
                            //         //     // console.log(ele.append);
                            //         //     ele.append(Option);
                            //         // });
                            //         // console.log(res);
                            //         layer.close(ccc);
                            //     }
                            // });
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            //第一个实例
                            table.render({
                                elem: '#DrivingLisenceShow'
                                , url: 'driver/driverdl', //数据接口
                                page: true, // 开启分页
                                where: {driverId: data.id}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {field: 'name', title: '司机名', sort: true},
                                    {field: 'sex', title: '性别', sort: true},
                                    {field: 'carClass', title: '准驾车型', sort: true},
                                    {field: 'dateOfFirstIssue', title: '初次领证日期', sort: true}
                                    , {fixed: 'right', align: 'center', toolbar: '#deleteBar'}
                                ]],
                                // request: {
                                //     pageName: 'pageIndex' //页码的参数名称，默认：page
                                //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                // },
                                response: {
                                    countName: 'total' //数据总数的字段名称，默认：count
                                    , dataName: 'rows' //数据列表的字段名称，默认：data
                                }
                            });


                            table.on('tool(DrivingLisenceShow)', function (obj) {
                                var dl = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        console.log(dl);
                                        jQuery.ajax({
                                            url: "/driver/removeDriverdl", async: false, type: "POST", data: {
                                                driverId: data.id,
                                                dl: dl.id
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                table.reload('DrivingLisenceShow', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });
                            //
                            // table.on('checkbox(ownerShow)', function (obj) {
                            //
                            //     var checkStatus = table.checkStatus('ownerShow');
                            //
                            //     // console.log(mmm)
                            //
                            //     // console.log(obj.data); //选中行的相关数据
                            //
                            //
                            // });
                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theDrivingLisence'
                        , url: 'drivingLicence/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ // 表头
                            {type: 'checkbox'},
                            //表头

                            {field: 'name', title: '司机名', sort: true},
                            {field: 'sex', title: '性别', sort: true},
                            {field: 'carClass', title: '准驾车型', sort: true},
                            {field: 'dateOfFirstIssue', title: '初次领证日期', sort: true}

                        ]],
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theDrivingLisence)', function (obj) {

                        var checkStatus = table.checkStatus('theDrivingLisence');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });
        }
        else if (obj.event === 'setVehicleLicense') {
            var theVehicleLicense = [];
            var fff;
            // var DeviceFree = getDeviceFree();
            // var did = jQuery("#deviceId");
            // var mmp = this;
            // console.log(DeviceFree);
            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theVehicleLicense" lay-filter="theVehicleLicense"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);
                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theVehicleLicense.push(fff[i].id);
                    }
                    console.log(theVehicleLicense);
                    // return;
                    jQuery.ajax({
                        url: "/driver/addDrivervl", type: "POST", async: false, traditional: true,
                        data: {
                            driverId: data.id,
                            vl: theVehicleLicense
                        },
                        success: function (res) {
                            console.log(res);
                            // jQuery.each(res, function (index, data) {
                            //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //     // console.log(ele)
                            //     // console.log(Option)
                            //     // console.log(ele.append);
                            //     ele.append(Option);
                            // });
                            // console.log(res);
                            layer.close(jjj);
                        }
                    });
                }
                , btn2: function () {
                    layer.close(jjj);
                },
                btn3: function () {
                    var eee = layer.open({
                        type: 1 //Page层类型
                        , content: '<table id="VehicleLicenseShow" lay-filter="VehicleLicenseShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                            // // return;
                            // jQuery.ajax({
                            //     url: "/vehicle/owners",   async: false, traditional: true,
                            //     data: {
                            //         vehicleId: data.id
                            //     },
                            //     success: function (res) {
                            //         console.log(res);
                            //         // jQuery.each(res, function (index, data) {
                            //         //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //         //     // console.log(ele)
                            //         //     // console.log(Option)
                            //         //     // console.log(ele.append);
                            //         //     ele.append(Option);
                            //         // });
                            //         // console.log(res);
                            //         layer.close(ccc);
                            //     }
                            // });
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            //第一个实例
                            table.render({
                                elem: '#VehicleLicenseShow'
                                , url: 'driver/drivervl', //数据接口
                                page: true, // 开启分页
                                where: {driverId: data.id}
                                ,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {field: 'owner', title: '所有人', sort: true},
                                    {field: 'overallSize', title: '外廓尺寸', sort: true},
                                    {field: 'tractionMass', title: '准牵引总质量', sort: true},
                                    {field: 'address', title: '住址', sort: true},
                                    {field: 'approvedPassengersCapacity', title: '核定载人数', sort: true},
                                    {field: 'inspectionRecord', title: '检验记录', sort: true},
                                    {field: 'vehicleType', title: '车辆类型', sort: true},
                                    {field: 'registerDate', title: '注册日期', sort: true},
                                    {field: 'remark', title: '备注', sort: true},
                                    {field: 'approvedLoad', title: '核定载质量', sort: true},
                                    {field: 'engineNo', title: '发动机号码', sort: true},
                                    {field: 'issueDate', title: '发证日期', sort: true},
                                    {field: 'plateNo', title: '车牌号', sort: true},
                                    {field: 'documentNo', title: '档案编号', sort: true},
                                    {field: 'useCharacter', title: '使用性质', sort: true},
                                    {field: 'model', title: '品牌型号', sort: true},
                                    {field: 'vin', title: '车辆识别代号', sort: true},
                                    {field: 'curbWeight', title: '整备质量', sort: true},
                                    {field: 'totalMass', title: '总质量', sort: true}

                                    , {fixed: 'right', align: 'center', toolbar: '#deleteBar'}
                                ]],
                                // request: {
                                //     pageName: 'pageIndex' //页码的参数名称，默认：page
                                //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                // },
                                response: {
                                    countName: 'total' //数据总数的字段名称，默认：count
                                    , dataName: 'rows' //数据列表的字段名称，默认：data
                                }
                            });


                            table.on('tool(VehicleLicenseShow)', function (obj) {
                                var vl = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        console.log(vl);
                                        jQuery.ajax({
                                            url: "/driver/removeDrivervl", async: false, type: "POST", data: {
                                                driverId: data.id,
                                                vl: vl.id
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                table.reload('VehicleLicenseShow', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });
                            //
                            // table.on('checkbox(ownerShow)', function (obj) {
                            //
                            //     var checkStatus = table.checkStatus('ownerShow');
                            //
                            //     // console.log(mmm)
                            //
                            //     // console.log(obj.data); //选中行的相关数据
                            //
                            //
                            // });
                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theVehicleLicense'
                        , url: '/vehicleLicence/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        ,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ // 表头
                            {type: 'checkbox'},
                            //表头

                            {field: 'owner', title: '所有人', sort: true},
                            {field: 'overallSize', title: '外廓尺寸', sort: true},
                            {field: 'tractionMass', title: '准牵引总质量', sort: true},
                            {field: 'address', title: '住址', sort: true},
                            {field: 'approvedPassengersCapacity', title: '核定载人数', sort: true},
                            {field: 'inspectionRecord', title: '检验记录', sort: true},
                            {field: 'vehicleType', title: '车辆类型', sort: true},
                            {field: 'registerDate', title: '注册日期', sort: true},
                            {field: 'remark', title: '备注', sort: true},
                            {field: 'approvedLoad', title: '核定载质量', sort: true},
                            {field: 'engineNo', title: '发动机号码', sort: true},
                            {field: 'issueDate', title: '发证日期', sort: true},
                            {field: 'plateNo', title: '车牌号', sort: true},
                            {field: 'documentNo', title: '档案编号', sort: true},
                            {field: 'useCharacter', title: '使用性质', sort: true},
                            {field: 'model', title: '品牌型号', sort: true},
                            {field: 'vin', title: '车辆识别代号', sort: true},
                            {field: 'curbWeight', title: '整备质量', sort: true},
                            {field: 'totalMass', title: '总质量', sort: true}

                        ]],
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theVehicleLicense)', function (obj) {

                        var checkStatus = table.checkStatus('theVehicleLicense');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });
        }
        else if (obj.event === 'setRegisterService') {
            var theVehicleRegister = [];
            var fff;
            // var DeviceFree = getDeviceFree();
            // var did = jQuery("#deviceId");
            // var mmp = this;
            // console.log(DeviceFree);
            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theVehicleRegister" lay-filter="theVehicleRegister"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);
                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theVehicleRegister.push(fff[i].id);
                    }
                    console.log(theVehicleRegister);
                    // return;
                    jQuery.ajax({
                        url: "/driver/addDrivervr", type: "POST", async: false, traditional: true,
                        data: {
                            driverId: data.id,
                            vr: theVehicleRegister
                        },
                        success: function (res) {
                            console.log(res);
                            // jQuery.each(res, function (index, data) {
                            //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //     // console.log(ele)
                            //     // console.log(Option)
                            //     // console.log(ele.append);
                            //     ele.append(Option);
                            // });
                            // console.log(res);
                            layer.close(jjj);
                        }
                    });
                }
                , btn2: function () {
                    layer.close(jjj);
                },
                btn3: function () {
                    var eee = layer.open({
                        type: 1 //Page层类型
                        , content: '<table id="VehicleRegisterShow" lay-filter="VehicleRegisterShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                            // // return;
                            // jQuery.ajax({
                            //     url: "/vehicle/owners",   async: false, traditional: true,
                            //     data: {
                            //         vehicleId: data.id
                            //     },
                            //     success: function (res) {
                            //         console.log(res);
                            //         // jQuery.each(res, function (index, data) {
                            //         //     var Option = "<option value='" + data.name + "'>" + data.name + "</option>";
                            //         //     // console.log(ele)
                            //         //     // console.log(Option)
                            //         //     // console.log(ele.append);
                            //         //     ele.append(Option);
                            //         // });
                            //         // console.log(res);
                            //         layer.close(ccc);
                            //     }
                            // });
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            //第一个实例
                            table.render({
                                elem: '#VehicleRegisterShow'
                                , url: 'driver/drivervr', //数据接口
                                page: true, // 开启分页
                                where: {driverId: data.id}
                                ,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {field: 'exFactoryDate', title: '出厂日期', sort: true},
                                    {field: 'color', title: '颜色', sort: true},
                                    {field: 'purposeOfUse', title: '使用性质', sort: true},
                                    {field: 'registerNo', title: '登记编号', sort: true},
                                    {field: 'importOrNot', title: '是否国产', sort: true},
                                    {field: 'numberOfSpringLeaf', title: '钢板弹簧片数', sort: true},
                                    {field: 'type', title: '车辆类型', sort: true},
                                    {field: 'engineModel', title: '发动机型号', sort: true},
                                    {field: 'steeringType', title: '转向形式', sort: true},
                                    {field: 'manufacturer', title: '制造厂', sort: true},
                                    {field: 'grossMass', title: '总质量', sort: true},
                                    {field: 'meansOfAcquisition', title: '获得方式', sort: true},
                                    {field: 'totalTractionWeight', title: '准牵引总质量', sort: true},
                                    {field: 'numberOfWheel', title: '轮胎数', sort: true},
                                    {field: 'engineNo', title: '发动机号', sort: true},
                                    {field: 'model', title: '车辆型号', sort: true},
                                    {field: 'engineNo', title: '发动机号', sort: true},
                                    {field: 'fuelType', title: '燃料种类', sort: true},
                                    {field: 'track', title: '轮距', sort: true},
                                    {field: 'numberOfAxles', title: '轴数', sort: true},
                                    {field: 'brand', title: '车辆品牌', sort: true},
                                    {field: 'passengerCapacityOfCab', title: '驾驶室载客', sort: true},
                                    {field: 'registerOffice', title: '登记机关', sort: true},
                                    {field: 'internalSize', title: '货箱内部尺寸', sort: true},
                                    {field: 'ownerNumber', title: '所有人', sort: true},
                                    {field: 'identifyCode', title: '识别代号', sort: true},
                                    {field: 'tyreSize', title: '轮胎规格', sort: true},
                                    {field: 'registerDate', title: '登记日期', sort: true},
                                    {field: 'ratedPassengerCapacity', title: '核定载客', sort: true},
                                    {field: 'allSize', title: '外廓尺寸', sort: true},
                                    {field: 'outputVolume', title: '排量', sort: true},
                                    {field: 'wheelbase', title: '轴距', sort: true},
                                    {field: 'ratedLoadingWeight', title: '核定载质量', sort: true}

                                    , {fixed: 'right', align: 'center', toolbar: '#deleteBar'}
                                ]],
                                // request: {
                                //     pageName: 'pageIndex' //页码的参数名称，默认：page
                                //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                // },
                                response: {
                                    countName: 'total' //数据总数的字段名称，默认：count
                                    , dataName: 'rows' //数据列表的字段名称，默认：data
                                }
                            });


                            table.on('tool(VehicleRegisterShow)', function (obj) {
                                var vr = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        console.log(vr);
                                        jQuery.ajax({
                                            url: "/driver/removeDrivervr", async: false, type: "POST", data: {
                                                driverId: data.id,
                                                vr: vr.id
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                table.reload('VehicleRegisterShow', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });
                            //
                            // table.on('checkbox(ownerShow)', function (obj) {
                            //
                            //     var checkStatus = table.checkStatus('ownerShow');
                            //
                            //     // console.log(mmm)
                            //
                            //     // console.log(obj.data); //选中行的相关数据
                            //
                            //
                            // });
                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theVehicleRegister'
                        , url: '/vehicleRegister/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        ,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ // 表头
                            {type: 'checkbox'},
                            //表头
                            {field: 'exFactoryDate', title: '出厂日期', sort: true},
                            {field: 'color', title: '颜色', sort: true},
                            {field: 'purposeOfUse', title: '使用性质', sort: true},
                            {field: 'registerNo', title: '登记编号', sort: true},
                            {field: 'importOrNot', title: '是否国产', sort: true},
                            {field: 'numberOfSpringLeaf', title: '钢板弹簧片数', sort: true},
                            {field: 'type', title: '车辆类型', sort: true},
                            {field: 'engineModel', title: '发动机型号', sort: true},
                            {field: 'steeringType', title: '转向形式', sort: true},
                            {field: 'manufacturer', title: '制造厂', sort: true},
                            {field: 'grossMass', title: '总质量', sort: true},
                            {field: 'meansOfAcquisition', title: '获得方式', sort: true},
                            {field: 'totalTractionWeight', title: '准牵引总质量', sort: true},
                            {field: 'numberOfWheel', title: '轮胎数', sort: true},
                            {field: 'engineNo', title: '发动机号', sort: true},
                            {field: 'model', title: '车辆型号', sort: true},
                            {field: 'engineNo', title: '发动机号', sort: true},
                            {field: 'fuelType', title: '燃料种类', sort: true},
                            {field: 'track', title: '轮距', sort: true},
                            {field: 'numberOfAxles', title: '轴数', sort: true},
                            {field: 'brand', title: '车辆品牌', sort: true},
                            {field: 'passengerCapacityOfCab', title: '驾驶室载客', sort: true},
                            {field: 'registerOffice', title: '登记机关', sort: true},
                            {field: 'internalSize', title: '货箱内部尺寸', sort: true},
                            {field: 'ownerNumber', title: '所有人', sort: true},
                            {field: 'identifyCode', title: '识别代号', sort: true},
                            {field: 'tyreSize', title: '轮胎规格', sort: true},
                            {field: 'registerDate', title: '登记日期', sort: true},
                            {field: 'ratedPassengerCapacity', title: '核定载客', sort: true},
                            {field: 'allSize', title: '外廓尺寸', sort: true},
                            {field: 'outputVolume', title: '排量', sort: true},
                            {field: 'wheelbase', title: '轴距', sort: true},

                        ]],
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theVehicleRegister)', function (obj) {

                        var checkStatus = table.checkStatus('theVehicleRegister');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });
        }

    });
    //监听工具条
    var $ = layui.$, active = {
        add: function () { //获取选中数据
            layer.open({
                type: 1 //Page层类型
                , title: '添加'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 0 //0-6的动画形式，-1不开启
                , content: mmp,
                btn: ['取消'] //只是为了演示
                , area: ['700px', '500px']
                , yes: function () {
                    console.log("no")
                    layer.closeAll();
                }
                ,
                success: function (layero, index) {
                    console.log(layero, index);
                    var laydate = layui.laydate;
                    laydate.render({
                        elem: '#drivingLicenseExpiryDate' //指定元素
                        , done: function (value, date, endDate) {
                            console.log(value); //得到日期生成的值，如：2017-08-18
                            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                        }
                    });


                    getYourDICK(13, jQuery("#idType"));


                    var form = layui.form;
                    form.render();


                    form.on('submit(formDemo)', function (data) {

                        console.log(data.field);

                        // return false;


                        jQuery.ajax({
                            url: "/driver/create.form",
                            type: "POST",
                            data: data.field,
                            success: function (res) {

                                console.log(res);

                                res.error ? layer.msg(res.error) : layer.closeAll();

                                table.reload('idTest', {
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });
                            }
                        });

                        // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                        // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                        console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
                        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                    });

                }
            });


        }
        , modify: function () { //获取选中数目


            var checkStatus = table.checkStatus('idTest')
                , data = checkStatus.data;
            console.log(data.length)

            if (checkStatus.data.length === 1) {
                layer.open({
                    type: 1 //Page层类型
                    , title: '修改'
                    , shade: 0.6 //遮罩透明度
                    , maxmin: true //允许全屏最小化
                    , anim: 0 //0-6的动画形式，-1不开启
                    , content: mmp,
                    btn: ['取消'] //只是为了演示
                    , area: ['700px', '500px']
                    , yes: function () {
                        console.log("no");
                        layer.closeAll();
                    }
                    ,
                    success: function (layero, index) {
                        console.log(layero, index);
                        var laydate = layui.laydate;


                        getYourDICK(13, jQuery("#idType"));


                        var form = layui.form;
                        form.render();


                        jQuery.ajax({
                            url: "/driver/edit.form",

                            data: {id: data[0].id}
                            ,
                            async: false,
                            success: function (res) {

                                console.log(res);

                                laydate.render({
                                    elem: '#drivingLicenseExpiryDate' //指定元素
                                    , format: "yyyy-MM-dd HH:mm:ss"
                                    , value: res.drivingLicenseExpiryDate
                                    , done: function (value, date, endDate) {
                                        console.log(value); //得到日期生成的值，如：2017-08-18
                                        console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                                        console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                                    }
                                });

                                jQuery("#id").val(res.id);
                                jQuery("#name").val(res.name);

                                var mmmmm = res.sex;
                                jQuery(":radio[name='sex'][value='" + mmmmm + "']").prop("checked", "checked");
                                jQuery("#phone").val(res.phone);
                                jQuery("#remark").val(res.remark);
                                jQuery("#idType").val(res.idType);
                                jQuery("#idNumber").val(res.idNumber);
                                jQuery("#drivingLicenseNumber").val(res.drivingLicenseNumber);
                                console.log(jQuery("#id").val());
                                console.log(res.id);
                                form.render();
                            }
                        });


                        form.on('submit(formDemo)', function (data) {
                            console.log(jQuery("#id").val());
                            console.log(data.field);


                            jQuery.ajax({
                                url: "/driver/edit.form",
                                type: "POST",
                                data: data.field,
                                success: function (res) {

                                    console.log(res);

                                    res.error ? layer.msg(res.error) : layer.closeAll();

                                    table.reload('idTest', {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                }
                            });

                            // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                            // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                            console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
                            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                        });

                    }
                });
            }


        }
        , delete: function () { //验证是否全选

            var checkStatus = table.checkStatus('idTest')
                , data = checkStatus.data;

            if (checkStatus.data.length > 1) {
                layer.msg('请一个个删除');
            } else if (checkStatus.data.length === 0) {
                layer.msg('先選一個');
            } else {
                layer.confirm('真的删除行么？', {
                    btn: ['是', '否'] //按钮
                }, function () {
                    jQuery.ajax({
                        url: "/driver/delete", type: "POST", data: {id: data[0].id}, async: false,
                        success: function (res) {
                            layer.msg('OK', {icon: 1});
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            console.log(res);
                        }
                    });

                }, function () {
                    layer.close();
                });
            }


        },
        reload: function () {
            var tableReload = $('#tableReload');
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    filter: tableReload.val()
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
