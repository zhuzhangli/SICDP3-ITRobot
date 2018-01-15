package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public interface CommunityQuestionPersistenceMapper extends IBaseDao<CommunityQuestionPersistence, String>
{
	//查看自己的论坛
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit(String userid,int startNumber,int number);
		
	//查看自己的论坛_time-limit
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit_Time(String userid,int startNumber,int number,String time);
	
	//时间倒序显示最新5条社区问题	
	@Select("SELECT * FROM TBL_CommunityQuestion  ORDER BY TIME DESC LIMIT #{0},5")
	List<CommunityQuestionPersistence> question_getCommunity_isanswer(int startnumber);
	
	//zyq_ajax_question校验是否重复添加	 
	@Select("SELECT  COMMUNITYQUESTIONID FROM TBL_CommunityQuestion WHERE USERID=#{0} AND TITLE=#{1}")
	String question_iscurrent(String userid, String questiontitle);

	//返回对应分类的全部问题
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity(String classifyid);
	
	//相关问题_返回对应分类的时间倒叙前5个问题
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} ORDER BY TIME DESC LIMIT 5")
	List<CommunityQuestionPersistence> selectQuestionByClassifyId(String faqclassifyid);
	
	//获取faqClassifyId分类号下的所有社区问题总数
	@Select("SELECT COUNT(1) FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0}")
	int questionSizeByClassifyId(String faqClassifyId);
	
	//获取faqClassifyId分类号下的所有社区问题数_根据是否已有最佳答案来划分
	@Select("SELECT COUNT(1) FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} AND ISANSWER=#{1}")
	int questionSizeByClassifyIdLimit(String faqClassifyId, int isanswer);
	
	//获取faqClassifyId分类号下的所有社区问题信息
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} AND ISANSWER=#{1} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity2(String classifyid,int isanswer);
	
	//zyq_question2_问题内容详情
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE COMMUNITYQUESTIONID=#{0} ")
	List<CommunityQuestionPersistence> question2_getCommunity(String questionId);
		
	//查看问题号为	questionId 的提问者id
	@Select("SELECT USERID FROM TBL_CommunityQuestion WHERE COMMUNITYQUESTIONID=#{0} ")
	String CommunityQuestion(String questionId);
	
	// zyq_question2_设为最佳答案
	@Update("UPDATE TBL_CommunityQuestion SET ISANSWER=#{1} WHERE COMMUNITYQUESTIONID=#{0}")
	void updateBestAnswer(String questionId, int isanswer);

	//zzl_获取问题中心中所有没有最佳答案的问题信息_2017年11月6日09:20:07
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER=#{0} AND QUESTIONSTATE = #{1}")
	List<CommunityQuestionPersistence> problemInfo(int isanswer,int questionState);

	//问题数量
	@Select("SELECT COUNT(1) FROM TBL_CommunityQuestion WHERE ISANSWER=#{0} AND QUESTIONSTATE = #{1}")
	int problemCount(int isanswer, int questionState);
	
	//zzl_更新社区问题状态_2017年11月12日18:38:02
	@Update("UPDATE TBL_CommunityQuestion SET QUESTIONSTATE=#{1} WHERE COMMUNITYQUESTIONID=#{0}")
	void updateCommunityQuestionState(String questionId, int questionState);
	
	//根据是否有最佳答案获取社区问题信息
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER=#{1} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity2_isanswer(int isanswer);
	
	//zyq_notice_查询用户的提问 			 !!!未使用
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion(String userid);	
} 
