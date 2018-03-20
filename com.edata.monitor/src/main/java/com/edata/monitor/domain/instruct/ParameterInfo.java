package com.edata.monitor.domain.instruct;

/**
 * 信令参数信息
 * 
 * @author 生
 *
 */
public class ParameterInfo {
	private String id;

	/**
	 * 获取ID号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置ID号
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String pid;

	/**
	 * 获取父ID号
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 设置父ID号
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	private String featureId;

	/**
	 * 获取功能ID号
	 */
	public String getFeatureId() {
		return featureId;
	}

	/**
	 * 设置功能ID号
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	private int index;

	/**
	 * 获取序号
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置序号
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	private String name;

	/**
	 * 获取名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String label;

	/**
	 * 获取标签
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 设置标签
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	private int type;

	/**
	 * 获取数据类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置数据类型
	 */
	public void setType(int type) {
		this.type = type;
	}

	private String selectValue;

	/**
	 * 获取单选值
	 */
	public String getSelectValue() {
		return selectValue;
	}

	/**
	 * 设置单选值
	 */
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	private String dictionaryKey;

	/**
	 * 获取字典键
	 */
	public String getDictionaryKey() {
		return dictionaryKey;
	}

	/**
	 * 设置字典键
	 */
	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}

	private int switchBit;

	/**
	 * 获取开关位
	 */
	public int getSwitchBit() {
		return switchBit;
	}

	/**
	 * 设置开关位
	 */
	public void setSwitchBit(int switchBit) {
		this.switchBit = switchBit;
	}

	private int rows;

	/**
	 * 获取行数
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 设置行数
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	private int columns;

	/**
	 * 获取列数
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * 设置列数
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	private String defaultValue;

	/**
	 * 获取默认值
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 设置默认值
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	private String description;

	/**
	 * 获取描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
