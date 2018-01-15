package org.xjtusicd3.partner.service;

import java.util.List;

import org.xjtusicd3.database.helper.FaqPictureHelper;
import org.xjtusicd3.database.model.FaqPicturePersistence;

public class FaqPicService {
	/**
	 * 获取faq推荐栏信息
	 */
	public static List<FaqPicturePersistence> faqPicture(int state,int num) {
		List<FaqPicturePersistence> faqPicturePersistences = FaqPictureHelper.faqPicture(state, num);
		return faqPicturePersistences;
	}

}
