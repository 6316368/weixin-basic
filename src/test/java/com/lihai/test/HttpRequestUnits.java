package com.lihai.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

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
		CloseableHttpClient createDefault = HttpClients.createDefault();
		HttpGet  get=new HttpGet("http://www.konghao.org");
		try {
		CloseableHttpResponse response = createDefault.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode>=200&&statusCode<300){
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			System.out.println(string);
		}
		System.out.println(statusCode);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
