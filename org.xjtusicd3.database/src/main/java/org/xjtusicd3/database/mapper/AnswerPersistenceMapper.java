package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.AnswerPersistence;

public interface AnswerPersistenceMapper extends IBaseDao<AnswerPersistence, String>{
	//zyq_faq3_知识内容
	@Select("SELECT * FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public List<AnswerPersistence> getAnswerByQuestionId(String QuestionId);
	
	//根据faq问题id查找用户id
	@Select("SELECT USERID FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public String findUserIdByFAQQuestionId(String faqquestionid);
	
	
	/*********************************************        后台                    ***************************************************************/	
	//获取faqanswerId相对应的内容
	@Select("SELECT FAQCONTENT FROM TBL_FAQanswer WHERE FAQANSWERID=#{0}")
	public String getContentById(String faqAnswerId);
	
	//根据问题id获取问题答案内容
	@Select("SELECT FAQCONTENT FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public String getContentByQuestionId(String faqquestionid);
	
	//更新faq信息
	@Update("UPDATE TBL_FAQanswer SET FAQCONTENT=#{1}  WHERE FAQQUESTIONID=#{0}")
	public void updateFaqAnswerInfo(String questionId, String faqcontent);
	
	/**
	 * zyq_notice_ajax_查询FAQ评论通知
	 * !!!未使用
	 */
	@Select("SELECT * FROM TBL_FAQanswer WHERE USERID=#{0}")
	public List<AnswerPersistence> notice_faqanswerList(String userId);
	
}
