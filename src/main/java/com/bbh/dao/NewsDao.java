package com.bbh.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.bbh.model.News;
import com.bbh.model.NewsPaper;

public interface NewsDao {
	public void save(News news);
	public void delete(int id);
	public List<News> findAll();
	public List<News> findAllFromNewsPaper(NewsPaper newsPaperName);
	public List<News> findAllForToday(NewsPaper newsPaper);
}
