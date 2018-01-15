package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.FaqPicturePersistenceMapper;
import org.xjtusicd3.database.model.FaqPicturePersistence;

public class FaqPictureHelper {
	/**
	 * faq首页面推荐栏图片更新
	 */
	public static void faqImgSave(FaqPicturePersistence faqPicturePersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPicturePersistenceMapper mapper = session.getMapper(FaqPicturePersistenceMapper.class);
		mapper.save(faqPicturePersistence);
		session.close();		
	}

	/**
	 * 获取faq推荐栏信息
	 */
	public static List<FaqPicturePersistence> faqPicture(int state,int num) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPicturePersistenceMapper mapper = session.getMapper(FaqPicturePersistenceMapper.class);
		List<FaqPicturePersistence> faqPicturePersistences = mapper.faqPicture(state, num);
		session.close();
		return faqPicturePersistences;	
	}

	/**
	 * 删除faq推荐栏信息
	 */
	public static void deletePic(String faqPicId, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPicturePersistenceMapper mapper = session.getMapper(FaqPicturePersistenceMapper.class);
		mapper.deletePic(faqPicId,state);
		session.close();		
	}

	/**
	 * 修改faq推荐栏图片标题
	 */
	public static void updateTitle(String faqPicId, String picTitle) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPicturePersistenceMapper mapper = session.getMapper(FaqPicturePersistenceMapper.class);
		mapper.updateTitle(faqPicId, picTitle);
		session.close();		
	}

}
