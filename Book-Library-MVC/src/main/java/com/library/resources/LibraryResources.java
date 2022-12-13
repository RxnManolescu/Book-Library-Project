package com.library.resources;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.library.entity.Employee;
import com.library.service.LibraryService;

@RestController
public class LibraryResources {

	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping("/")
	public ModelAndView getLoginPageController() {
		return new ModelAndView("index");
	}

	@RequestMapping("/login")
	public ModelAndView loginController(@RequestParam("employeeId") int id,@RequestParam("pwd") String password,HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		
//		if(employee!=null) {
//			session.setAttribute("employee", employee);
//			modelAndView.setViewName("	");
//		}
//		else {
//			modelAndView.addObject("message", "Login Failed, Please try again");
//			modelAndView.setViewName("index");
//		}

		return modelAndView;
	}
}
