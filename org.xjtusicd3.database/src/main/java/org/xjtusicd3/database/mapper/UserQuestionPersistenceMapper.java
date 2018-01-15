package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public interface UserQuestionPersistenceMapper extends IBaseDao<UserQuestionPersistence, String>
{	
	//zzl_获取有满意度且满意度为1的前台问题_2017年11月4日20:47:35
	@Select("SELECT * FROM TBL_UserQuestion,TBL_RobotAnswer WHERE TBL_UserQuestion.USERQUESTIONID = TBL_RobotAnswer.USERQUESTIONID AND TBL_RobotAnswer.SATICFACTION = #{0} AND TBL_RobotAnswer.QUESTIONSTATE = #{1} ORDER BY TBL_UserQuestion.QUESTIONTIME DESC")
	List<UserQuestionPersistence> resolvedEvent(int saticfaction,int questionstate);
	
	//获取已处理事件总数
	@Select("SELECT COUNT(1) FROM TBL_UserQuestion,TBL_RobotAnswer WHERE TBL_UserQuestion.USERQUESTIONID = TBL_RobotAnswer.USERQUESTIONID AND TBL_RobotAnswer.SATICFACTION = #{0} AND TBL_RobotAnswer.QUESTIONSTATE = #{1}")
	int getResolvedCount(int saticfaction,int questionstate);
	
	//zzl _获取用户问题信息_2017年11月4日21:32:11
	@Select("SELECT * FROM TBL_UserQuestion WHERE USERQUESTIONID=#{0}")
	List<UserQuestionPersistence> getUserQuestionById(String userQuestionId);
		
}
