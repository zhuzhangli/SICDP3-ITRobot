package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.RobotAnswerPersistence;

public interface RobotAnswerPersistenceMapper extends IBaseDao<RobotAnswerPersistence, String>{
	@Insert("INSERT INTO TBL_RobotAnswer(ROBOTANSWERID,SATICFACTION,USERQUESTIONID,FAQANSWERID)"
			+ " VALUES (#{0},#{1},#{2},#{3})")
	void addUserSaticfaction(String robotAnswerId, int saticfaction, String questionId, String answerId);

}
