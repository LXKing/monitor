package mmp.gps.domain.mapLayer;

import java.sql.Timestamp;
import java.util.List;

public class MapLayerDto {
    public String id;

    public String companyId;

    public String userId;

    public String name;

    public boolean visible;

    public String remark;

    public Timestamp editTime;

    public List<Long> circleAreas;

    public List<Long> rectangleAreas;

    public List<Long> polygonAreas;

    public List<Long> routeAreas;

    public List<Long> pois;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public List<Long> getCircleAreas() {
        return circleAreas;
    }

    public void setCircleAreas(List<Long> circleAreas) {
        this.circleAreas = circleAreas;
    }

    public List<Long> getRectangleAreas() {
        return rectangleAreas;
    }

    public void setRectangleAreas(List<Long> rectangleAreas) {
        this.rectangleAreas = rectangleAreas;
    }

    public List<Long> getPolygonAreas() {
        return polygonAreas;
    }

    public void setPolygonAreas(List<Long> polygonAreas) {
        this.polygonAreas = polygonAreas;
    }

    public List<Long> getRouteAreas() {
        return routeAreas;
    }

    public void setRouteAreas(List<Long> routeAreas) {
        this.routeAreas = routeAreas;
    }

    public List<Long> getPois() {
        return pois;
    }

    public void setPois(List<Long> pois) {
        this.pois = pois;
    }

    @Override
    public String toString() {
        return "MapLayerDto{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", userId='" + userId + '\'' + ", name='" + name + '\'' + ", visible=" + visible + ", remark='" + remark + '\'' + ", " + "editTime=" + editTime + ", circleAreas=" + circleAreas + ", rectangleAreas=" + rectangleAreas + ", " + "polygonAreas=" + polygonAreas + ", routeAreas=" + routeAreas + ", pois=" + pois + '}';
    }
}
