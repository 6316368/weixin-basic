package com.lihai.hydropowercoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lihai.weixin.json.JsonUtil;

/**ClassName:TestJson
 * Function: 水电煤缴费的应用
 *           网站:http://apix.cn/services/show/114
 * @author   lh
 * @Date	 2016	2016年1月30日		下午1:08:14
 */
public class TestJson {
	// "[{id=0, name=未分组, count=1}, {id=1, name=黑名单, count=0}, {id=2, name=星标组, count=0}, {id=100, name=测试组, count=0}]";
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		//查询所有支持缴费的省份
		//HydropowercoaluUtils.getAllProvince();
	    //查询所有的缴费的城市
		//HydropowercoaluUtils.getAllCity();
		
	}

	
	
	
	
}
