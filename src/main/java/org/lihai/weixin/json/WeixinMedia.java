package org.lihai.weixin.json;

/**ClassName:Media
 * Function: 定义一组media的信息
 * @author   lh
 * @Date	 2016	2016年1月25日		下午4:34:46
 */
public class WeixinMedia {
	/**
	 *媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和
	 *缩略图（thumb，主要用于视频与音乐格式的缩略图） 
	 */
	private String type;
	/**
	 *媒体文件上传后，获取时的唯一标识
	 */
	private String media_id;
	/**
	 *媒体文件上传时间戳
	 */
	private String created_at;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	

}
