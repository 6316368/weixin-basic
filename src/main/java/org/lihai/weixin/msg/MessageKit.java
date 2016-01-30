package org.lihai.weixin.msg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.lihai.weixin.Quartz.RefreshAccessTokenTask;
import org.lihai.weixin.json.JsonUtil;
import org.lihai.weixin.json.TempleMsg;
import org.lihai.weixin.json.WeixinMedia;
import org.lihai.weixin.kit.MediaKit;
import org.lihai.weixin.kit.WeixinKit;
import org.lihai.weixin.model.WeixinFinalValue;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
@SuppressWarnings("unchecked")
public class MessageKit {
	
	private static Map<String, String> replyMsg=new HashMap<String, String>();
	static{
		replyMsg.put("123", "你输入了123");
		replyMsg.put("hello", "world");
		replyMsg.put("run", "祝你一路平安");
	}

	/**function(使用DOM4J把一个xml转换成一个map)
	 * @param  @param req
	 * @param  @return
	 * @param  @throws IOException
	 * @param  @throws DocumentException         
	 * @return Map<String,String> 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	public static Map<String,String> regMsg2Map(HttpServletRequest req) throws IOException, DocumentException{
		req.setCharacterEncoding("utf-8");
		String xml = reg2Xml(req);
		Map<String,String> maps=new HashMap<String, String>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		for (Element e : elements) {
			maps.put(e.getName(), e.getTextTrim());
		}
		return maps;
	}
    /**function(读取流中的数据并返回一个String类型的数据)
     * @param  @param req
     * @param  @return
     * @param  @throws IOException         
     * @return String 
     * @throws                
     * @author lh 
     * @Date   2016年1月25日
    */
    private  static String reg2Xml(HttpServletRequest req) throws IOException{
    	req.setCharacterEncoding("utf-8");
		BufferedReader  bf=null;
		bf=new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
		String str=null;
		StringBuffer  sb=new StringBuffer();
		while((str=bf.readLine())!=null){
			sb.append(str);
		}
		return  sb.toString();
	}
	public static String handlerMsg(Map<String, String> regMsg2Map) throws IOException {
		String  msgType=regMsg2Map.get("MsgType");
		//事件类型
		if(msgType.equals(WeixinFinalValue.MSG_EVENT_TYPE)){
			
		}
		//文本消息类型
		else  if(msgType.equals(WeixinFinalValue.MSG_TEXT_TYPE)){
			return textTypeHandler(regMsg2Map);
		}
		//图片消息
		else  if(msgType.equals(WeixinFinalValue.MSG_IMAGE_TYPE)){
			return imageTypeHandler(regMsg2Map,MediaKit.postMedia("H:\\pictures\\桌面.jpg", "image"));
		}
		
		return null;
	}
	/**function(回复图文消息)
	 * @param  @param regMsg2Map
	 * @param  @return         
	 * @return String 
	 * @throws IOException 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	private static String imageTypeHandler(Map<String, String> regMsg2Map,String mediaid) throws IOException {
		Map<String, String>  map=new HashMap<String, String>();
		map.put("ToUserName", regMsg2Map.get("FromUserName"));
		map.put("FromUserName", regMsg2Map.get("ToUserName"));
		map.put("CreateTime", new Date().getTime()+"");
		map.put("MsgType", "image");
		map.put("Image", "<MediaId>"+mediaid+"</MediaId>");
		return map2Xml(map);
	}
	/**function(把map中的信息拼成一个xml格式的数据)
	 * @param  @param regMsg2Map
	 * @param  @return         
	 * @return String 
	 * @throws IOException 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	private static String textTypeHandler(Map<String, String> regMsg2Map) throws IOException {
		Map<String, String>  map=new HashMap<String, String>();
		map.put("ToUserName", regMsg2Map.get("FromUserName"));
		map.put("FromUserName", regMsg2Map.get("ToUserName"));
		map.put("CreateTime", new Date().getTime()+"");
		map.put("MsgType", "text");
		String replyContent="你发送消息的内容不正确";
		String con = regMsg2Map.get("Content");
		if(replyMsg.containsKey(con)){
			replyContent=replyMsg.get(con);
		}
		map.put("Content", replyContent);
		return map2Xml(map);
	}
	/**function(把一个map转换成一个xml)
	 * @param  @param map
	 * @param  @return         
	 * @return String 
	 * @throws IOException 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	private static String map2Xml(Map<String, String> map) throws IOException {
		Document d=DocumentHelper.createDocument();
		Element root = d.addElement("xml");
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			root.addElement(key).addText(map.get(key));
		}
		StringWriter sw=new StringWriter();
		XMLWriter xw=new XMLWriter(sw);
		xw.setEscapeText(false);
		xw.write(d);
		//d.write(sw);
		return sw.toString();
	} 
	
	/**function(使用post请求发送模板消息)
	 * @param  @param tempMsg
	 * @param  @return         
	 * @return String 
	 * @throws                
	 * @author lh 
	 * @Date   2016年1月25日
	*/
	public  static String postTemlateMsg(TempleMsg tempMsg){ 
		String url=WeixinFinalValue.SEND_TEMPLATE_MSG;
		url=url.replace("ACCESS_TOKEN", RefreshAccessTokenTask.at);
		String data = JsonUtil.getInstance().obj2json(tempMsg);
		return WeixinKit.postString(url, data, "application/json");
	}
}
