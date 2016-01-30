package org.lihai.weixin.user.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lihai.weixin.Quartz.RefreshAccessTokenTask;
import org.lihai.weixin.json.JsonUtil;
import org.lihai.weixin.kit.WeixinKit;
import org.lihai.weixin.model.WeixinFinalValue;
import org.lihai.weixin.user.model.Wgroup;
import org.lihai.weixin.web.servlet.WeixinContext;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements IgroupService {

	public void add(Wgroup group) {
		String url=WeixinFinalValue.ADD_GROUP;
		url=url.replaceAll("ACCESS_TOKEN", WeixinContext.getAccessToken());
		Map<String, Wgroup>  map=new HashMap<String, Wgroup>();
		map.put("group", group);
		String json = JsonUtil.getInstance().obj2json(map);
		System.out.println(json);
		String con = WeixinKit.postString(url, json, "application/json");
	   
	}

	public List<Wgroup> quaryall() {
		String url=WeixinFinalValue.QUERY_ALL_GROUP;
		url=url.replaceAll("ACCESS_TOKEN", WeixinContext.getAccessToken());
		String json = WeixinKit.getString(url);
		Map<String, List<Map>> maps=(Map<String, List<Map>>) JsonUtil.getInstance().json2obj(json, Map.class);
		List<Map> listmap=maps.get("groups");
		List<Wgroup>  listwgrouop=new ArrayList<Wgroup>();
		for (Map map : listmap) {
			Wgroup w=new Wgroup();
			w.setId((Integer) map.get("id"));
			w.setName((String) map.get("name"));
			w.setCount((Integer) map.get("count"));
			listwgrouop.add(w);
		}
		return listwgrouop;
	}

	public Wgroup quaryUserGroupByOpenId(String openid) {
		List<Wgroup> quaryall = quaryall();
		String url=WeixinFinalValue.QUERY_USER_GROUP;
		url=url.replace("ACCESS_TOKEN", WeixinContext.getAccessToken());
		Map<String, String>  map=new HashMap<String, String>();
		map.put("openid", openid); 
		String json = JsonUtil.getInstance().obj2json(map);
		String con = WeixinKit.postString(url, json, "application/json");
		Map<String, Integer>  gm=(Map<String, Integer> )JsonUtil.getInstance().json2obj(con, Map.class);
		Integer gid=gm.get("groupid");
		for (Wgroup wgroup : quaryall) {
			if(wgroup.getId()==gid){
				return wgroup;
			}
		}
		System.out.println(con);
		return null;
	}

	public void updateGroupByGroupId(int groupID,String   name) {
		String url = WeixinFinalValue.UPDATE_GROUP_NAME;
		url=url.replaceAll("ACCESS_TOKEN", WeixinContext.getAccessToken());
		Map<String,Wgroup> wmap = new HashMap<String,Wgroup>();
		Wgroup wg = new Wgroup();
		wg.setId(groupID);
		wg.setName(name);
		wmap.put("group", wg);
		String json = JsonUtil.getInstance().obj2json(wmap);
		WeixinKit.postString(url, json, "application/json");
		
	}

	public void moveUserToGroup(String openid, int gid) {
		String url = WeixinFinalValue.MOVE_USER_GROUP;
		url=url.replaceAll("ACCESS_TOKEN", WeixinContext.getAccessToken());
		Map<String,String> wmap = new HashMap<String,String>();
		wmap.put("openid", openid);
		wmap.put("to_groupid", ""+gid);
		String json = JsonUtil.getInstance().obj2json(wmap);
		WeixinKit.postString(url, json, "application/json");
	}

	public void moveUsersToGroup(List<String> opendis, int gid) {
		String url = WeixinFinalValue.MOVE_USERS_GROUP;
		url=url.replaceAll("ACCESS_TOKEN", WeixinContext.getAccessToken());
		Map<String,Object> wmap = new HashMap<String,Object>();
		wmap.put("openid_list", opendis);
		wmap.put("to_groupid", "102");
		String json = JsonUtil.getInstance().obj2json(wmap);
		WeixinKit.postString(url, json, "application/json");
	}

	public void deleteGroupByGroupId(int groupID) {
		String url = WeixinFinalValue.DELETE_GROUP;
		url=url.replaceAll("ACCESS_TOKEN", WeixinContext.getAccessToken());
		Map<String,Wgroup> wmap = new HashMap<String,Wgroup>();
		Wgroup wg = new Wgroup();
		wg.setId(groupID);
		wmap.put("group", wg);
		String json = JsonUtil.getInstance().obj2json(wmap);
		WeixinKit.postString(url, json, "application/json");
	}

}
