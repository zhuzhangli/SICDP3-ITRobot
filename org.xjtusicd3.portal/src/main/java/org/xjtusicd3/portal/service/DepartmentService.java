package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.DepartmentHelper;
import org.xjtusicd3.database.model.DepartmentPersistence;
import org.xjtusicd3.portal.view.DepConfigureView;


public class DepartmentService {
	//获取所有部门信息
	public static List<DepConfigureView> getAllDepartment() {
		List<DepConfigureView> depViews = new ArrayList<DepConfigureView>();
		
		//获取所有部门信息
		List<DepartmentPersistence> departmentPersistences = DepartmentHelper.getAllDepartment();
		
		for(DepartmentPersistence dep:departmentPersistences){
			DepConfigureView depView = new DepConfigureView();
			
			depView.setDEPARTMENTID(dep.getDEPARTMENTID());
			depView.setDEPARTMENTNAME(dep.getDEPARTMENTNAME());
			
			depViews.add(depView);
		}
		return depViews;
	}

}
