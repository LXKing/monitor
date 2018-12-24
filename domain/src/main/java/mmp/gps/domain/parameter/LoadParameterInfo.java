package mmp.gps.domain.parameter;

public class LoadParameterInfo {
    private String id;
    private String pid;
    private String featureId;
    private int index;
    private String name;
    private String label;
    private int type;
    private String selectValue;
    private String dictionaryKey;
    private int switchBit;
    private int rows;
    private int columns;
    private String defaultValue;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public String getDictionaryKey() {
        return dictionaryKey;
    }

    public void setDictionaryKey(String dictionaryKey) {
        this.dictionaryKey = dictionaryKey;
    }

    public int getSwitchBit() {
        return switchBit;
    }

    public void setSwitchBit(int switchBit) {
        this.switchBit = switchBit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
