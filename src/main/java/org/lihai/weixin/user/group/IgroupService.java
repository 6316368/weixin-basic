package org.lihai.weixin.user.group;

import java.util.List;

import org.lihai.weixin.user.model.Wgroup;

/**ClassName:IgroupService
 * Function: 针对用户组的一系列操作
 * @author   lh
 * @Date	 2016	2016年1月26日		上午11:06:00
 */
public interface IgroupService {
	/***
	 *添加一个用户组 
	 */
	public void  add(Wgroup   group);
	/***
	 *查询所有的分组
	 */
	public List<Wgroup> quaryall();
	/***
	 *根据用户ID在哪个用户组中
	 */
	public Wgroup quaryUserGroupByOpenId(String openid);
	/***
	 *通过用户组的ID更新用户组信息 
	 */
	public  void updateGroupByGroupId(int  groupID,String   name);
	/***
	 *通过用户组的ID更新用户组信息 
	 */
	public  void deleteGroupByGroupId(int  groupID);
	/***
	 *移动某个用户到某个用户组中
	 */
	public  void  moveUserToGroup(String openid,int gid);
	/***
	 *移动一组用户到某个用户组中
	 */
	public  void moveUsersToGroup(List<String> opendis,int gid);
}
