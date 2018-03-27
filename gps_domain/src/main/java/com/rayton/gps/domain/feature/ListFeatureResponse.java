package com.rayton.gps.domain.feature;

import java.util.ArrayList;
import java.util.List;

public class ListFeatureResponse {
    private int rows;
    private List<FeatureInfo> features = new ArrayList<FeatureInfo>();

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<FeatureInfo> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureInfo> features) {
        this.features = features;
    }
}
