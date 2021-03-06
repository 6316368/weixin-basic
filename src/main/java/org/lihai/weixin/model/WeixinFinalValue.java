package org.lihai.weixin.model;

/**ClassName:WeixinFinalValue
 * Function: 定义微信开发中需要使用的所有的常量
 * @author   lh
 * @Date	 2016	2016年1月24日		下午4:54:50
 */
public class WeixinFinalValue {
	/***
	 * 微信公众号开发中使用的凭证ID
	 */
	public final static  String  AppID="wxceff12edbe4c26bb";
	/***
	 * 微信公众号开发中使用密钥
	 */
	public final static  String  AppSecret="3e60f31e9912ec63429e1df5212acccf";
	/***
	 * 获取ACCESS_TOKEN的url地址
	 */
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/***
	 * 添加菜单的url地址
	 */
	public final static String MENU_ADD = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/***
	 * 查询菜单的url地址
	 */
	public final static String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	/***
	 * 发送消息的类型:文本消息
	 */
	public final static String MSG_TEXT_TYPE = "text";
	/***
	 * 发送消息的类型:图片消息
	 */
	public final static String MSG_IMAGE_TYPE = "image";
	/***
	 * 发送消息的类型:语音消息
	 */
	public final static String MSG_VOICE_TYPE = "voice";
	/***
	 * 发送消息的类型:视频消息
	 */
	public final static String MSG_VIDEO_TYPE = "video";
	/***
	 * 发送消息的类型:短视频消息
	 */
	public final static String MSG_SHORTVIDEO_TYPE = "shortvideo";
	/***
	 * 微信中的地理位置
	 */
	public final static String MSG_LOCATION_TYPE = "location";
	/***
	 * 微信中的事件
	 */
	public final static String MSG_EVENT_TYPE = "event";
	/***
	 * 新增临时素材的url使用post请求
	 */
	public final static String POST_MEDIA="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	/***
	 * 的一个media的地址
	 */
	public final static String GET_MEDIA="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	/***
	 * 模板消息的url
	 */
	public final static String SEND_TEMPLATE_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/***
	 * 根据用户ID查询某个用户的相关信息
	 */
	public final static String USER_QUERY = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public final static String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public final static String AUTH_GET_OID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/***
	 * 添加用户组的url地址
	 */
	public final static String ADD_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	/***
	 * 查询所有用户组的url地址
	 */
	public final static String QUERY_ALL_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	/***
	 * 查询某个用户所在的分组
	 */
	public final static String QUERY_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	/***
	 * 根据用户组ID更新改用户组的信息
	 */
	public final static String UPDATE_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	/***
	 * 移动某个用户到指定的分组
	 */
	public final static String MOVE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	/***
	 * 移动一组用户到指定的分组
	 */
	public final static String MOVE_USERS_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
	/***
	 * 根据用户组ID删除该用户组
	 */
	public final static String DELETE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
	
	public final static String QR_GET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";

	public final static String KF_ADD = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
}
