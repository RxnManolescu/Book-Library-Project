package com.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.library.entity.Book;
import com.library.entity.Library;
import com.library.service.LibraryService;

@Controller
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	
	@RequestMapping("/index")
	public ModelAndView indexPageController() {
		return new ModelAndView("index");
	}
	
	// =================Login Page Controller=======================
	
	@RequestMapping("/")
	public ModelAndView loginPageController() {
		return new ModelAndView("LoginPage", "library", new Library());
	}
	
	@RequestMapping("/login")
	public ModelAndView loginController(@ModelAttribute("library") Library library, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		if(libraryService.loginCheck(library)) {
			modelAndView.addObject("library", library);  
			session.setAttribute("library", library);  
			modelAndView.setViewName("index");
		}
		else {
			modelAndView.addObject("message", "Invalid User Credentials, Please try again");
			modelAndView.addObject("library", new Library());
			modelAndView.setViewName("LoginPage");
		} 
			
		return modelAndView;
	}
	
	
	@RequestMapping("/viewCatalogue")
	public ModelAndView viewCatalogueController() {
		
		ModelAndView modelAndView=new ModelAndView();
		List <Book> libList=libraryService.getBookList();
		
		modelAndView.addObject("libraries", libList);
		modelAndView.setViewName("LibraryCatalogue");
		return modelAndView;
	
	}

}


	



