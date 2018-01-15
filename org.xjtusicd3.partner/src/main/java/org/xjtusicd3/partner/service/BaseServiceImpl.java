package org.xjtusicd3.partner.service;

import java.io.Serializable;
import java.util.List;

import org.xjtusicd3.database.logic.IBaseDao;

public class BaseServiceImpl<T> implements BaseService<T> {
	private IBaseDao<T,String> baseDao;
	
	 public void setBaseDao(IBaseDao<T,String> baseDao) {
	        this.baseDao = baseDao;
	    }
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		baseDao.delete(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.findByPk(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return baseDao.getAll();
	}

	

}
