package mmp.gps.domain.mapLayer;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

/**
 * 地图图层
 */
public class MapLayer {
    private String id;
    private String companyId;
    private String userId;
    @NotBlank
    private String name;
    private boolean visible;
    private String remark;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "MapLayer{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", userId='" + userId + '\'' + ", name='" + name + '\'' + ", visible=" + visible + ", remark='" + remark + '\'' + ", editTime=" + editTime + '}';
    }

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
}
