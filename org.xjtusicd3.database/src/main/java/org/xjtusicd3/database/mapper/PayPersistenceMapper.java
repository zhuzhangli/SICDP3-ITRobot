package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.PayPersistence;

public interface PayPersistenceMapper extends IBaseDao<PayPersistence, String>{
	//查找关注的对象
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2} ")
	List<PayPersistence> payList_Limit(String userid,int startNumber,int number);
	
	//查找关注的对象_时间限制		！！！
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{1},'%Y-%m-%d %H:%i:%s') ORDER BY TIME DESC LIMIT #{2},#{3}")
	List<PayPersistence> payList_time_Limit(String userid, String time,int startNumber,int number);
	
	//查找关注的对象_时间段限制
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{1},'%Y-%m-%d %H:%i:%s') AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')>STR_TO_DATE(#{4},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{2},#{3}")
	List<PayPersistence> payList_time_Limit_Time(String userid, String time,int startNumber,int number,String time2);
	
	//zyq_personal3_关注、被关注
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0}")
	List<PayPersistence> payList(String userid);
	
	//查看被关注对象信息
	@Select("SELECT * FROM TBL_Pay WHERE BEPAYUSERID=#{0}")
	List<PayPersistence> bepayList(String beuserid);
	
	//zyq_personal2_查看关注列表
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND BEPAYUSERID=#{1}")
	List<PayPersistence> getpayList(String userId, String touserId);
	
	//获取关注人数
	@Select("SELECT COUNT(PAYID) FROM TBL_Pay WHERE PAYUSERID=#{0} ")
	int payListSize(String userid);
	
	//粉丝数
	@Select("SELECT COUNT(PAYID) FROM TBL_Pay WHERE BEPAYUSERID=#{0}")
	int bepayListSize(String beuserid);
	
	//zyq_personal2_取消关注
	@Delete("DELETE FROM TBL_Pay WHERE PAYUSERID=#{0} AND BEPAYUSERID=#{1}")
	void deletePay(String userId, String touserId);	
}
