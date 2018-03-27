<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link href="${ctx}/resources/shit/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/shit/css/Maincss.css" rel="stylesheet"/>

    <script src="${ctx}/resources/shit/jquery/jquery.2.1.4.min.js"></script>


</head>

<body>
<div>

    <div class="headtop">
        <!-- logo -->
        <%-- <div class="logoimg">
             <img src="${ctx}/image/logorun.png"/>
        </div> --%>
        <!-- 头部图标功能 -->
        <div class="imgtubiao">

            <ul>

                <li>
                    <img src="${ctx}/image/dian.png"/>
                    <span>在线监控</span>
                </li>

                <li>
                    <img src="${ctx}/image/shiping.png"/>
                    <span>视频监控</span>
                </li>

                <li>
                    <img src="${ctx}/image/user.png"/>
                    <span>司机管理</span>
                </li>

                <li>
                    <img src="${ctx}/image/che.png"/>
                    <span>多车监控</span>
                </li>

                <li>
                    <img src="${ctx}/image/shiping.png"/>
                    <span>视频监控</span>
                </li>

                <li>
                    <img src="${ctx}/image/user.png"/>
                    <span>司机管理</span>
                </li>

                <li>
                    <img src="${ctx}/image/che.png"/>
                    <span>多车监控</span>
                </li>

                <li>
                    <img src="${ctx}/image/shiping.png"/>
                    <span>视频监控</span>
                </li>
                <li style="margin-top: 49px;">

                    <span>Admin</span>
                    <span>Help</span>
                    <span>账号管理</span>
                    <span>退出</span>
                </li>
            </ul>
        </div>


        <div class="user">
            某某有限公司
        </div>
    </div>
</div>
<div class=" bodyall">

    <div class="row rowheight ">
        <!-- 左边 -->
        <div class="leftbody">
            <span>车辆列表 ></span>
            <span>刷新  v</span>
            <div class="sousuocar">
                <div class="input-group">
                    <span class="input-group-addon">车牌号</span>
                    <input type="text" class="form-control" placeholder="请输入车牌号">
                </div>
            </div>
            <div class="bodyalltopnav">
                <button type="button" class="btn btn-default">全部120</button>
                <button type="button" class="btn btn-default">在线 70</button>
                <button type="button" class="btn btn-default">异常8</button>
            </div>
            <div class="datatable">
                <table class="table table-striped">

                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>城市</th>
                        <th>邮编</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Tanmay</td>
                        <td>Bangalore</td>
                        <td>560001</td>
                    </tr>
                    <tr>
                        <td>Sachin</td>
                        <td>Mumbai</td>
                        <td>400003</td>
                    </tr>
                    <tr>
                        <td>Uma</td>
                        <td>Pune</td>
                        <td>411027</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 右边 -->
        <div class="Rightbody">
            <!-- 上面-->
            <div class="Rightbody_top">
                <button type="button" class="btn btn-default">查看区域车辆 v</button>
                <button type="button" class="btn btn-default">默认视野 v</button>
                <button type="button" class="btn btn-default">刷新地图 v</button>
                <button type="button" class="btn btn-default">全屏 v</button>
                <button type="button" class="btn btn-default">工具 v</button>
                <button type="button" class="btn btn-default">打印地图 v</button>
                <button type="button" class="btn btn-default">位置服务 v</button>
                <button type="button" class="btn btn-default">预留</button>
            </div>
            <!-- 嵌套地图-->
            <div class="Rightbody_centen">
                <iframe src="/map" id="Rightbody_centenid" style="width:100%;height:103%;border:0px ; "></iframe>
            </div>
            <!-- 下面-->
            <div class="bottomRigth" id="bottomRigth">
                <div class="arrow">
                    <i class="glyphicon glyphicon-chevron-up imgclass" onclick="sousuo()"></i>
                </div>
                <div class="bottomhead">
                    <small>车机状态</small>
                    <i class="glyphicon glyphicon-th-large"></i>
                    <small>全部 120</small>
                    <small>在线70</small>
                    <small>异常80</small>
                </div>
                <div class="bottomb">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>名称</th>
                            <th>城市</th>
                            <th>邮编</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Tanmay</td>
                            <td>Bangalore</td>
                            <td>560001</td>
                        </tr>
                        <tr>
                            <td>Sachin</td>
                            <td>Mumbai</td>
                            <td>400003</td>
                        </tr>
                        <tr>
                            <td>Uma</td>
                            <td>Pune</td>
                            <td>411027</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


</div>
<script src="${ctx }/resources/shit/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx }/resources/shit/js/Main.js"></script>
<script>
    $(function () {
        $(".panel-heading").click(function (e) {
            /*切换折叠指示图标*/
            $(this).find("span").toggleClass("glyphicon-chevron-down");
            $(this).find("span").toggleClass("glyphicon-chevron-up");
        });
    });
</script>
</body>
</html>