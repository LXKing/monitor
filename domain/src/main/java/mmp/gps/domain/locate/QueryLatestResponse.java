package mmp.gps.domain.locate;

import java.util.Map;

/**
 * 查询最后位置数据响应
 */
public class QueryLatestResponse {
    private Map<String, LatestInfo> latests;

    /**
     * 获取最后位置列表
     */
    public Map<String, LatestInfo> getLatests() {
        return latests;
    }

    /**
     * 设置最后位置列表
     */
    public void setLatests(Map<String, LatestInfo> latests) {
        this.latests = latests;
    }

    @Override
    public String toString() {
        return "QueryLatestResponse{" +
                "latests=" + latests +
                '}';
    }
}
