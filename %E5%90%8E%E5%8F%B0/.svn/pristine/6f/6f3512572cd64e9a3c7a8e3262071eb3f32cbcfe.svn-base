<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <title>地图图层管理</title>
    <style type="text/css">
        #allmap {
            width: 100%;
            height: 400px;
            overflow: hidden;
        }
    </style>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/mapLayer/mapLayer.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.delete", request);
    request.setAttribute("delete_auth", delete_auth);

    boolean addCircleAreas_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.addCircleAreas", request);
    request.setAttribute("addCircleAreas_auth", addCircleAreas_auth);

    boolean removeCircleArea_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.removeCircleArea", request);
    request.setAttribute("removeCircleArea_auth", removeCircleArea_auth);

    boolean addRectangleAreas_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.addRectangleAreas", request);
    request.setAttribute("addRectangleAreas_auth", addRectangleAreas_auth);

    boolean removeRectangleArea_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.removeRectangleArea", request);
    request.setAttribute("removeRectangleArea_auth", removeRectangleArea_auth);

    boolean addPolygonAreas_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.addPolygonAreas", request);
    request.setAttribute("addPolygonAreas_auth", addPolygonAreas_auth);

    boolean removePolygonArea_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.removePolygonArea", request);
    request.setAttribute("removePolygonArea_auth", removePolygonArea_auth);

    boolean addRouteAreas_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.addRouteAreas", request);
    request.setAttribute("addRouteAreas_auth", addRouteAreas_auth);

    boolean removeRouteArea_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.removeRouteArea", request);
    request.setAttribute("removeRouteArea_auth", removeRouteArea_auth);

    boolean addPois_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.addPois", request);
    request.setAttribute("addPois_auth", addPois_auth);

    boolean removePoi_auth = AuthorizeCache.hasAuthorized("baseinfo.mapLayer.removePoi", request);
    request.setAttribute("removePoi_auth", removePoi_auth);
%>
<input id="addCircleAreas_auth" type="hidden"
       value="${addCircleAreas_auth}">
<input id="removeCircleArea_auth" type="hidden"
       value="${removeCircleArea_auth}">
<input id="addRectangleAreas_auth" type="hidden"
       value="${addRectangleAreas_auth}">
<input id="removeRectangleArea_auth" type="hidden"
       value="${removeRectangleArea_auth}">
<input id="addPolygonAreas_auth" type="hidden"
       value="${addPolygonAreas_auth}">
<input id="removePolygonArea_auth" type="hidden"
       value="${removePolygonArea_auth}">
<input id="addRouteAreas_auth" type="hidden"
       value="${addRouteAreas_auth}">
<input id="removeRouteArea_auth" type="hidden"
       value="${removeRouteArea_auth}">
<input id="addPois_auth" type="hidden" value="${addPois_auth}">
<input id="removePoi_auth" type="hidden" value="${removePoi_auth}">
<div id="mapLayerFrame">
    <div id="divMapLayerAllMap"
         style="overflow: hidden; zoom: 1; position: relative;">
        <div id="mapLayerMap"
             style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
    </div>
    <div style="height: 300px;">
        <div class="mon-toolbar metro-gray">
            <div class="title mon-3x">地图图层列表</div>
            <div class="prompt width-3x">
                名称：<input id="txtMapLayerFilter" type="text" placeholder="图层名称"/>
            </div>
            <div id="btnQueryMapLayer" class="mon-button">
                <div class="icon i-16-search"></div>
                <span>查询</span>
            </div>
            <c:if test="${create_auth || update_auth || delete_auth}">
                <div class="spliter"></div>
            </c:if>
            <c:if test="${create_auth}">
                <div id="btnCreateMapLayer" class="mon-button">
                    <div class="icon i-16-add"></div>
                    <span>添加</span>
                </div>
            </c:if>
            <c:if test="${update_auth}">
                <div id="btnEditMapLayer" class="mon-button">
                    <div class="icon i-16-edit"></div>
                    <span>编辑</span>
                </div>
            </c:if>
            <c:if test="${delete_auth}">
                <div id="btnRemoveMapLayer" class="mon-button">
                    <div class="icon i-16-remove"></div>
                    <span>删除</span>
                </div>
            </c:if>
        </div>
        <div class="mon-clear"></div>
        <div id="gridMapLayer"></div>
    </div>
    <div id="dialogMapLayerSelectCircleArea" class="display-none">
        <div class="mon-toolbar metro-gray">
            <div class="title mon-3x">圆形区域列表</div>
            <div class="prompt width-3x">
                名称：<input id="txtCircleAreaFilter" type="text" placeholder="圆形区域名称"/>
            </div>
            <div id="btnQueryCircleArea" class="mon-button">
                <div class="icon i-16-search"></div>
                <span>查询</span>
            </div>

            <div class="spliter"></div>
            <div id="btnSelectCircleArea" class="mon-button">
                <div class="icon i-16-checked"></div>
                <span>选择</span>
            </div>
        </div>
        <div class="mon-clear"></div>
        <div id="gridCircleAreaInMapLayer"></div>
    </div>
</div>
<div class="mon-clear"></div>
<div id="dialogMapLayerSelectRectangleArea" class="display-none">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">矩形区域列表</div>
        <div class="prompt width-3x">
            名称：<input id="txtRectangleAreaFilter" type="text"
                      placeholder="矩形区域名称"/>
        </div>
        <div id="btnQueryRectangleArea" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>

        <div class="spliter"></div>
        <div id="btnSelectRectangleArea" class="mon-button">
            <div class="icon i-16-checked"></div>
            <span>选择</span>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="gridRectangleAreaInMapLayer"></div>
</div>
<div class="mon-clear"></div>
<div id="dialogMapLayerSelectPolygonArea" class="display-none">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">多边形区域列表</div>
        <div class="prompt width-3x">
            名称：<input id="txtPolygonAreaFilter" type="text"
                      placeholder="多边形区域名称"/>
        </div>
        <div id="btnQueryPolygonArea" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>

        <div class="spliter"></div>
        <div id="btnSelectPolygonArea" class="mon-button">
            <div class="icon i-16-checked"></div>
            <span>选择</span>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="gridPolygonAreaInMapLayer"></div>
</div>
<div class="mon-clear"></div>
<div id="dialogMapLayerSelectRouteArea" class="display-none">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">路线区域列表</div>
        <div class="prompt width-3x">
            名称：<input id="txtRouteAreaFilter" type="text" placeholder="路线区域名称"/>
        </div>
        <div id="btnQueryRouteArea" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>

        <div class="spliter"></div>
        <div id="btnSelectRouteArea" class="mon-button">
            <div class="icon i-16-checked"></div>
            <span>选择</span>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="gridRouteAreaInMapLayer"></div>
</div>
<div class="mon-clear"></div>
<div id="dialogMapLayerSelectPoi" class="display-none">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">兴趣点列表</div>
        <div class="prompt width-3x">
            名称：<input id="txtPoiFilter" type="text" placeholder="兴趣点名称"/>
        </div>
        <div id="btnQueryPoi" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>

        <div class="spliter"></div>
        <div id="btnSelectPoi" class="mon-button">
            <div class="icon i-16-checked"></div>
            <span>选择</span>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="gridPoiInMapLayer"></div>
</div>
</body>
</html>