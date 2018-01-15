package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.RobotAnswerPersistence;

public interface RobotAnswerPersistenceMapper extends IBaseDao<RobotAnswerPersistence, String>{
	//查看是否已填写过满意度
	@Select("SELECT ROBOTANSWERID FROM TBL_RobotAnswer WHERE USERQUESTIONID = #{0}")
	String getQuertionInfo(String questionId);
	
	//更新已处理的状态
	@Update("UPDATE TBL_RobotAnswer SET QUESTIONSTATE=#{1}  WHERE USERQUESTIONID=#{0}")
	void updateRobotAnswerState(String questionId, int questionState);
	
	//zzl_获取应答表中问题对应的知识库答案id_2017年11月4日21:31:49
	@Select("SELECT FAQANSWERID FROM TBL_RobotAnswer WHERE USERQUESTIONID=#{0} ")
	String getFaqAnswerIdByQuestionId(String userQuestionId);
}
