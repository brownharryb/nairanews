package com.bbh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbh.daoimpl.NewsPaperDaoImpl;
import com.bbh.model.NewsPaper;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		
		
		NewsPaper newsPaper = new NewsPaper();
		newsPaper.setName("Vanguard");
		newsPaper.setId(1);
		new NewsPaperDaoImpl().save(newsPaper);
//		
//		List<NewsPaper> newsPapers = new NewsPaperDaoImpl().findAll();	
//		
//		
//		mv.addObject("papers", newsPapers);
//		new NewsPaperDaoImpl().delete(2);
		mv.setViewName("index.jsp");
		return mv;
	}
}
