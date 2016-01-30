package com.lihai.test;

import java.awt.Menu;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.lihai.weixin.Quartz.RefreshAccessTokenTask;
import org.lihai.weixin.json.Access_token;
import org.lihai.weixin.json.JsonUtil;
import org.lihai.weixin.json.ModleMsgData;
import org.lihai.weixin.json.TempleMsg;
import org.lihai.weixin.kit.MediaKit;
import org.lihai.weixin.model.WeixinFinalValue;
import org.lihai.weixin.model.WeixinMenu;
import org.lihai.weixin.msg.MessageKit;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class HttpRequestUnits {

	/**function(测试发送httpget请求)
	 * @param           
	 * @return void 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月24日
	*/
	@Test
	public void testhttpget(){
 try {
		CloseableHttpClient createDefault = HttpClients.createDefault();
		String url=WeixinFinalValue.ACCESS_TOKEN_URL;
		url=url.replaceAll("APPID", WeixinFinalValue.AppID);
		url=url.replaceAll("APPSECRET", WeixinFinalValue.AppSecret);
		System.out.println(url);
		HttpGet  get=new HttpGet(url.trim());
		CloseableHttpResponse response = createDefault.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode>=200&&statusCode<300){
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			Access_token json2obj = (Access_token) JsonUtil.getInstance().json2obj(string, Access_token.class);
			System.out.println(json2obj.getAccess_token());
		}
		System.out.println(statusCode);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public  void testMenu() throws ClientProtocolException, IOException{
		List<WeixinMenu>  wms=new ArrayList<WeixinMenu>();
		
		WeixinMenu  wm1=new WeixinMenu();
		wm1.setId(1);
		wm1.setName("学习网站");
		wm1.setType("view");
	    wm1.setUrl("http://www.konghao.org");
	    
	    wms.add(wm1);
	    
	    List<WeixinMenu>  wms2sub=new ArrayList<WeixinMenu>();
	    WeixinMenu  wm2=new WeixinMenu();
		wm2.setName("测试资源");
		wm2.setSub_button(wms2sub);
		
		WeixinMenu wm3=new WeixinMenu();
		wm3.setId(2);
		wm3.setName("事件测试");
		wm3.setType("clieck");
		wm3.setKey("A00001");
	    wm3.setUrl("http://www.konghao.org");
	    wms2sub.add(wm3);
	    
	    WeixinMenu wm4=new WeixinMenu();
		wm4.setId(2);
		wm4.setName("烧苗测试");
		wm4.setType("scancode_waitmsg");
		wm4.setKey("A00002");
	    wm4.setUrl("http://www.konghao.org");
	    wms2sub.add(wm4);
	    wm2.setSub_button(wms2sub);
	    wms.add(wm4);
	    
	    
	    Map<String, List<WeixinMenu>> maps=new HashMap<String, List<WeixinMenu>>();
	    maps.put("button", wms);
	    String json = JsonUtil.getInstance().obj2json(maps);
		System.out.println(json);
		CloseableHttpClient createDefault = HttpClients.createDefault();
		String url=WeixinFinalValue.MENU_ADD;
		url=url.replaceAll("ACCESS_TOKEN", RefreshAccessTokenTask.at);
	    HttpPost  post=new HttpPost(url);
	    post.addHeader("Content-Type","application/json");
	    StringEntity entity=new StringEntity(json,ContentType.create("application/json","utf-8"));
	    post.setEntity(entity);
	    CloseableHttpResponse response = createDefault.execute(post);
	    int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode>=200&&statusCode<300){
			System.out.println(EntityUtils.toString(response.getEntity()));
		}
		
	}
	
	
	@Test
	public  void  testPostMedia(){
		String mid = MediaKit.postMedia("H:\\pictures\\桌面.jpg", "image");
	    System.out.println(mid);
	}
	
	@Test
	public  void  testGetMedia() throws IOException{
		MediaKit.getMedia("NgALyWw5jt1ZOb6VK7Vb0urofExsPEcM5TN8m95ancfYMg49h-R2DAmuQvko1gji",new File("d://sss.jpg"));
	  
	}
	
	@Test
	public  void  testTempleMsg() throws IOException{
	  TempleMsg  msg=new TempleMsg();
	  msg.setTouser("oKAtBuN6KGfNIdxdB6y3rqHtdCSY");
	  msg.setTemplate_id("1JGofSp4NzVesA2cVuucxnbQoHGK2_e0KIh2tMZh7OE");
	  msg.setTopcolor("#ff0000");
	  msg.setUrl("http://www.konghao.org");
	  Map<String, Object> map=new HashMap<String, Object>();
	  map.put("num", new ModleMsgData("123", "#00ff00"));
	  msg.setData(map);
	  //System.out.println(JsonUtil.getInstance().obj2json(msg));
	  MessageKit.postTemlateMsg(msg);
	}
}
