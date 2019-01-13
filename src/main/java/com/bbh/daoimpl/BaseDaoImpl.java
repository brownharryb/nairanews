package com.bbh.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.bbh.config.SpringRootConfig;


abstract public class BaseDaoImpl {
	
	protected void save(Object obj) {	
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(obj);
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}finally {
			if(session != null) {
				session.close();
			}
		}
			
	}
	
	
	protected void delete(Class<?> c, int id) {	
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Object obj = session.get(c, id);
			if(obj != null) {
				session.delete(obj);
			}
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}finally {
			if(session != null) {
				session.close();
			}
		}
			
	}
	
	protected List<?> findAll(Class<?> c) {
		String q = "from "+c.getSimpleName();
		Query query = this.getSessionFactory().openSession().createQuery(q);
		List<?> l = query.list();
		return l;
		
		
	}
	

	protected SessionFactory getSessionFactory() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SpringRootConfig.class);
		context.refresh();
		return (SessionFactory) context.getBean("sessionFactory");
	}

}
