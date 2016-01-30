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

/**ClassName:HydropowercoaluUtils
 * Function: 水电煤接口的使用
 * @author   lh
 * @Date	 2016	2016年1月30日		下午1:33:18
 */
public class HydropowercoaluUtils {

	/**
	 * function(查询所有的的缴费省份)
	 * 
	 * @param @throws IOException
	 * @param @throws ClientProtocolException
	 * @return void
	 * @throws
	 * @author lh
	 * @Date 2016年1月30日
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getAllProvince() throws IOException,
			ClientProtocolException {
		CloseableHttpClient createDefault = HttpClients.createDefault();
		String url = "http://p.apix.cn/apixlife/pay/utility/query_province?apix-key=49294feda0424dc26286ff90eca27c50";
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = createDefault.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			System.out.println(str);
			Map<String, Map<String, List<Map>>> maps = (Map<String, Map<String, List<Map>>>) JsonUtil
					.getInstance().json2obj(str, Map.class);
			System.out.println(maps);
			Map<String, List<Map>> map = maps.get("Data");
			System.out.println(map);
			List<Map> list = map.get("Province");
			List<Province> listprovince = new ArrayList<Province>();
			for (Map province : list) {
				Province pro = new Province();
				pro.setProvinceId((String) province.get("ProvinceId"));
				pro.setProvinceName((String) province.get("ProvinceName"));
				listprovince.add(pro);
			}
			for (Province province : listprovince) {
				System.out.println("省ID:" + province.getProvinceId() + "省名称:"
						+ province.getProvinceName() + "");
			}
		}
	}
	/**function(查询所有的缴费的城市)
	 * @param  @throws IOException
	 * @param  @throws ClientProtocolException         
	 * @return void 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月30日
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static void getAllCity() throws IOException,
	ClientProtocolException {
		CloseableHttpClient createDefault = HttpClients.createDefault();
		String url="http://p.apix.cn/apixlife/pay/utility/query_city?provid=PROVID&apix-key=APIX-KEY";
		url=url.replaceAll("PROVID", "v1953");//北京
		url=url.replaceAll("APIX-KEY", "49294feda0424dc26286ff90eca27c50");//北京
		HttpGet  get=new HttpGet(url);
		CloseableHttpResponse response = createDefault.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			System.out.println(str);
			Map<String, Map<String, List<Map>>> maps = (Map<String, Map<String, List<Map>>>) JsonUtil
					.getInstance().json2obj(str, Map.class);
			System.out.println(maps);
			Map<String, List<Map>> map = maps.get("Data");
			System.out.println(map);
			List<Map> list = map.get("City");
			List<City> listcity = new ArrayList<City>();
			for (Map city : list) {
				City ci = new City();
				ci.setCityId((String) city.get("CityId"));
				ci.setCityName((String) city.get("CityName"));
				listcity.add(ci);
			}
			for (City city : listcity) {
				System.out.println("市:" + city.getCityId() + "     市名称:"+ city.getCityName() + "");
			}
		}
	}
}
