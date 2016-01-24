package org.lihai.weixin.msg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
@SuppressWarnings("unchecked")
public class MessageKit {

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
    private  static String reg2Xml(HttpServletRequest req) throws IOException{
		BufferedReader  bf=null;
		bf=new BufferedReader(new InputStreamReader(req.getInputStream()));
		String str=null;
		StringBuffer  sb=new StringBuffer();
		while((str=bf.readLine())!=null){
			sb.append(str);
		}
		return  sb.toString();
	} 
}
