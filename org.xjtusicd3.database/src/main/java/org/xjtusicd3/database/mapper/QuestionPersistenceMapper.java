package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.QuestionPersistence;

public interface QuestionPersistenceMapper extends IBaseDao<QuestionPersistence, String>{
	// zyq_robot-根据二级分类号获取faq问题信息_根据收藏量取前三
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID,TBL_FAQquestion.FAQTITLE FROM TBL_FAQquestion WHERE TBL_FAQquestion.FAQCLASSIFYID=#{0} ORDER BY TBL_FAQquestion.COLLECTION DESC LIMIT 3 ")
	public List<QuestionPersistence> SecondClassify_robot(String ClassifyId);
	
	//获取该分类下faq信息的总数
	@Select("SELECT COUNT(1) FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0}")
	public int pageTotal(String ClassifyId);
	
	//获取已审核faq
	@Select("SELECT * FROM TBL_FAQquestion ,tbl_faqclassify WHERE TBL_FAQquestion.FAQCLASSIFYID = tbl_faqclassify.FAQCLASSIFYID AND  FAQSTATE = #{1} AND tbl_faqclassify.FAQPARENTID  = #{0} ORDER BY MODIFYTIME DESC LIMIT #{2},#{3}")
	public List<QuestionPersistence> faqAudited(String classifyId,int faqState,int startnum, int number);
	
