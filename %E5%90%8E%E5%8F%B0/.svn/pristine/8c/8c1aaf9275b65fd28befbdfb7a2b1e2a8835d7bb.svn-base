<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/sockjs.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/gpsparser.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/instruct.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/datalog.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/locate.js"></script>
    <link href="${ctx}/resources/new/css/zTreeStyle.css" rel="stylesheet" />
    <link href="${ctx}/resources/new/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${ctx}/resources/new/css/Maincss.css" rel="stylesheet" />

</head>
<body>
<div class=" bodyall">
    <div class="  rowheight ">
        <!-- 左边 -->
        <div class="leftbody" id="leftbody">
            <!-- 头部 -->
            <div class="LefttopLeft">
                <div class="headleftbody">
                    <span class="car">车辆列表</span>
                    <img src="${ctx}/resources/new/image/directory.png"/>
                    <img src="${ctx}/resources/new/image/Double_right.png"/>
                    <div style="float: right;padding-right: 10px;">
                        <span class="shuaxin">刷新 </span>  <img src="${ctx}/resources/new/image/refresh.png"/>
                    </div>
                </div>
                <div class="search">
                    <img src="${ctx}/resources/new/image/search.png"/>
                    <input type="text" class="form-control" placeholder="车牌号v设备号">
                </div>
            </div>
            <ul id="myTab" class="nav nav-tabs navlefthead" >
                <li class="active">
                    <a href="#All" data-toggle="tab">
                        全部120
                    </a>
                </li>

                <li><a href="#online" data-toggle="tab">在线70</a></li>
                <li><a href="#offline" data-toggle="tab">离线80</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="All">
                    <div class="layer">
                        <div id="tableMain">
                            <ul id="dataTree" class="ztree">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="online">
                    <p>在线
                </div>
                <div class="tab-pane fade" id="offline">
                    <p>在离线
                </div>

            </div>
            <!-- <div class="layer">
            <div id="tableMain">
                <ul id="dataTree" class="ztree">

                </ul>
            </div>
            </div> -->
            <div class="left_bottom">
                <ul>
                    <li>
                        <img src="${ctx}/resources/new/image/zaixian.png"/>
                        <span> 在线</span>
                    </li>
                    <li>
                        <img src="${ctx}/resources/new/image/lansedian.png"/>
                        <span> 行驶</span>

                    </li>
                    <li>
                        <img src="${ctx}/resources/new/image/budingwei.png"/>
                        <span> 不定位 </span>
                    </li>
                    <li>
                        <img src="${ctx}/resources/new/image/lixiandian.png"/>
                        <span>     离线   </span>
                    </li>
                    <li>
                        <img src="${ctx}/resources/new/image/baojingdian.png"/>
                        <span>报警   </span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="sousuotubiao" id="sousuotubiao" onclick="soushuoleft()">
            <i class="glyphicon glyphicon-chevron-left" id="imgeleft"></i>
        </div>
        <!-- 右边 -->
        <div class="Rightbody">
            <!-- 上面-->
            <div class="Rightbody_top">
                <div class="btn-group btn-group-sm ">
                    <button type="button" >查看区域车辆 <img src="${ctx}/resources/new/image/smallcar.png"/></button>
                    <button type="button" >默认视野 <img src="${ctx}/resources/new/image/eyes.png"/></button>
                    <button type="button" >刷新地图 <img src="${ctx}/resources/new/image/refresh.png"/></button>
                    <button type="button" >全屏 <img src="${ctx}/resources/new/image/foursurface.png"/></button>
                    <button type="button" >工具 <img src="${ctx}/resources/new/image/pliers.png"/></button>
                    <button type="button" >设置 <img src="${ctx}/resources/new/image/srtting.png"/></button>
                    <button type="button" >打印地图 <img src="${ctx}/resources/new/image/ditudayin.png"/></button>
                    <button type="button" >位置服务 <img src="${ctx}/resources/new/image/weizhifuwu.png"/></button>
                    <button type="button" >预留</button>
                </div>
            </div>
            <!-- 嵌套地图-->
            <div class="Rightbody_centen" id="Rightbody_centen">
                <iframe src="map.jsp" id="Rightbody_centenid" style="width:100%;height:100%;border:0px ; "></iframe>
            </div>
            <!-- 下面-->
            <div class="bottomRigth" id="bottomRigth">
                <div class="arrow">
                    <i class="glyphicon glyphicon-chevron-down imgclass" onclick="sousuo()" id="imgclass"></i>
                </div>
                <div class="bottomhead">
                    <small>车机状态</small>
                    <img src="${ctx}/resources/new/image/srtting.png" />
                    <img src="${ctx}/resources/new/image/V.png"/>
                    <small>全部 120</small>
                    <small>在线70</small>
                    <small>异常80</small>
                </div>
                <div class="bottomb">
                    <table id="finishingTask" data-toggle="table"  data-pagination="true" data-side-pagination="client" data-page-number="1" data-page-size="3"
                           data-striped =true;
                    >
                        <thead >
                        <tr>
                            <th  data-field="id" style="display: none" class="noneid">id</th>
                            <th data-field="name"> 名称</th>
                            <th data-field="city">城市</th>
                            <th data-field="youbian">邮编</th>
                        </tr>
                        </thead>

                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${ctx}/resources/new/js/bootstrap/js/bootstrap-table.min.js"></script>
<script src="${ctx}/resources/new/js/jquery/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/resources/new/js/jquery/jquery.ztree.exedit.js"></script>
<script src="${ctx}/resources/new/js/jquery/jquery.ztree.excheck-3.5.js"></script>
<script src="${ctx}/resources/new/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/new/js/Main.js"></script>

<script>

</script>
</body>
</html>