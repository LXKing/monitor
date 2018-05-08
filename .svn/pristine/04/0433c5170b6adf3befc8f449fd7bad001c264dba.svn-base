var renderDic = function (url) {

    var mmp = `
    
	  	<div class="layui-form-item layui-inline pane" style="margin-bottom: 0;">
		<label class="layui-form-label">类型</label>
		<div class="layui-input-block">
			<select name="TypeList" id="TypeList"  lay-filter="TypeList">
			</select>
		</div>
	</div>
 
    
    `;

    var mcc = `  <form class="layui-form layui-form-pane" action="" style="color: black">
	<div class="layui-form-item layui-inline pane" style="display: none">
		<label class="layui-form-label"></label>
		<div class="layui-input-block">
			<input type="text" id="id" name="id" class="layui-input" value="0"/>
			<input id="pid" name="pid"  class="layui-input" value="0"/>
			<input id="kind" name="kind"  class="layui-input"/>
		</div>
	</div>
	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">名称</label>
		<div class="layui-input-block">
			<input type="text" name="name" id="name" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		</div>
	</div>
	 	<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">编码</label>
		<div class="layui-input-block">
			<input type="text" name="code" id="code" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		</div>
	</div>
		<div class="layui-form-item layui-inline pane">
		<label class="layui-form-label">序号</label>
		<div class="layui-input-block">
			<input type="text" name="index" id="index" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item pane">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>`;

    var node = {};

    var trrrrrrr = `<div id="tableMain"> 
        <ul id="dataTree" class="ztree">

        </ul>
    </div>`;
    document.getElementById("shitBar").innerHTML = shitbar;
    jQuery("#mmpxxx").append(mmp);
    jQuery("#mmpxxx").prepend(`  <button class="layui-btn  layui-bg-blue ssss" data-type="addSiblingD">添加同级</button>
                <button class="layui-btn  layui-bg-blue ssss" data-type="addChildD">添加子级</button>`);

    jQuery(".layui-btn[data-type='reload']").hide();
    jQuery(".sbox").hide();
    jQuery(".layui-table-view").remove();
    var table = layui.table;
    table.render({});
    console.log(url);


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


    jQuery.ajax({
        url: "/dictionary/list", type: "POST", async: false,
        success: function (res) {
            shit = res;
            jQuery.each(res, function (index, data) {
                var Option = `<option value= ${data.name} dataid=${data.index}>  ${data.name}  </option>`;

                // console.log(ele)
                // console.log(Option)
                // console.log(ele.append);
                jQuery("#TypeList").append(Option);
            });


            var form = layui.form;
            form.render();

            setTimeout(function () {
                jQuery('select[name="TypeList"]').next().find('.layui-select-title input').click();
                jQuery('select[name="TypeList"]').next().find('.layui-anim').children('dd[lay-value="车牌颜色"]').click();

            }, 5);


            form.on('select(TypeList)', function (data) {
                // jQuery("#TypeList").val(jQuery("#TypeList").find("option:selected").attr("dataid"));
                console.log(data.elem); //得到select原始DOM对象
                console.log(data.value); //得到被选中的值
                console.log(data.othis); //得到美化后的DOM对象


                var authAll = getYourDICK(jQuery("#TypeList").find("option:selected").attr("dataid"), jQuery("#theTable"))
                node.kind = jQuery("#TypeList").find("option:selected").attr("dataid");

                jQuery("#theTable").html(trrrrrrr);
                var setting = {
                    view: {
                        showLine: false,
                        showIcon: false,
                        addDiyDom: addDiyDom
                    },
                    data: {
                        // mmp 大小写
                        simpleData: {
                            enable: true,
                            idKey: "id",
                            pIdKey: "pid",
                            rootPId: 0
                        }
                    },
                    callback: {
                        onClick: zTreeOnClick
                    },
                    check: {
                        enable: false,
                        chkboxType: {"Y": "ps", "N": "ps"}
                    }
                };


                function zTreeOnClick(event, treeId, treeNode) {
                    // mmp.val(treeNode.name);
                    // layer.close(kkk);
                    node.id = treeNode.id;
                    node.name = treeNode.name;
                    node.pid = treeNode.pid;
                    node.nodes=getChildNodes(treeNode,zttt);
                    console.log(getChildNodes(treeNode,zttt));
                    // alert(node + ", " + treeNode.name);
                }


                var zttt = jQuery.fn.zTree.init(jQuery("#dataTree"), setting, authAll);

//添加表头

                function getChildNodes(treeNode,zttt) {

                    var childNodes = zttt.transformToArray(treeNode);

                    var nodes = new Array();

                    for (i = 0; i < childNodes.length; i++) {

                        nodes.push(childNodes[i].id) ;

                    }

                    return nodes;

                }

                var li_head = ' <li class="head"><a><div class="diy">名称</div><div class="diy">编码</div><div class="diy">序号</div></a></li>';
                var rows = $("#dataTree").find('li');
                if (rows.length > 0) {
                    rows.eq(0).before(li_head)
                } else {
                    jQuery("#dataTree").append(li_head);
                    jQuery("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
                }

            });
            // console.log(res);
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
                , content: mcc,
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
                    jQuery("#name").bind("input propertychange", function () {
                        let name = this.value;

                        console.log(name);
                        jQuery.ajax({
                            url: "/dictionary/exist",
                            type: "POST",
                            data: {
                                name: name,
                                id: "",
                                kind: node.kind,
                                checkId: false

                            },
                            success: function (res) {
                                fuck = res;
                                console.log(fuck);
                                if (!fuck) {
                                    layer.msg('name已存在');
                                }

                            }
                        });
                    });

                    jQuery("#kind").val(node.kind);

                    var form = layui.form;
                    form.render();


                    form.on('submit(formDemo)', function (data) {

                        console.log(data.field);
                        console.log(node.kind);
                        console.log(node);

                        // return false;


                        // form.render();

                        if (!fuck) {
                            return false;
                        }


                        jQuery.ajax({
                            url: "/dictionary/create.form",
                            type: "POST",
                            data: data.field,
                            success: function (res) {

                                console.log(res);

                                res.error ? layer.msg(res.msg) : layer.closeAll();

                                setTimeout(function () {

                                    jQuery('select[name="TypeList"]').next().find('.layui-select-title input').click();
                                    jQuery('select[name="TypeList"]').next().find('.layui-anim').children('dd[class=layui-this]').click();

                                }, 5);
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


            layer.open({
                type: 1 //Page层类型
                , title: '修改'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 0 //0-6的动画形式，-1不开启
                , content: mcc,
                btn: ['取消'] //只是为了演示
                , yes: function () {
                    console.log("no");
                    layer.closeAll();
                }
                ,
                success: function (layero, index) {
                    console.log(layero, index);


                    var form = layui.form;

                    // form.render();


                    jQuery.ajax({
                        url: "/dictionary/edit.form",

                        data: {id: node.id}
                        ,
                        async: false,
                        success: function (res) {


                            console.log(res);


                            jQuery("#id").val(res.id);

                            jQuery("#name").val(res.name);
                            jQuery("#pid").val(res.pid);
                            jQuery("#kind").val(res.kind);
                            jQuery("#index").val(res.index);
                            jQuery("#code").val(res.code);

                            console.log(jQuery("#id").val());
                            console.log(res.id);
                            form.render();
                        }
                    });


                    form.on('submit(formDemo)', function (data) {
                        console.log(jQuery("#id").val());
                        console.log(data.field);

                        // return false;

                        jQuery.ajax({
                            url: "/dictionary/edit.form",
                            type: "POST",
                            data: data.field,
                            success: function (res) {

                                console.log(res);

                                res.error ? layer.msg(res.msg) : layer.closeAll();
                                setTimeout(function () {

                                    jQuery('select[name="TypeList"]').next().find('.layui-select-title input').click();
                                    jQuery('select[name="TypeList"]').next().find('.layui-anim').children('dd[class=layui-this]').click();

                                }, 5);

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
        , delete: function () { //验证是否全选


            layer.confirm('真的删除行么？', {
                btn: ['是', '否'] //按钮
            }, function () {
                // alert(node );
                console.log(node);
                jQuery.ajax({
                    url: "/dictionary/delete", type: "POST", data: {ids: node.nodes}, async: false,traditional: true,
                    success: function (res) {
                        layer.msg(JSON.stringify(res.msg));

                        setTimeout(function () {

                            jQuery('select[name="TypeList"]').next().find('.layui-select-title input').click();
                            jQuery('select[name="TypeList"]').next().find('.layui-anim').children('dd[class=layui-this]').click();

                        }, 5);


                        console.log(res);
                    }
                });

            }, function () {
                layer.close();
            });


        },
        addSiblingD: function () { //获取选中数据
            layer.open({
                type: 1 //Page层类型
                , title: '添加'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 0 //0-6的动画形式，-1不开启
                , content: mcc,
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
                    jQuery("#name").bind("input propertychange", function () {
                        let name = this.value;

                        console.log(name);
                        jQuery.ajax({
                            url: "/dictionary/exist",
                            type: "POST",
                            data: {
                                name: name,
                                id: "",
                                kind: node.kind,
                                checkId: false

                            },
                            success: function (res) {
                                fuck = res;
                                console.log(fuck);
                                if (!fuck) {
                                    layer.msg('name已存在');
                                }

                            }
                        });
                    });

                    jQuery("#kind").val(node.kind);
                    jQuery("#pid").val(node.pid);

                    var form = layui.form;
                    form.render();


                    form.on('submit(formDemo)', function (data) {

                        console.log(data.field);
                        console.log(node.kind);
                        console.log(node);

                        // return false;


                        // form.render();

                        if (!fuck) {
                            return false;
                        }


                        jQuery.ajax({
                            url: "/dictionary/create.form",
                            type: "POST",
                            data: data.field,
                            success: function (res) {

                                console.log(res);

                                res.error ? layer.msg(res.msg) : layer.closeAll();

                                setTimeout(function () {

                                    jQuery('select[name="TypeList"]').next().find('.layui-select-title input').click();
                                    jQuery('select[name="TypeList"]').next().find('.layui-anim').children('dd[class=layui-this]').click();

                                }, 5);
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


        },
        addChildD: function () { //获取选中数据
            layer.open({
                type: 1 //Page层类型
                , title: '添加'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 0 //0-6的动画形式，-1不开启
                , content: mcc,
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
                    jQuery("#name").bind("input propertychange", function () {
                        let name = this.value;

                        console.log(name);
                        jQuery.ajax({
                            url: "/dictionary/exist",
                            type: "POST",
                            data: {
                                name: name,
                                id: "",
                                kind: node.kind,
                                checkId: false

                            },
                            success: function (res) {
                                fuck = res;
                                console.log(fuck);
                                if (!fuck) {
                                    layer.msg('name已存在');
                                }

                            }
                        });
                    });

                    jQuery("#kind").val(node.kind);
                    jQuery("#pid").val(node.id);

                    var form = layui.form;
                    form.render();


                    form.on('submit(formDemo)', function (data) {

                        console.log(data.field);
                        console.log(node.kind);
                        console.log(node);

                        // return false;


                        // form.render();

                        if (!fuck) {
                            return false;
                        }


                        jQuery.ajax({
                            url: "/dictionary/create.form",
                            type: "POST",
                            data: data.field,
                            success: function (res) {

                                console.log(res);

                                res.error ? layer.msg(res.msg) : layer.closeAll();

                                setTimeout(function () {

                                    jQuery('select[name="TypeList"]').next().find('.layui-select-title input').click();
                                    jQuery('select[name="TypeList"]').next().find('.layui-anim').children('dd[class=layui-this]').click();

                                }, 5);
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
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        console.log(type);
        console.log(active);
    });

    /**
     * 自定义DOM节点
     */
    function addDiyDom(treeId, treeNode) {
        console.log(treeNode);
        console.log(treeId);
        var spaceWidth = 15;
        var liObj = $("#" + treeNode.tId);
        var aObj = $("#" + treeNode.tId + "_a");
        var switchObj = $("#" + treeNode.tId + "_switch");
        var icoObj = $("#" + treeNode.tId + "_ico");
        var spanObj = $("#" + treeNode.tId + "_span");
        aObj.attr('title', '');
        aObj.append('<div class="diy swich"></div>');
        var div = $(liObj).find('div').eq(0);
        switchObj.remove();
        spanObj.remove();
        icoObj.remove();
        div.append(switchObj);
        div.append(spanObj);
        var spaceStr = "<span style='height:1px;display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
        switchObj.before(spaceStr);
        var editStr = '';
        editStr += '<div class="diy">' + treeNode.code + '</div>';
        editStr += '<div class="diy">' + treeNode.index + '</div>';

        aObj.append(editStr);
    }


};

