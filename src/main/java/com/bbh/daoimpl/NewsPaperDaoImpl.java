package com.bbh.daoimpl;

import java.util.List;

import javax.persistence.NoResultException;

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

	@Override
	public NewsPaper get(int id) {
		return (NewsPaper) super.get(NewsPaper.class, id);
	}

	@Override
	public NewsPaper getFromName(String name) {
		try {
			String q = "from NewsPaper n where n.name=:name";
			Query query = this.getSessionFactory().openSession().createQuery(q);
			query.setParameter("name", name);
			return (NewsPaper) query.getSingleResult();
		}catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
