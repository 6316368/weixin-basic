package org.lihai.weixin.model;

import java.util.List;

public class WeixinMenu {
	/**
	 *菜单的ID 
	 */
	private int id;
	/**
	 *菜单的名称 
	 */
	private  String name;
	/**
	 *菜单的类型
	 */
	private String type;
	/**
	 *菜单的url地址
	 */
	private String url;
	/**
	 *菜单的事件
	 */
	private String key;
	/**
	 *父类菜单的ID
	 */
	private int  pid;
	/**
	 *一组菜单的列表
	 */
	private  List<WeixinMenu> sub_button;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<WeixinMenu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WeixinMenu> sub_button) {
		this.sub_button = sub_button;
	}
	
}