	//根据分类号和浏览量获取一条数据
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0} AND FAQSTATE = #{1} ORDER BY SCAN DESC LIMIT #{2}")
	public QuestionPersistence faq1_faqPersistences(String ClassifyId, int faqState, int startNumber);
	
	//根据分类号获取数据
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0} AND FAQSTATE = #{1} ORDER BY SCAN DESC LIMIT #{2},#{3}")
	public List<QuestionPersistence> faq1_faqPersistences2(String faqClassify, int faqState,int startNumber,int number);
	
	//zyq_faq2_知识列表
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0} AND FAQSTATE = #{1} LIMIT #{2},#{3}")
	public List<QuestionPersistence> faq2_faqlist(String ClassifyId, int faqState,int pagenow,int number);
	
	//zzl_记录用户提问记录_查看用户提问是否为faq中的内容_2017年10月22日11:43:43
	@Select("SELECT COUNT(FAQQUESTIONID) FROM TBL_FAQquestion  WHERE FAQTITLE=#{0}")
	public boolean getFaqQuestion(String comment);
	
	//zyq_faq_查看用户动态	 
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQSTATE = #{0} ORDER BY MODIFYTIME DESC LIMIT #{1}")
	public List<QuestionPersistence> faq_userDynamics(int faqState,int number);
	
	//zzl_获取待审核faq_2017年11月6日20:02:27
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQSTATE = #{0} ORDER BY MODIFYTIME DESC LIMIT #{1},#{2}")
	public List<QuestionPersistence> faqPendingAudits(int faqState,int startnum, int number);
	
	// faq_未登录用户推荐_2017年9月14日21:16:18
	@Select("SELECT FAQQUESTIONID, FAQTITLE, MODIFYTIME, FAQDESCRIPTION ,COLLECTION,SCAN,USERID,sum(CAST(SCAN AS SIGNED)+CAST(COLLECTION AS SIGNED)*10) as a FROM TBL_FAQquestion WHERE FAQSTATE = #{0} GROUP BY FAQQUESTIONID ORDER BY a DESC LIMIT #{1},#{2}")
	public List<QuestionPersistence> faq_recommend_Limit(int faqstate,int startnum,int number);
	
	//查看自己的知识库_每次查看5条
	@Select("SELECT * FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQquestion.USERID=#{0} AND FAQSTATE = #{1} ORDER BY TBL_FAQquestion.MODIFYTIME DESC LIMIT #{2},#{3}")
	public List<QuestionPersistence> personal2_faq_Limit(String userId,int faqstate,int startNumber,int number);
	
	//判断是创建知识还是修改知识
	@Select("SELECT COUNT(FAQQUESTIONID) FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0} AND MODIFYNUMBER=#{1}")
	public boolean personal2_Ismodify(String faqquestionid, String modifynumber);
		
	//zyq_faq3_根据知识ID找类型classify
	@Select("SELECT FAQCLASSIFYID FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0}")
	public String faq3_faqclassifyId(String QuestionId);
	
	//根据faq问题id获取用户id
	@Select("SELECT USERID FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0}")
	public String findUserIdByQuestionId(String QuestionId);
	
	//获取faqquestionId对应faq问题
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0}" )
	public List<QuestionPersistence> getFaqQuestionInfo(String faqQuestionId);
	
	//获取faq浏览量
	@Select("SELECT SCAN FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0}")
	public String getFaqScan(String questionId);
	
	//zyq_faq3_知识内容
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0} AND FAQSTATE = #{1} ")
	public List<QuestionPersistence> faq3_faqcontent(String QuestionId,int faqstate);
	
	//删除待审核faq
	@Update("UPDATE TBL_FAQquestion SET  FAQSTATE = #{1} WHERE FAQQUESTIONID=#{0}")
	public void deleteFAQ(String questionId,int faqstate);
	
	//对访问FAQ页面的浏览量进行增加	 
	@Update("UPDATE TBL_FAQquestion SET TBL_FAQquestion.SCAN=#{1} WHERE TBL_FAQquestion.FAQQUESTIONID=#{0}")
	public void updateFAQScan(String faqquestionId,String faqScan);
	
	//更新faq信息1
	@Update("UPDATE TBL_FAQquestion SET  MODIFYNUMBER=#{1}, FAQSTATE = #{2} WHERE FAQQUESTIONID=#{0}")
	public void updateFaqInfo1(String questionId, String modifyNum, int faqState);
	
	//更新faq信息
	@Update("UPDATE TBL_FAQquestion SET FAQKEYWORDS=#{1} ,FAQDESCRIPTION=#{2} ,  MODIFYNUMBER=#{3}, FAQSTATE = #{4} WHERE TBL_FAQquestion.FAQQUESTIONID=#{0}")
	public void updateFaqInfo(String questionId, String keywords, String description, String modifyNum,int faqState);
	
	//获取分类下faq具体信息	
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID ,TBL_FAQquestion.FAQTITLE,TBL_FAQquestion.COLLECTION ,TBL_FAQquestion.SCAN, TBL_FAQquestion.MODIFYTIME,TBL_FAQquestion.FAQDESCRIPTION ,TBL_FAQquestion.USERID,sum(CAST(SCAN AS SIGNED)+CAST(COLLECTION AS SIGNED)*10) as a FROM TBL_FAQquestion ,TBL_FAQclassify WHERE TBL_FAQclassify.FAQPARENTID=#{0} AND TBL_FAQquestion.FAQCLASSIFYID = TBL_FAQclassify.FAQCLASSIFYID AND FAQSTATE = #{1} GROUP BY TBL_FAQquestion.FAQQUESTIONID ORDER BY a DESC LIMIT #{2},#{3}")
	public List<QuestionPersistence> questionView(String parentId,int state, int startnum, int num);
	
	//推荐知识_根据收藏量推荐前4个	 
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID ,TBL_FAQquestion.FAQTITLE,TBL_FAQquestion.FAQDESCRIPTION FROM TBL_FAQquestion ,TBL_FAQclassify WHERE TBL_FAQclassify.FAQPARENTID=#{0} AND TBL_FAQclassify.FAQCLASSIFYID = TBL_FAQquestion.FAQCLASSIFYID AND FAQSTATE = 2 ORDER BY TBL_FAQquestion.COLLECTION DESC LIMIT 4")
	public List<QuestionPersistence> faqInfo_limit(String faqParentId);
	
	// faqadd_校验知识是否重复增添
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQquestion.FAQTITLE=#{0} AND TBL_FAQanswer.USERID=#{1}")
	public String faqadd_iscurrent(String faqtitle, String userid);
	
	//查看知识库信息
	@Select("SELECT * FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQquestion.USERID=#{0} AND STR_TO_DATE(TBL_FAQquestion.MODIFYTIME,'%Y-%m-%d %H:%i')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TBL_FAQquestion.MODIFYTIME DESC LIMIT #{1},#{2}")
	public List<QuestionPersistence> personal2_faq_Limit_Time(String userId,int startNumber,int number,String time);
	
	//查看username发表的title faq的信息
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQTITLE=#{0} AND USERID=#{1}" )
	public List<QuestionPersistence> titleIsExist(String title, String userId);
	
	//获取前200条faq		!!!未使用
	@Select("SELECT * FROM TBL_FAQquestion LIMIT 200")
	public List<QuestionPersistence> getFaq();
	
	//zyq_robot_查看所以的faq信息			!!!未使用
	@Select("SELECT * FROM TBL_FAQquestion")
	public List<QuestionPersistence> getFaqTotal();	
}
