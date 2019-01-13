package com.bbh.dao;

import java.util.List;

import com.bbh.model.NewsPaper;

public interface NewsPaperDao {
	
	public void save(NewsPaper newsPaper);
	public void delete(int id);
	public List<NewsPaper> findAll();

}
