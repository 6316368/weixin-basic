package org.lihai.weixin.json;

import java.util.Map;

/**
 * ClassName:TempleMsg Function: 模板消息的使用
 * @author lh
 * @Date 2016 2016年1月25日 下午9:53:12
 */
public class TempleMsg {
    /**
     *发给某个用户 
     */
	private String touser;
	 /**
     *模板的ID
     */
	private String template_id;
	/**
	 *模板消息中点击详情后的url 
	 */
	private String url;
	/**
	 *模板消息顶部的颜色
	 */
	private String topcolor;
	/**
	 *模板消息中主体数据
	 */
	private  Map<String, Object> data;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
