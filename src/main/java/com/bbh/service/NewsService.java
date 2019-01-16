package com.bbh.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bbh.daoimpl.NewsDaoImpl;
import com.bbh.daoimpl.NewsPaperDaoImpl;
import com.bbh.model.News;
import com.bbh.model.NewsPaper;

@Service
public class NewsService {
	
	
	public List<News> fetchNewsFromDB(NewsPaper paper) {
//		NewsPaper paper = new NewsPaperDaoImpl().getFromName(newsPaper);
		if(paper == null) {
			return null;
		}
		NewsDaoImpl ndao = new NewsDaoImpl();
		List<News> todaysNews = ndao.findAllForToday(paper);
		List<News> allNews = ndao.findAllFromNewsPaper(paper);
//		System.out.println("bomsy boma");
//		System.out.println(todaysNews);
//		System.out.println(allNews);
		if(todaysNews.isEmpty() || allNews.isEmpty()) {
			ndao.fetchNewsFromInternet(paper);
			allNews = ndao.findAllFromNewsPaper(paper);
			todaysNews = ndao.findAllForToday(paper);
		}
		return allNews;
	}
	
	
	public List<News> fetchNewsFromNet(NewsPaper newsPaper) {
		if(newsPaper == null) {
			return null;
		}
		switch(newsPaper.getName()) {
		case "Vanguard":
			return this.fetchVanguardNews(newsPaper);
		case "Punch":
			return this.fetchPunchNews(newsPaper);
		default:
			return null;
		}
	}
	
	
	public List<News> fetchNewsFromNet() {
		return this.fetchNewsFromNet(null);
	}
	
	private List<News> fetchVanguardNews(NewsPaper newsPaper) {
		List<News> newsList = new ArrayList<News>();
		try {
			Document doc = Jsoup.connect(newsPaper.getUrl()).get();
//			String title = doc.title();
			Elements newsArticles = doc.select("article");
			for(Element article:newsArticles) {
				News news = new News();
				SimpleDateFormat ft = new SimpleDateFormat ("MMMM dd, yyyy"); 
				news.setHeadline(article.select("header").select("h2").select("a").text());
				news.setSourceUrl(article.select("header").select("h2").select("a").attr("href"));
				news.setAuthor(article.select("header").select(".entry-meta").select("meta[itemprop=author]").attr("content"));
				news.setContent(article.select(".entry-content").select("p").text());
				news.setImageUrl(article.select(".rtp-thumb").select("img").attr("data-lazy-src"));
				news.setNewsHash(article.select("header").select("h2").select("a").attr("href").hashCode());
				news.setNewsPaper(newsPaper);
				try {
					news.setDate(ft.parse(article.select("header").select(".entry-meta").select("meta[itemprop=dateModified]").attr("content")));
				}catch(ParseException e) {
					e.printStackTrace();
				}				
				newsList.add(news);				
			}
			
			return newsList;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<News> fetchPunchNews(NewsPaper newsPaper) {
		List<News> newsList = new ArrayList<News>();
		try {
			Document doc = Jsoup.connect(newsPaper.getUrl()).get();
//			String title = doc.title();
			Elements newsArticles = doc.select(".sub-section-wrapper .items");
			for(Element article:newsArticles) {
				News news = new News();
				SimpleDateFormat ft = new SimpleDateFormat ("MMMM dd yyyy"); 
				news.setHeadline(article.select(".seg-title").text());
				news.setSourceUrl(article.select("a").attr("href"));
//				news.setAuthor(article.select("header").select(".entry-meta").select("meta[itemprop=author]").attr("content"));
				news.setContent(article.select(".seg-summary").text());
				news.setImageUrl(article.select("figure").attr("data-src"));
				news.setNewsHash(article.select("a").attr("href").hashCode());
				news.setNewsPaper(newsPaper);
//				Date
				String original = article.select(".seg-time span.pull-right").text();
				int comma = original.lastIndexOf(',');
				String stringDate = original.substring(0, comma - 2) + original.substring(comma + 1);
				try {
					news.setDate(ft.parse(stringDate));
				}catch(ParseException e) {
					e.printStackTrace();
				}
				newsList.add(news);				
			}
			
			
			
			return newsList;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
