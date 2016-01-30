package org.lihai.weixin.user.model;

/**ClassName:Wgroup
 * Function: 微信中用户组的管理对象
 * @author   lh
 * @Date	 2016	2016年1月25日		下午10:57:01
 */
public class Wgroup {
	/**
	 *用户组ID 
	 */
	private int id;
	/**
	 *用户组名称 
	 */
	private String name;
	/**
	 *组中的用户数量 
	 */
	private int count;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Wgroup() {
		super();
	}

	public Wgroup(int id, String name, int count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}

	

}
