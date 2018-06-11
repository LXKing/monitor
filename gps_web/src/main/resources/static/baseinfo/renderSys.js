var renderSys = function (url) {

    var mmp = `
      <form class="layui-form layui-form-pane" action="" style="color: black">
	<div class="layui-form-item layui-inline pane" style="display: none">
		<label class="layui-form-label"></label>
		<div class="layui-input-block">
			<input type="text" id="iD" name="iD" class="layui-input"/>
			<input id="cOMID" name="cOMID" type="hidden"/>
		</div>
	</div>
	
	

	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">系统VID</label>
		<div class="layui-input-block">
			<input type="text" name="sYSVID" id="sYSVID" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">终端ID</label>-->
		<!--<div class="layui-input-block">-->
				<!--<input type="text" name="dEVICEID" id="dEVICEID" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">-->
		<!--</div>-->
	<!--</div>-->
	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">车辆ID</label>-->
		<!--<div class="layui-input-block">-->
				<!--<input type="text" name="cARID" id="cARID" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">-->
		<!--</div>-->
	<!--</div>-->

	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">司机ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="dRIVERID" id="dRIVERID" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">SIM卡ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="sIMID" id="sIMID" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!---->
		<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">车队ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="cARGROUPID" id="cARGROUPID" placeholder="请输入" required lay-verify="required" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">行车证ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="cARLICECEID" id="cARLICECEID" required lay-verify="required" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!---->
		<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">登记证ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="cARREGID" id="cARREGID" required lay-verify="required" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!---->
		<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">准驾证ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="dRIVERLICENCEID" id="dRIVERLICENCEID" required lay-verify="required" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">所属公司ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="cOMPANYID" id="cOMPANYID" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!--<div class="layui-form-item layui-inline pane">-->
		<!--<label class="layui-form-label">所属账号ID</label>-->
		<!--<div class="layui-input-block">-->
			<!--<input type="text" name="uSERID" id="uSERID" required lay-verify="required" placeholder="请输入" class="layui-input"/>-->
		<!--</div>-->
	<!--</div>-->
	<!---->
	
		<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">系统VID激活日期</label>
		<div class="layui-input-block">
			<input type="text" name="sYSVIDACTIVATIONDATE" id="sYSVIDACTIVATIONDATE" required lay-verify="required" placeholder="请输入" class="layui-input"/>
		</div>
	</div>
	
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">系统VID到期时间</label>
		<div class="layui-input-block">
			<input type="text" name="eXPIREDATE" id="eXPIREDATE" required lay-verify="required" placeholder="请输入" class="layui-input"/>
		</div>
	</div>	
	
	 
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">系统VID到期提醒时间</label>
		<div class="layui-input-block">
			<input type="text" name="eXPIRENOTIFYDATE" id="eXPIRENOTIFYDATE" required lay-verify="required" placeholder="请输入" class="layui-input"/>
		</div>
	</div>
 
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">备注</label>
		<div class="layui-input-block">
			<input type="text" name="rEMARK" id="rEMARK" required lay-verify="required" placeholder="请输入" class="layui-input"/>
		</div>
	</div>
	
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">业务代表</label>
		<div class="layui-input-block">
			<input type="text" name="bUSSINESSAGENT" id="bUSSINESSAGENT" required lay-verify="required" placeholder="请输入" class="layui-input"/>
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


    document.getElementById("shitBar").innerHTML = shitbar;
    var table = layui.table;
    console.log(url);


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
                {field: 'sYSVID', title: '系统VID', sort: true},

                {fixed: 'right', align: 'center', toolbar: '#renderSysBar'}

            ]
        ],
        skin: "nob",
        size: "sm"
        // response: {
        //     countName: 'total' //数据总数的字段名称，默认：count
        //     , dataName: 'rows' //数据列表的字段名称，默认：data
        // }
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
                area: ['950px', '500px'],
                btn: ['取消'] //只是为了演示
                , yes: function () {
                    console.log("no")
                    layer.closeAll();
                }
                ,
                success: function (layero, index) {
                    console.log(layero, index);


                    var fuck;
                    // 即时校验
                    jQuery("#sYSVID").bind("input propertychange", function () {
                        let name = this.value;

                        console.log(name);
                        jQuery.ajax({
                            url: "/svid/exist",
                            type: "POST",
                            data: {
                                sysvid: name,

                            },
                            success: function (res) {
                                fuck = res;
                                console.log(fuck);
                                if (!fuck) {
                                    layer.msg('已存在');
                                }

                            }
                        });
                    });


                    var laydate = layui.laydate;
                    laydate.render({
                        elem: '#eXPIREDATE' //指定元素
                        , done: function (value, date, endDate) {
                            console.log(value); //得到日期生成的值，如：2017-08-18
                            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                        }
                    });
                    laydate.render({
                        elem: '#sYSVIDACTIVATIONDATE' //指定元素
                        , done: function (value, date, endDate) {
                            console.log(value); //得到日期生成的值，如：2017-08-18
                            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                        }
                    });

                    laydate.render({
                        elem: '#eXPIRENOTIFYDATE' //指定元素
                        , done: function (value, date, endDate) {
                            console.log(value); //得到日期生成的值，如：2017-08-18
                            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                        }
                    });

                    var form = layui.form;
                    form.render();


                    form.on('submit(formDemo)', function (data) {

                        console.log(data.field);

                        // return false;


                        if (!fuck) {
                            return false;
                        }


                        jQuery.ajax({
                            url: "/svid/add",
                            type: "POST",
                            data: data.field,
                            success: function (res) {

                                console.log(res);

                                res.error ? layer.msg(res.msg) : layer.closeAll();

                                table.reload('idTest', {
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });
                            },
                            error: function (res) {
                                console.log(res)
                                console.log(res.responseJSON.msg)
                                layer.msg(res.responseJSON.msg)
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
                    area: ['950px', '500px'],
                    btn: ['取消'] //只是为了演示
                    , yes: function () {
                        console.log("no");
                        layer.closeAll();
                    }
                    ,
                    success: function (layero, index) {
                        console.log(layero, index);


                        var fuck = true;

                        // 即时校验
                        jQuery("#sYSVID").bind("input propertychange", function () {
                            let name = this.value;

                            console.log(name);
                            jQuery.ajax({
                                url: "/svid/exist",
                                type: "POST",
                                data: {
                                    sysvid: name,

                                },
                                success: function (res) {
                                    fuck = res;
                                    console.log(fuck);
                                    if (!fuck) {
                                        layer.msg('已存在');
                                    }

                                }
                            });
                        });


                        var form = layui.form;

                        // form.render();


                        jQuery.ajax({
                            url: "/svid/get",

                            data: {id: data[0].iD}
                            ,
                            async: false,
                            success: function (res) {


                                var laydate = layui.laydate;
                                laydate.render({
                                    elem: '#eXPIREDATE' //指定元素
                                    , value: res.eXPIREDATE
                                    , done: function (value, date, endDate) {
                                        console.log(value); //得到日期生成的值，如：2017-08-18
                                        console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                                        console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                                    }
                                });
                                laydate.render({
                                    elem: '#sYSVIDACTIVATIONDATE' //指定元素
                                    , value: res.sYSVIDACTIVATIONDATE
                                    , done: function (value, date, endDate) {
                                        console.log(value); //得到日期生成的值，如：2017-08-18
                                        console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                                        console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                                    }
                                });

                                laydate.render({
                                    elem: '#eXPIRENOTIFYDATE' //指定元素
                                    , value: res.eXPIRENOTIFYDATE
                                    , done: function (value, date, endDate) {
                                        console.log(value); //得到日期生成的值，如：2017-08-18
                                        console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                                        console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                                    }
                                });


                                console.log(res);


                                jQuery("#iD").val(res.iD);


                                jQuery("#cOMID").val(res.cOMID);
                                jQuery("#sYSVID").val(res.sYSVID);
                                jQuery("#rEMARK").val(res.rEMARK);
                                jQuery("#bUSSINESSAGENT").val(res.bUSSINESSAGENT);


                                console.log(jQuery("#id").val());

                                form.render();
                            }
                        });


                        form.on('submit(formDemo)', function (data) {
                            console.log(jQuery("#id").val());
                            console.log(data.field);

                            // return false;

                            if (!fuck && jQuery("#sYSVID").val().length !== 0) {
                                return false;
                            }
                            jQuery.ajax({
                                url: "/svid/update",
                                type: "POST",
                                data: data.field,
                                success: function (res) {

                                    console.log(res);

                                    res.error ? layer.msg(res.msg) : layer.closeAll();

                                    table.reload('idTest', {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                },
                                error: function (res) {
                                    console.log(res)
                                    console.log(res.responseJSON.msg)
                                    layer.msg(res.responseJSON.msg)
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
                layer.msg('为了安全，请逐个删除');
            } else if (checkStatus.data.length === 0) {
                layer.msg('先選一個');
            } else {
                layer.confirm('真的删除行么？', {
                    btn: ['是', '否'] //按钮
                }, function () {
                    jQuery.ajax({
                        url: "/svid/remove", type: "POST", data: {id: data[0].iD}, async: false,
                        success: function (res) {
                            layer.msg(JSON.stringify(res.msg));
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


        }, reload: function () {
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
    // 表格内按鈕
    table.on('tool(table)', function (obj) {
        var data = obj.data;


        var object = obj;

        if (obj.event === 'driver') {
            var theDrivers = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="driver" lay-filter="driver"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);



                    if (data.dRIVERID !== "") {
                        var mmkk = data.dRIVERID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theDrivers.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }


                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theDrivers.push(fff[i].id);
                    }
                    console.log(theDrivers);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            dRIVERID: unique(theDrivers)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('driver', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        , content: '<table id="driverShow" lay-filter="driverShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            console.log(data.dRIVERID)

                            console.log(data.dRIVERID === "")
                            var vvv = data.dRIVERID;
                            if (vvv === "") {
                                vvv = "x";
                            }

                            //第一个实例
                            table.render({
                                elem: '#driverShow'
                                , url: '/svid/getDrivers', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头

                                    {field: 'name', title: '姓名', sort: true},
                                    {field: 'sex', title: '性别', sort: true},
                                    {field: 'phone', title: '电话', sort: true},
                                    {field: 'drivingLicenseNumber', title: '驾驶证号', sort: true},
                                    {field: 'remark', title: '备注', sort: true},
                                    {fixed: 'right', align: 'center', toolbar: '#VehicleDriverBar'}
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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(driverShow)', function (obj) {
                                var driver = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        console.log(driver);
                                        var aaaaaa = removeWithoutCopy(data.dRIVERID.split(","), driver.id).join(",");
                                        data.dRIVERID = aaaaaa;
                                        console.log(aaaaaa);
                                        console.log(data.dRIVERID);
                                        console.log(driver.id);
                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                dRIVERID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('driver', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#driver'
                        , url: '/driver/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ // 表头
                            {type: 'checkbox'},
                            {field: 'name', title: '姓名', sort: true},
                            {field: 'sex', title: '性别', sort: true},
                            {field: 'phone', title: '电话', sort: true},
                            {field: 'drivingLicenseNumber', title: '驾驶证号', sort: true},
                            {field: 'remark', title: '备注', sort: true}
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

                    table.on('checkbox(driver)', function (obj) {

                        var checkStatus = table.checkStatus('driver');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });
        }

        else if (obj.event === 'car') {

            var theVehicles = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="vehicle" lay-filter="vehicle"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);



                    if (data.cARID !== "") {
                        var mmkk = data.cARID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theVehicles.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }


                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theVehicles.push(fff[i].id);
                    }
                    console.log(theVehicles);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            cARID: unique(theVehicles)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('vehicle', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            console.log(data.dRIVERID)

                            console.log(data.dRIVERID === "")
                            var vvv = data.cARID;
                            if (vvv === "") {
                                vvv = "x";
                            }

                            //第一个实例
                            table.render({
                                elem: '#vehicleShow'
                                , url: '/svid/getVehicles', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {
                                        field: 'annualSurveyDate', title: '年检日期'


                                    }

                                    , {field: 'deviceNumber', title: '设备号'}
                                    , {
                                        field: 'installDate', title: '安装日期'


                                    }
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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(vehicleShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);
                                        var aaaaaa = removeWithoutCopy(data.cARID.split(","), vehicle.id).join(",");
                                        data.cARID = aaaaaa;
                                        console.log(aaaaaa);
                                        console.log(data.cARID);
                                        console.log(vehicle.id);
                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                cARID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('vehicle', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


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
                            {
                                field: 'annualSurveyDate', title: '年检日期'


                            }

                            , {field: 'deviceNumber', title: '设备号'}
                            , {
                                field: 'installDate', title: '安装日期'


                            }
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
        else if (obj.event === 'device') {

            var theDevice = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theDevice" lay-filter="theDevice"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);


                    if (data.dEVICEID !== "") {
                        var mmkk = data.dEVICEID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theDevice.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }

                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theDevice.push(fff[i].id);
                    }
                    console.log(theDevice);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            dEVICEID: unique(theDevice)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theDevice', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        , content: '<table id="DeviceShow" lay-filter="DeviceShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.dEVICEID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#DeviceShow'
                                , url: '/svid/getDevice', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ //表头
                                    {type: 'checkbox'},
                                    {field: 'deviceNumber', title: '设备号', sort: true},

                                    {field: 'factoryNumber', title: '出厂号', sort: true},
                                    {field: 'model', title: '型号', sort: true},
                                    {field: 'phoneNumber', title: '电话号码', sort: true},

                                    {field: 'remark', title: '说明', sort: true},
                                    {fixed: 'right', align: 'center', toolbar: '#DriverVehicleBar'}
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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(DeviceShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);

                                        // console.log(data.dEVICEID)

                                        // console.log(removeWithoutCopy(data.dEVICEID.split(","), vehicle.id + ""))
                                        console.log(data.dEVICEID.split(",").length)

                                        // var nnn = data.dEVICEID.split(",");
                                        // removeWithoutCopy(nnn, vehicle.id + "").join(",");

                                        // console.log(nnn)
                                        var aaaaaa = removeWithoutCopy(data.dEVICEID.split(","), vehicle.id + "").join(",");
                                        data.dEVICEID = aaaaaa;


                                        // console.error(data.dEVICEID);
                                        // console.error(nnn);
                                        // console.error(vehicle.id);
                                        // console.error(aaaaaa);
                                        // console.log(data.dEVICEID);

                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                dEVICEID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theDevice', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theDevice'
                        , url: '/device/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm",
                        cols: [
                            [
                                {type: 'checkbox'},
                                //表头

                                {field: 'deviceNumber', title: '设备号', sort: true},
                                {field: 'enable', title: '启用否', sort: true},
                                {field: 'factoryNumber', title: '出厂号', sort: true},
                                {field: 'model', title: '型号', sort: true},
                                {
                                    field: 'serviceEndDate', title: '服务结束时间', sort: true


                                },
                                {field: 'phoneNumber', title: '电话号码', sort: true},
                                {
                                    field: 'serviceStartDate', title: '服务开始时间', sort: true

                                },
                                {
                                    field: 'warranty', title: '保修期', sort: true
                                },
                                {field: 'protocolName', title: '协议', sort: true},
                                {field: 'iMEI', title: 'IMEI', sort: true},
                                {field: 'remark', title: '说明', sort: true},

                            ]
                        ],
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theDevice)', function (obj) {

                        var checkStatus = table.checkStatus('theDevice');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });


        }
        else if (obj.event === 'sim') {


            var theSIM = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theSIM" lay-filter="theSIM"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);
                    // 监听表格复选框选择


                    console.error(data.sIMID)

                    if (data.sIMID !== "") {
                        var mmkk = data.sIMID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theSIM.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }

                    console.log(theSIM);

                    // return;
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theSIM.push(fff[i].id);
                    }
                    console.log(theSIM);

                    // return;
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            sIMID: unique(theSIM)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theSIM', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        , content: '<table id="SIMShow" lay-filter="SIMShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.sIMID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#SIMShow'
                                , url: '/svid/getSIM', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ //表头


                                    {field: 'carrierOperator', title: '运营商', sort: true},
                                    {field: 'openSMS', title: '开通短信否', sort: true},
                                    {field: 'phoneNumber', title: '电话号码', sort: true},

                                    {field: 'remark', title: '说明', sort: true},

                                    {field: 'speechType', title: '语音类型', sort: true},
                                    {fixed: 'right', align: 'center', toolbar: '#DriverVehicleBar'}

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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(SIMShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);

                                        // console.log(data.dEVICEID)

                                        // console.log(removeWithoutCopy(data.dEVICEID.split(","), vehicle.id + ""))
                                        console.log(data.sIMID.split(",").length)

                                        // var nnn = data.dEVICEID.split(",");
                                        // removeWithoutCopy(nnn, vehicle.id + "").join(",");

                                        // console.log(nnn)
                                        var aaaaaa = removeWithoutCopy(data.sIMID.split(","), vehicle.id + "").join(",");
                                        data.sIMID = aaaaaa;


                                        // console.error(data.dEVICEID);
                                        // console.error(nnn);
                                        // console.error(vehicle.id);
                                        // console.error(aaaaaa);
                                        // console.log(data.dEVICEID);

                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                sIMID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theSIM', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theSIM'
                        , url: '/simcard/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ //表头
                            {type: 'checkbox'},


                            {field: 'carrierOperator', title: '运营商', sort: true},
                            {field: 'openSMS', title: '开通短信否', sort: true},
                            {field: 'phoneNumber', title: '电话号码', sort: true},

                            {field: 'remark', title: '说明', sort: true},

                            {field: 'speechType', title: '语音类型', sort: true}


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

                    table.on('checkbox(theSIM)', function (obj) {

                        var checkStatus = table.checkStatus('theSIM');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });


        }
        else if (obj.event === 'cargroup') {


            var theCG = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theCG" lay-filter="theCG"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);



                    if (data.cARGROUPID !== "") {
                        var mmkk = data.cARGROUPID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theCG.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }

                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theCG.push(fff[i].id);
                    }
                    console.log(theCG);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            cARGROUPID: unique(theCG)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theCG', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        , content: '<table id="CGShow" lay-filter="CGShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.cARGROUPID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#CGShow'
                                , url: '/svid/getCG', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ //表头

                                    {field: 'name', title: '名称', sort: true},
                                    {field: 'parentVisible', title: '上级可见否', sort: true},
                                    {field: 'type', title: '类型', sort: true},
                                    {field: 'remark', title: '说明', sort: true},
                                    {fixed: 'right', align: 'center', toolbar: '#DriverVehicleBar'}

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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(CGShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);

                                        // console.log(data.dEVICEID)

                                        // console.log(removeWithoutCopy(data.dEVICEID.split(","), vehicle.id + ""))
                                        console.log(data.cARGROUPID.split(",").length)

                                        // var nnn = data.dEVICEID.split(",");
                                        // removeWithoutCopy(nnn, vehicle.id + "").join(",");

                                        // console.log(nnn)
                                        var aaaaaa = removeWithoutCopy(data.cARGROUPID.split(","), vehicle.id + "").join(",");
                                        data.cARGROUPID = aaaaaa;


                                        // console.error(data.dEVICEID);
                                        // console.error(nnn);
                                        // console.error(vehicle.id);
                                        // console.error(aaaaaa);
                                        // console.log(data.dEVICEID);

                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                cARGROUPID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theCG', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theCG'
                        , url: '/motorcade/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ //表头
                            {type: 'checkbox'},

                            {field: 'name', title: '名称', sort: true},
                            {field: 'parentVisible', title: '上级可见否', sort: true},
                            {field: 'type', title: '类型', sort: true},
                            {field: 'remark', title: '说明', sort: true},


                        ]],
                        response: {
                            countName: 'total' //数据总数的字段名称，默认：count
                            , dataName: 'rows' //数据列表的字段名称，默认：data
                        },
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theCG)', function (obj) {

                        var checkStatus = table.checkStatus('theCG');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });


        }
        else if (obj.event === 'carL') {

            var theVehicleLicense = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theVehicleLicense" lay-filter="theVehicleLicense"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);


                    if (data.cARLICECEID !== "") {
                        var mmkk = data.cARLICECEID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theVehicleLicense.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }

                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theVehicleLicense.push(fff[i].id);
                    }
                    console.log(theVehicleLicense);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            cARLICECEID: unique(theVehicleLicense)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theVehicleLicense', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.cARLICECEID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#VehicleLicenseShow'
                                , url: '/svid/getVehicleLicense', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头

                                    //表头

                                    {field: 'owner', title: '所有人', sort: true},
                                    {field: 'overallSize', title: '外廓尺寸', sort: true},
                                    {field: 'tractionMass', title: '准牵引总质量', sort: true},
                                    {field: 'address', title: '住址', sort: true},
                                    {field: 'approvedPassengersCapacity', title: '核定载人数', sort: true},
                                    {field: 'inspectionRecord', title: '检验记录', sort: true},
                                    {field: 'vehicleType', title: '车辆类型', sort: true},
                                    {
                                        field: 'registerDate', title: '注册日期', sort: true


                                    },
                                    {field: 'remark', title: '备注', sort: true},
                                    {field: 'approvedLoad', title: '核定载质量', sort: true},
                                    {field: 'engineNo', title: '发动机号码', sort: true},
                                    {
                                        field: 'issueDate', title: '发证日期', sort: true


                                    },
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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(VehicleLicenseShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);
                                        var nnn = data.cARLICECEID.split(",");
                                        var aaaaaa = removeWithoutCopy(nnn, vehicle.id + "").join(",");
                                        // data.cARID = aaaaaa;
                                        console.log(aaaaaa);
                                        console.log(nnn);
                                        console.log(data.cARLICECEID);
                                        console.log(vehicle.id);
                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                cARLICECEID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theVehicleLicense', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


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
                        , cellMinWidth: 150,
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
                            {
                                field: 'registerDate', title: '注册日期', sort: true


                            },
                            {field: 'remark', title: '备注', sort: true},
                            {field: 'approvedLoad', title: '核定载质量', sort: true},
                            {field: 'engineNo', title: '发动机号码', sort: true},
                            {
                                field: 'issueDate', title: '发证日期', sort: true


                            },
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
        else if (obj.event === 'carR') {


            var theVehicleRegister = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theVehicleRegister" lay-filter="theVehicleRegister"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);


                    if (data.cARREGID !== "") {
                        var mmkk = data.cARREGID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theVehicleRegister.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }






                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theVehicleRegister.push(fff[i].id);
                    }
                    console.log(theVehicleRegister);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            cARREGID: unique(theVehicleRegister)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theVehicleRegister', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.cARREGID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#VehicleRegisterShow'
                                , url: '/svid/getVehicleRegister', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {
                                        field: 'exFactoryDate', title: '出厂日期', sort: true

                                    },
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
                                    {
                                        field: 'registerDate', title: '登记日期', sort: true

                                    },
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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(VehicleRegisterShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);
                                        var nnn = data.cARREGID.split(",");
                                        var aaaaaa = removeWithoutCopy(nnn, vehicle.id + "").join(",");
                                        // data.cARID = aaaaaa;
                                        console.log(aaaaaa);
                                        console.log(nnn);
                                        console.log(data.cARREGID);
                                        console.log(vehicle.id);
                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                cARREGID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theVehicleRegister', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


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
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm"
                        , cols: [[ // 表头
                            {type: 'checkbox'},
                            //表头
                            {
                                field: 'exFactoryDate', title: '出厂日期', sort: true

                            },
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
                            {
                                field: 'registerDate', title: '登记日期', sort: true


                            },
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
        else if (obj.event === 'DrivingL') {


            var theDrivingLisence = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theDrivingLisence" lay-filter="theDrivingLisence"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);


                    if (data.dRIVERLICENCEID !== "") {
                        var mmkk = data.dRIVERLICENCEID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theDrivingLisence.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }



                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theDrivingLisence.push(fff[i].id);
                    }
                    console.log(theDrivingLisence);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            dRIVERLICENCEID: unique(theDrivingLisence)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theDrivingLisence', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.dRIVERLICENCEID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#DrivingLisenceShow'
                                , url: '/svid/getDrivingLis', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm"
                                , cols: [[ // 表头
                                    {field: 'name', title: '司机名', sort: true},
                                    {field: 'sex', title: '性别', sort: true},
                                    {field: 'carClass', title: '准驾车型', sort: true},
                                    {
                                        field: 'dateOfFirstIssue', title: '初次领证日期', sort: true


                                    }
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

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(DrivingLisenceShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);
                                        var nnn = data.dRIVERLICENCEID.split(",");
                                        var aaaaaa = removeWithoutCopy(nnn, vehicle.id + "").join(",");
                                        // data.cARID = aaaaaa;
                                        console.log(aaaaaa);
                                        console.log(nnn);
                                        console.log(data.dRIVERLICENCEID);
                                        console.log(vehicle.id);
                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                dRIVERLICENCEID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theDrivingLisence', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theDrivingLisence'
                        , url: '/drivingLicence/query', //数据接口
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
                            {
                                field: 'dateOfFirstIssue', title: '初次领证日期', sort: true

                            }

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
        else if (obj.event === 'company') {


            var theCOMPANY = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theCOMPANY" lay-filter="theCOMPANY"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);

                    if (data.cOMPANYID !== "") {
                        var mmkk = data.cOMPANYID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theCOMPANY.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }

                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theCOMPANY.push(fff[i].id);
                    }
                    console.log(theCOMPANY);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            cOMPANYID: unique(theCOMPANY)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theCOMPANY', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        , content: '<table id="theCOMPANYShow" lay-filter="theCOMPANYShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.cOMPANYID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#theCOMPANYShow'
                                , url: '/svid/getCompany', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm",
                                cols: [
                                    [


                                        //表头

                                        {field: 'fullName', title: '全称', sort: true},
                                        {field: 'shortName', title: '简称', sort: true},
                                        {field: 'officeAddress', title: '办公地址', sort: true},


                                        {field: 'ondutyPhone', title: '值班电话', sort: true},

                                        {fixed: 'right', align: 'center', toolbar: '#deleteBar'}

                                    ]
                                ],
                                // request: {
                                //     pageName: 'pageIndex' //页码的参数名称，默认：page
                                //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                // },
                                response: {
                                    countName: 'total' //数据总数的字段名称，默认：count
                                    , dataName: 'rows' //数据列表的字段名称，默认：data
                                }
                            });

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(theCOMPANYShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);

                                        // console.log(data.dEVICEID)

                                        // console.log(removeWithoutCopy(data.dEVICEID.split(","), vehicle.id + ""))
                                        console.log(data.cOMPANYID.split(",").length)

                                        // var nnn = data.dEVICEID.split(",");
                                        // removeWithoutCopy(nnn, vehicle.id + "").join(",");

                                        // console.log(nnn)
                                        var aaaaaa = removeWithoutCopy(data.cOMPANYID.split(","), vehicle.id + "").join(",");
                                        data.cOMPANYID = aaaaaa;


                                        // console.error(data.dEVICEID);
                                        // console.error(nnn);
                                        // console.error(vehicle.id);
                                        // console.error(aaaaaa);
                                        // console.log(data.dEVICEID);

                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                cOMPANYID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theCOMPANY', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theCOMPANY'
                        , url: '/company/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm",
                        cols: [
                            [
                                {type: 'checkbox'},
                                //表头

                                {field: 'fullName', title: '全称', sort: true},
                                {field: 'shortName', title: '简称', sort: true},
                                {field: 'officeAddress', title: '办公地址', sort: true},
                                {
                                    field: 'serviceStartDate', title: '服务开始时间', sort: true


                                },
                                {
                                    field: 'serviceEndDate', title: '服务结束时间', sort: true


                                },
                                {field: 'enable', title: '启用否', sort: true},
                                {field: 'createTime', title: '入网时间', sort: true},
                                {field: 'ondutyPhone', title: '值班电话', sort: true},
                                {field: 'remark', title: '说明', sort: true},


                            ]
                        ],
                        response: {
                            countName: 'total' //数据总数的字段名称，默认：count
                            , dataName: 'rows' //数据列表的字段名称，默认：data
                        },
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theCOMPANY)', function (obj) {

                        var checkStatus = table.checkStatus('theCOMPANY');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });


        }
        else if (obj.event === 'user') {


            var theUSER = [];
            var fff;


            var jjj = layer.open({
                type: 1 //Page层类型
                , content: '<table id="theUSER" lay-filter="theUSER"></table>',
                offset: 'lt',
                btn: ['添加', '取消', '查看已绑定'] //只是为了演示
                , yes: function () {
                    console.log(fff);


                    if (data.uSERID !== "") {
                        var mmkk = data.uSERID.split(",");
                        for (var l = mmkk.length - 1; l >= 0; l--) {
                            theUSER.push(mmkk[l]);
                        }
                    }

                    function unique(arr) {

                        var res = [];

                        var json = {};

                        for (var i = 0; i < arr.length; i++) {

                            if (!json[arr[i]]) {

                                res.push(arr[i]);

                                json[arr[i]] = 1;

                            }

                        }

                        return res;

                    }

                    // 监听表格复选框选择
                    for (var i = fff.length - 1; i >= 0; i--) {
                        theUSER.push(fff[i].id);
                    }
                    console.log(theUSER);
                    // return;
                    jQuery.ajax({
                        url: "/svid/update", type: "POST", async: false, traditional: true,
                        data: {
                            iD: data.iD,
                            uSERID: unique(theUSER)
                        },
                        success: function (res) {
                            console.log(res);
                            table.reload('idTest', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            table.reload('theUSER', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
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
                        , content: '<table id="USERShow" lay-filter="USERShow"></table>',
                        offset: 'lt',
                        btn: ['取消'] //只是为了演示
                        , yes: function () {
                            layer.close(eee);
                        }
                        ,
                        success: function (layero, index) {
                            var table = layui.table;

                            // console.log(data.dRIVERID)

                            // console.log(data.dRIVERID === "")
                            var vvv = data.uSERID;
                            if (vvv === "") {
                                vvv = "-1";
                            }

                            //第一个实例
                            table.render({
                                elem: '#USERShow'
                                , url: '/svid/getUser', //数据接口
                                page: false, // 开启分页
                                where: {id: vvv}
                                , cellMinWidth: 150,
                                skin: "nob",
                                size: "sm",
                                cols: [
                                    [
                                        {type: 'checkbox'},
                                        //表头

                                        {field: 'createTime', title: '注册时间', sort: true},
                                        {field: 'enable', title: '启用否', sort: true},
                                        {field: 'name', title: '名称', sort: true},
                                        {field: 'phone', title: '电话', sort: true},

                                        {field: 'remark', title: '说明', sort: true},
                                        {
                                            field: 'serviceEndDate', title: '服务结束时间', sort: true

                                        },
                                        {
                                            field: 'serviceStartDate', title: '服务开始时间', sort: true

                                        },
                                        {fixed: 'right', align: 'center', toolbar: '#deleteBar'}

                                    ]
                                ],
                                // request: {
                                //     pageName: 'pageIndex' //页码的参数名称，默认：page
                                //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                // },
                                response: {
                                    countName: 'total' //数据总数的字段名称，默认：count
                                    , dataName: 'rows' //数据列表的字段名称，默认：data
                                }
                            });

                            function removeWithoutCopy(arr, id) {
                                for (var i = arr.length - 1; i >= 0; i--) {
                                    if (arr[i] === id) {
                                        arr.splice(i, 1);
                                    }
                                }
                                return arr;
                            }

                            table.on('tool(USERShow)', function (obj) {
                                var vehicle = obj.data;
                                if (obj.event === 'del') {
                                    layer.confirm('真的删除行么', function (index) {
                                        // console.log(driver);

                                        // console.log(data.dEVICEID)

                                        // console.log(removeWithoutCopy(data.dEVICEID.split(","), vehicle.id + ""))
                                        console.log(data.uSERID.split(",").length)

                                        // var nnn = data.dEVICEID.split(",");
                                        // removeWithoutCopy(nnn, vehicle.id + "").join(",");

                                        // console.log(nnn)
                                        var aaaaaa = removeWithoutCopy(data.uSERID.split(","), vehicle.id + "").join(",");
                                        data.uSERID = aaaaaa;


                                        // console.error(data.dEVICEID);
                                        // console.error(nnn);
                                        // console.error(vehicle.id);
                                        // console.error(aaaaaa);
                                        // console.log(data.dEVICEID);

                                        jQuery.ajax({
                                            url: "/svid/update", async: false, type: "POST",

                                            data: {
                                                iD: data.iD,
                                                uSERID: aaaaaa
                                            },
                                            success: function (res) {
                                                console.log(res);
                                                // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // object.update({
                                                //     dRIVERID: aaaaaa
                                                //
                                                // });
                                                //执行重载
                                                table.reload('idTest', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                table.reload('theUSER', {
                                                    page: {
                                                        curr: 1 //重新从第 1 页开始
                                                    }
                                                });
                                                // table.reload('driverShow', {
                                                //     page: {
                                                //         curr: 1 //重新从第 1 页开始
                                                //     }
                                                // });
                                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                                // obj.del();
                                                layer.close(index);
                                            }
                                        });


                                    });
                                }
                            });


                        }
                    });
                },
                success: function (layero, index) {
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#theUSER'
                        , url: '/user/query', //数据接口
                        page: true, // 开启分页
                        where: {filter: ''}
                        , cellMinWidth: 150,
                        skin: "nob",
                        size: "sm",
                        cols: [
                            [
                                {type: 'checkbox'},
                                //表头

                                {field: 'createTime', title: '注册时间', sort: true},
                                {field: 'enable', title: '启用否', sort: true},
                                {field: 'name', title: '名称', sort: true},
                                {field: 'phone', title: '电话', sort: true},

                                {field: 'remark', title: '说明', sort: true},
                                {
                                    field: 'serviceEndDate', title: '服务结束时间', sort: true

                                },
                                {
                                    field: 'serviceStartDate', title: '服务开始时间', sort: true

                                },


                            ]
                        ],
                        response: {
                            countName: 'total' //数据总数的字段名称，默认：count
                            , dataName: 'rows' //数据列表的字段名称，默认：data
                        },
                        // request: {
                        //     pageName: 'pageIndex' //页码的参数名称，默认：page
                        //     , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                        // },
                        // response: {
                        //     countName: 'total' //数据总数的字段名称，默认：count
                        //     , dataName: 'rows' //数据列表的字段名称，默认：data
                        // }
                    });

                    table.on('checkbox(theUSER)', function (obj) {

                        var checkStatus = table.checkStatus('theUSER');
                        fff = checkStatus.data;
                        // console.log(mmm)

                        // console.log(obj.data); //选中行的相关数据


                    });
                }
            });


        }


    });


    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        console.log(type);
        console.log(active);
    });


};
