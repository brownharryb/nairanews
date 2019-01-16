package com.bbh.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbh.config.SpringRootConfig;
import com.bbh.daoimpl.NewsPaperDaoImpl;
import com.bbh.model.News;
import com.bbh.model.NewsPaper;
import com.bbh.service.NewsService;

@Controller
public class NewsController {
	
	@RequestMapping("/news/{newsPaperId}")
	public ModelAndView news(@PathVariable("newsPaperId") int newsPaperId) {
		NewsPaperDaoImpl paperDao = new NewsPaperDaoImpl();
		
		NewsPaper newsPaper = paperDao.get(newsPaperId);
		if(newsPaper == null) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SpringRootConfig.class);
		context.refresh();
		NewsService service = (NewsService) context.getBean(NewsService.class);
		List<NewsPaper> papers = paperDao.findAll();
		for(NewsPaper paper:papers) {
			if(paper.getName().equals(newsPaper.getName())) {
				List<News> news = service.fetchNewsFromDB(paper);
				mv.addObject("news",news);
				mv.addObject("newsPapers",papers);
				mv.addObject("currentPaper", paper);
				mv.setViewName("/news.jsp");
				return mv;
				
			}
		}
		return null;
	}

}
