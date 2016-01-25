package org.lihai.weixin.Quartz;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lihai.weixin.json.Access_token;
import org.lihai.weixin.json.ErrorEntity;
import org.lihai.weixin.json.JsonUtil;
import org.lihai.weixin.model.WeixinFinalValue;
import org.lihai.weixin.web.servlet.WeixinContext;
import org.springframework.stereotype.Component;

@Component
public class RefreshAccessTokenTask {
	public static String  at="mJCneVG5tw4aL8yUHXoT5yI5LVm9D5Y56-tqQiOnzrMV33Y1CB8DfeYeod8iio90-cU6YTOC69Qe-O-aLHkDNiNs4JhBYzUzrIQ0Kc1zFxwYZLaADAHIU";
	public void refreshToken() {
			HttpGet get = null;
			CloseableHttpResponse resp = null;
			CloseableHttpClient client = null;
			try {
				client = HttpClients.createDefault();
				String url = WeixinFinalValue.ACCESS_TOKEN_URL;
				url = url.replaceAll("APPID", WeixinFinalValue.AppID);
				url = url.replaceAll("APPSECRET", WeixinFinalValue.AppSecret);
				get = new HttpGet(url);
				resp = client.execute(get);
				int statusCode = resp.getStatusLine().getStatusCode();
				if(statusCode>=200&&statusCode<300) {
					HttpEntity entity = resp.getEntity();
					String content = EntityUtils.toString(entity);
					try {
						Access_token at = (Access_token)JsonUtil.getInstance().json2obj(content, Access_token.class);
						WeixinContext.setAccessToken(at.getAccess_token());
					} catch (Exception e) {
						ErrorEntity err = (ErrorEntity)JsonUtil.getInstance().json2obj(content, ErrorEntity.class);
						System.out.println("获取token异常:"+err.getErrcode()+","+err.getErrmsg());
						refreshToken();
					}
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(resp!=null) resp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if(client!=null) client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
}
