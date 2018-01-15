package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.FaqPicturePersistence;

public interface FaqPicturePersistenceMapper extends IBaseDao<FaqPicturePersistence, String>{
	//获取faq推荐栏信息
	@Select("SELECT * FROM tbl_faqpicture WHERE STATE = #{0} ORDER BY  TIME DESC LIMIT #{1}")
	List<FaqPicturePersistence> faqPicture(int state,int num);

	//删除faq推荐栏信息
	@Update("UPDATE tbl_faqpicture SET  STATE = #{1} WHERE FAQPICTUREID=#{0}")
	void deletePic(String faqPicId, int state);

	//修改faq推荐栏图片标题
	@Update("UPDATE tbl_faqpicture SET  DESCRIPTION = #{1} WHERE FAQPICTUREID=#{0}")
	void updateTitle(String faqPicId, String picTitle);

}
