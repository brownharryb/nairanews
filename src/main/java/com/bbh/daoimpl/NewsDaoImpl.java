package com.bbh.daoimpl;

import java.util.Date;
import java.sql.Time;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bbh.config.SpringRootConfig;
import com.bbh.dao.NewsDao;
import com.bbh.model.News;
import com.bbh.model.NewsPaper;
import com.bbh.service.NewsService;

public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {

	@Override
	public void save(News news) {
		super.save(news);	
		
	}

	@Override
	public void delete(int id) {
		super.delete(News.class, id);
	}

	@Override
	public List<News> findAll() {
		return (List<News>) super.findAll(News.class);
	}

	@Override
	public List<News> findAllFromNewsPaper(NewsPaper newsPaper) {
		String q = "from News as news where news.newsPaper = :newsPaper";
		Query query = this.getSessionFactory().openSession().createQuery(q);
		query.setParameter("newsPaper", newsPaper);
		List<News> l = query.list();
		return l;
	}
	
	@Override
	public List<News> findAllForToday(NewsPaper newsPaper) {
		Date today = new Date();		
		String q = "from News where date = :date";
		Query query = this.getSessionFactory().openSession().createQuery(q);
		query.setParameter("date",today);
		List<News> l = query.list();
		return l;
	}
	
	
	public void fetchNewsFromInternet(NewsPaper newsPaper) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SpringRootConfig.class);
		context.refresh();
		NewsService service = context.getBean(NewsService.class);		
		List<News> newsList = service.fetchNewsFromNet(newsPaper);
		if(newsList != null) {
			for(News news: newsList) {
				this.save(news);
			}
		}
	}	

}
