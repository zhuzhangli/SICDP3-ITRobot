package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.model.QuestionPersistence;

public class QuestionHelper {
	/**
	 * robot-分类
	 */
	public static List<QuestionPersistence> SecondClassify_robot(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.SecondClassify_robot(ClassifyId);
		session.close();
		return list;
	}
	
	/**
	 * abstract:记录用户提问记录_查看用户提问是否为faq中的内容
	 */
	public static boolean getFaqQuestion(String comment) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		boolean list = mapper.getFaqQuestion(comment);
		session.close();
		return list;
	}
	
	/**
	 * zyq_faq_查看用户动态
	 */
	public static List<QuestionPersistence> faq_userDynamics(int faqState,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq_userDynamics(faqState,number);
		session.close();
		return list;
	}
		
	//查看自己的知识库_每次查看5条
	public static List<QuestionPersistence> personal2_faq_Limit(String userId,int faqstate,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq_Limit(userId,faqstate,startNumber,number);
		session.close();
		return list;
	}
	
	//判断是创建知识还是修改知识
	public static boolean personal2_Ismodify(String faqquestionid, String modifynumber) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		boolean list = mapper.personal2_Ismodify(faqquestionid,modifynumber);
		session.close();
		return list;
	}

	//faq_按时间推荐
	public static List<QuestionPersistence> faq_recommend_Limit(int faqstate,int startnum,int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq_recommend_Limit(faqstate,startnum,number);
		session.close();
		return list;
	}	
		
	/**
	 * faq3_根据知识ID找类型classify
	 */
	public static String faqclassify(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String ClassifyId = mapper.faq3_faqclassifyId(QuestionId);
		session.close();
		return ClassifyId;
	}	
		
	/**
	 * abstract:获取分类下faq具体信息
	 * @param num 
	 * @param startnum 
	 */
	public static List<QuestionPersistence> questionView(String parentId, int state, int startnum, int num) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.questionView(parentId,state,startnum,num);
		session.close();
		return list;
	}	
		
	/**
	 * abstract:推荐知识_根据收藏量推荐前4个
	 */
	public static List<QuestionPersistence> faqInfo_limit(String faqParentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqInfo_limit(faqParentId);
		session.close();
		return list;
	}
	
	/**
	 * 根据分类id及浏览量获取一条数据
	 * @param faqState 
	 */
	public static QuestionPersistence faq1_faqPersistences(String faqClassify, int faqState,int startNumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		QuestionPersistence list = mapper.faq1_faqPersistences(faqClassify,faqState,startNumber);
		session.close();
		return list;
	}
	
	/**
	 * 根据分类id及浏览量获取5条数据
	 */
	public static List<QuestionPersistence> faq1_faqPersistences2(String faqClassify,int faqState,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq1_faqPersistences2(faqClassify,faqState,startNumber,number);
		session.close();
		return list;
	}
	
	/**
	 * faq2_知识列表
	 */	
	public static List<QuestionPersistence> faq2_faqlist(String ClassifyId,int faqState,int pagenow,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int a  = (pagenow-1)*5;
		List<QuestionPersistence> list = mapper.faq2_faqlist(ClassifyId,faqState,a,number);
		session.close();
		return list;
	}
	
	/**
	 * 根据faq问题id获取用户id
	 */
	public static String findUserIdByQuestionId(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String UserId = mapper.findUserIdByQuestionId(QuestionId);
		session.close();
		return UserId;
	}
	
	/**
	 * 获取该分类下faq信息的总数
	 */
	public static int pageTotal(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int pageTotal = mapper.pageTotal(ClassifyId);
		session.close();
		return pageTotal;
	}
	
	/**
	 * faq3_知识内容
	 * ！！！可合并
	 */
	public static List<QuestionPersistence> faq3_faqcontent(String faqId,int faqstate){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent(faqId,faqstate);
		session.close();
		return list;
	}
	
	/**
	 * abstract:对访问FAQ页面的浏览量进行增加
	 */
	public static void updateFAQScan(String faqquestionid, String faqScan){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.updateFAQScan(faqquestionid,faqScan);
		session.close();
	}
	
	/**
	 *	获取faq浏览量
	 */
	public static String getFaqScan(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String scan = mapper.getFaqScan(questionId);
		session.close();
		return scan;
	}
	
	/**
	 * abstract:faqadd_校验知识是否重复增添
	 */
	public static String faqadd_iscurrent2(String faqtitle,String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String faqQuestionId = mapper.faqadd_iscurrent(faqtitle,userId);
		session.close();
		return faqQuestionId;
	}
	
	/**
	 * spider_知识库问题的添加
	 */
	public static void save(QuestionPersistence questionPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.save(questionPersistence);
		session.close();
	}
	
	/**
	 * 查看自己的知识库
	 */
	public static List<QuestionPersistence> personal2_faq_Limit_Time(String userId,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq_Limit_Time(userId,startNumber,number,time);
		session.close();
		return list;
	}
	
	//zzl_获取待审核faq_2017年11月6日20:02:27
	public static List<QuestionPersistence> faqPendingAudits( int faqState,int startnum,int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqPendingAudits(faqState,startnum,number);
		session.close();
		return list;
	}
	
	/**
	 * 待审核faq
	 */
	public static List<QuestionPersistence> getFaqQuestionInfo(String faqQuestionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaqQuestionInfo(faqQuestionId);
		session.close();
		return list;
	}
	
	/**
	 * 更新faq问题状态
	 */
	public static void updateFaqInfo(String questionId, String keywords, String description, String modifyNum,
			int faqState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.updateFaqInfo(questionId,  keywords,  description,  modifyNum,faqState);
		session.close();
		
	}
	
	/**
	 * 更新faq问题状态1
	 */
	public static void updateFaqInfo1(String questionId, String modifyNum, int faqState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.updateFaqInfo1(questionId,modifyNum,faqState);
		session.close();		
	}	
		
	/**
	 * zpz_delete faq
	 */
	public static void deleteFAQ(String questionId,int faqstate){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.deleteFAQ(questionId,faqstate);  
		session.close();
	}	
		
	//获取已审核faq
	public static List<QuestionPersistence> faqAudited(String classifyId,int faqState,int startnum, int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqAudited(classifyId,faqState,startnum,number);
		session.close();
		return list;
	}	
	
	/**
	 * 查看username发表的title faq的信息
	 */
	public static List<QuestionPersistence> titleIsExist(String title, String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.titleIsExist(title, userId);
		session.close();
		return list;
	}
	
	/*
	 * zpz_get faq information		!!!未使用
	 */
	public static List<QuestionPersistence> getFaq(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaq();
		session.close();
		return list;
	}
	
	/*
	 * zyq_robot_查看所以faq的信息			!!!未使用
	 */
	public static List<QuestionPersistence> getFaqTotal(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaqTotal();
		session.close();
		return list;

	}	
}
