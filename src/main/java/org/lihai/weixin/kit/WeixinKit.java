package org.lihai.weixin.kit;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lihai.weixin.json.Access_token;
import org.lihai.weixin.json.JsonUtil;
import org.lihai.weixin.model.WeixinFinalValue;

public class WeixinKit {
	public static String postString(String url, String data, String type) {
		CloseableHttpClient createDefault = null;
		CloseableHttpResponse response = null;
		try {
			createDefault = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			post.addHeader("ContentType", type);
			StringEntity entity = new StringEntity(data, ContentType.create(
					type, "UTF-8"));
			post.setEntity(entity);
			response = createDefault.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				String str = EntityUtils.toString(response.getEntity());
				System.out.println(str);
				return str;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (createDefault != null) {
					createDefault.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getString(String url) {
		CloseableHttpClient createDefault = null;
		CloseableHttpResponse response = null;
		try {
			createDefault = HttpClients.createDefault();
			HttpGet get = new HttpGet(url.trim());
			response = createDefault.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity,"UTF-8");
				System.out.println(str);
				return str;
			}
			System.out.println(statusCode);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (createDefault != null) {
					createDefault.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
