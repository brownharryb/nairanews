package com.bbh.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.bbh.dao.NewsPaperDao;
import com.bbh.model.NewsPaper;


@Repository
public class NewsPaperDaoImpl extends BaseDaoImpl implements NewsPaperDao{
	

	@Override
	public void save(NewsPaper newsPaper) {	
		super.save(newsPaper);			
	}

	@Override
	public void delete(int id) {
		super.delete(NewsPaper.class, id);
		
	}

	@Override
	public List<NewsPaper> findAll() {
		return (List<NewsPaper>) super.findAll(NewsPaper.class);
	}
	
}
