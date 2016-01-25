package org.lihai.weixin.kit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lihai.weixin.Quartz.RefreshAccessTokenTask;
import org.lihai.weixin.json.JsonUtil;
import org.lihai.weixin.json.WeixinMedia;
import org.lihai.weixin.model.WeixinFinalValue;

public class MediaKit {

	/**function(使用post请求增加微信临时素材)
	 * @param  @return         
	 * @return String 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	public static  String postMedia(String  path,String  type){
		CloseableHttpClient createDefault =null;
		CloseableHttpResponse response=null;
		try {
			createDefault=HttpClients.createDefault();
			String url=WeixinFinalValue.POST_MEDIA;
			url=url.replace("ACCESS_TOKEN", RefreshAccessTokenTask.at);
			url=url.replaceAll("TYPE", type);
			HttpPost  post=new HttpPost(url);
			FileBody fb=new FileBody(new File(path));
			HttpEntity entity=MultipartEntityBuilder.create().addPart("media", fb).build();
			post.setEntity(entity);
			response=createDefault.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode>=200&&statusCode<300){
				String string = EntityUtils.toString(response.getEntity());
				WeixinMedia wm = (WeixinMedia)JsonUtil.getInstance().json2obj(string, WeixinMedia.class);
				return wm.getMedia_id();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(createDefault!=null){
					createDefault.close();
				}
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**function(使用post请求增加微信临时素材)
	 * @param  @return         
	 * @return String 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	public static  void getMedia(String  MediaId,File  f){
		CloseableHttpClient createDefault =null;
		CloseableHttpResponse response=null;
		try {
			createDefault=HttpClients.createDefault();
			String url=WeixinFinalValue.GET_MEDIA;
			url=url.replace("ACCESS_TOKEN", RefreshAccessTokenTask.at);
			url=url.replaceAll("MEDIA_ID", MediaId);
			HttpGet  post=new HttpGet(url);
			response=createDefault.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode>=200&&statusCode<300){
				
				InputStream ins = response.getEntity().getContent();
				FileUtils.copyInputStreamToFile(ins, f);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(createDefault!=null){
					createDefault.close();
				}
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
