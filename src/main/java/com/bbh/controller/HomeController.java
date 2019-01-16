package com.bbh.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbh.daoimpl.NewsDaoImpl;
import com.bbh.model.NewsPaper;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		NewsPaper paper = new NewsPaper();
		paper.setName("Vanguard");
		paper.setUrl("https://www.vanguardngr.com/category/technology/");
//		NewsPaperDaoImpl newsPaperDao = new NewsPaperDaoImpl();
//		newsPaperDao.save(paper);
		
		
		NewsDaoImpl newsDao = new NewsDaoImpl();		
		newsDao.fetchNewsFromInternet(paper);
		
		
		mv.setViewName("index.jsp");
		return mv;
	}
}
