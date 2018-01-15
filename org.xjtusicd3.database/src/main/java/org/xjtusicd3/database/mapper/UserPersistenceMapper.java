package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{	
	//zyq_login_ajax_注册	
	@Insert("INSERT INTO TBL_User(TBL_User.USERID,TBL_User.USERNAME,TBL_User.USERPASSWORD,TBL_User.USERSTATE,TBL_User.CREATETIME,TBL_User.AVATAR) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	public void login_register(String userid,String name,String password,int userstate,String createTime,String userimage);
	
	//判断用户是否登录
	@Select("SELECT COUNT(USERID) FROM TBL_User WHERE USERNAME=#{0} AND USERPASSWORD=#{1} AND USERSTATE=2")
	public Boolean isLogin(String username, String password);
	
	//判断用户名是否已存在
	@Select("SELECT COUNT(USERID) FROM TBL_User WHERE USERNAME = #{0}")
	public Boolean getUserInfoByName(String name);
	
	//zyq_message_ajax_获得用户基本信息	 
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	public List<UserPersistence> getUserInfo(String username);

	//通过username找userID
	@Select("SELECT USERID FROM TBL_User WHERE USERNAME=#{0}")
	public String getUserIdByName(String username);
	
	//修改用户信息	
	@Update("UPDATE TBL_User SET GENDER=#{1},USERBIRTHDAY=#{2},USERADDRESS=#{3},USERSIGNATURE=#{4} WHERE USERID=#{0}")
	public void updateUserInfo2(String userid, String usersex, String userbirthday, String address,String userbrief );
	
	//修改密码	
	@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USERNAME=#{0}")
	public void updateUserPassword(String userid, String password);
		
	//zzl_获取指定用户信息_2017年11月10日11:46:39
	@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
	public List<UserPersistence> getUserInfoById(String userId);
	
	//通过用户Id获取用户名
	@Select("SELECT USERNAME FROM TBL_User WHERE USERID=#{0}")
	public String getUserNameById(String userId);
	
	//获取用户角色名
	@Select("SELECT ROLENAME FROM TBL_User,TBL_Role WHERE TBL_User.ROLEID = TBL_Role.ROLEID AND USERID=#{0}")
	public String getRoleNameByUserId(String userId);
	
	//zzl_获取所有待审核用户_2017年11月9日21:29:09
	@Select("SELECT * FROM TBL_User WHERE USERSTATE = #{0} ORDER BY CREATETIME DESC")
	public List<UserPersistence> getAllUsers(int userState);
	
	//zzl_获取所有普通用户信息 -- 即USERSTATE = 2 且 角色名是普通用户_2017年11月9日22:34:22
	@Select("SELECT * FROM TBL_User ,TBL_Role WHERE TBL_User.ROLEID = TBL_Role.ROLEID AND USERSTATE = #{0} AND TBL_Role.ROLENAME=#{1} ORDER BY TBL_User.CREATETIME DESC ")
	public List<UserPersistence> getUserLists(int userState, String roleName);
	
	//zzl_更改用户状态_2017年11月10日10:33:30
	@Update("UPDATE TBL_User SET USERSTATE = #{1} WHERE USERID=#{0}")
	public void updateUserState(String userId, int userState);
	
	//zyq_上传图片
	@Update("UPDATE TBL_User SET TBL_User.AVATAR=#{1} WHERE USERNAME=#{0}")
	public void updateUserImage(String username, String path);
	
	//Excel导入用户信息
	@Insert("INSERT INTO TBL_User(TBL_User.USERID,TBL_User.USERPASSWORD,TBL_User.USERNAME,TBL_User.USERSTATE,TBL_User.AVATAR,TBL_User.ROLEID) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	public void login_register2(String userid, String password, String username, int userstate, String userimage,
			String roleid);
	
	//更新员工角色
	@Update("UPDATE TBL_User SET ROLEID = #{1} WHERE USERID=#{0}")
	public void updateUserRole(String userId, String roleId);

	//重置密码
	@Update("UPDATE TBL_User SET USERPASSWORD = #{1} WHERE USERID=#{0}")
	public void resetPass(String userid, String password);
	
}
