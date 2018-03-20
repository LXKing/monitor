package com.edata.monitor.domain.instruct;

/**
 * 设备功能信息
 * 
 * @author 生
 *
 */
public class FeatureInfo {
	private String id;

	/**
	 * 获取记录ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置记录ID
	 */
	public void setId(String id) {
		this.id = id;
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

	private String command;

	/**
	 * 获取命令
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 设置命令
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	private int params;

	/**
	 * 获取参数个数
	 */
	public int getParams() {
		return params;
	}

	/**
	 * 设置参数个数
	 */
	public void setParams(int params) {
		this.params = params;
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
