package com.library.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.service.LibraryService;

@Controller
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping("/LogoutPage")
	public ModelAndView logoutPageController() {
		return new ModelAndView("LogoutPage");
	}
	
	@RequestMapping("/index")
	public ModelAndView indexPageController() {
		return new ModelAndView("index");
	}
	
	// =================Login Page Controller=======================
	
	@RequestMapping("/")
	public ModelAndView loginPageController() {
		return new ModelAndView("LoginPage", "employee", new Employee());
	}
	
	@RequestMapping("/login")
	public ModelAndView loginController(@ModelAttribute("employee") Employee employee, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		Employee employeeDetails = libraryService.loginCheck(employee.getEmployeeId(), employee.getPassword());
		
		if(employeeDetails!=null) {
			modelAndView.addObject("employee", employeeDetails);  
			session.setAttribute("employee", employeeDetails);  
			modelAndView.setViewName("index");
		}
		else {
			modelAndView.addObject("messageLogin", "Invalid User Credentials, Please try again");
			modelAndView.addObject("employee", new Employee());
			modelAndView.setViewName("LoginPage");
		} 
			
		return modelAndView;
	}
	// =================Catalogue Controller=======================
	
	@RequestMapping("/viewCatalogue")
	public ModelAndView viewCatalogueController() {
		
		ModelAndView modelAndView=new ModelAndView();
		List<Book> libList=libraryService.getBookList();
		
		modelAndView.addObject("libraries", libList);
		modelAndView.setViewName("BookCatalogue");
		return modelAndView;
	
	}
	
	// =================View Borrowed Books booksController=======================
	
	@RequestMapping("/viewBorrowedBooks")
	public ModelAndView viewBorrowedBooksController(HttpSession session) {
			
		ModelAndView modelAndView=new ModelAndView();
			
		Employee employee=(Employee)session.getAttribute("employee");
		List<Library> lib =  libraryService.getLibraryByEmployeeId(employee.getEmployeeId());
			
		if(lib.size() > 0)
			{
			modelAndView.addObject("libraries", lib);
			modelAndView.addObject("employeeId", employee.getEmployeeId());
			modelAndView.addObject("library", new Library());
			modelAndView.setViewName("BorrowedBooks");
		} else {
			modelAndView.addObject("message", "You have no current Borrowed Books");
			modelAndView.setViewName("BorrowedBooks");
			}
		return modelAndView;
		}

	// =================Return Search Books booksController=======================
	
	@RequestMapping("/returnBook")
	public ModelAndView returnBookController(HttpSession session) {
			
	ModelAndView modelAndView = new ModelAndView();
			
	Employee employee = (Employee) session.getAttribute("employee");
			
	List<Library> lib = libraryService.getLibraryByEmployeeId(employee.getEmployeeId());
	if(lib.size()>0) {
		modelAndView.addObject("libraries", lib);
		modelAndView.addObject("employeeId", employee.getEmployeeId());
		modelAndView.setViewName("ReturnBookSearch");
	}else {
		modelAndView.addObject("message", "You have no books to return");
		modelAndView.setViewName("ReturnMessages");
	}
			
		return modelAndView;
	}
    
	// ================= Search Books booksController=======================
	@RequestMapping("/searchReturnBook")
	public ModelAndView searchReturnBookController(@RequestParam("bookType") String type, @RequestParam("issueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issueDate , HttpSession session) {
			
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = (Employee) session.getAttribute("employee");
			
		List<Library> lib = libraryService.getBooksByTypeAndDate(type, issueDate, 0);
		if(lib.size()>0) {
			modelAndView.addObject("libraries", lib);
			modelAndView.addObject("employeeId", employee.getEmployeeId());
			modelAndView.setViewName("ReturnBookSearch");
		}else {
			modelAndView.addObject("message", "You have no books to return with type " + type + " and issue date " + issueDate);
			modelAndView.setViewName("ReturnMessages");
		}
			
			return modelAndView;
		}
		
	
	

	// =================Return Books button Controller=======================
	//ash version
		@RequestMapping("/returningBookButtonFromBorrowedBooks")
		public ModelAndView returnBookButtonFromBorrowedBooksController(@RequestParam("tId") String tId, HttpSession session) {
			ModelAndView modelAndView = new ModelAndView();
//			System.out.println("transaction id :"+tId);
			//unable to get library object as it is just an iterator of the libraries object session
			//Library libraryRet = (Library)session.getAttribute("library"); - not in session so doesnt work
//			System.out.println(library); // for testing

			//this is null because library passed in is null
			Library libraryReturning = libraryService.returnBook(tId, 1);
			Employee employee=(Employee)session.getAttribute("employee");
			List<Library> lib =  libraryService.getLibraryByEmployeeId(employee.getEmployeeId());
//			
//			//emp namp, book type, issue date, return date, late fee
			modelAndView.addObject("message2", "Return Successful!");
			modelAndView.addObject("bookDetails", "Employee name: " + libraryReturning.getEmployeeName() + ", Book Type: " + libraryReturning.getBookType() + ", Issue Date: " + libraryReturning.getIssueDate() + ", Return Date: " + libraryReturning.getReturnDate());
			modelAndView.addObject("latefee", "Late Fee is " + libraryReturning.getLateFee());
			modelAndView.addObject("libraries", lib);		
			modelAndView.setViewName("BorrowedBooks");
			modelAndView.addObject("employeeId", employee.getEmployeeId());


			return modelAndView;
		}
		
		//ash version
		@RequestMapping("/returningBookButtonFromSearchBorrowedBooks")
		public ModelAndView returnBookButtonController(@RequestParam("tId") String tId, HttpSession session) {
			ModelAndView modelAndView = new ModelAndView();
//			System.out.println("transaction id :"+tId);
			//unable to get library object as it is just an iterator of the libraries object session
			//Library libraryRet = (Library)session.getAttribute("library"); - not in session so doesnt work
//			System.out.println(library); // for testing

			//this is null because library passed in is null
			Library libraryReturning = libraryService.returnBook(tId, 1);
			Employee employee=(Employee)session.getAttribute("employee");
			List<Library> lib =  libraryService.getLibraryByEmployeeId(employee.getEmployeeId());
//			
//			//emp namp, book type, issue date, return date, late fee
			modelAndView.addObject("message2", "Return Successful!");
			modelAndView.addObject("bookDetails", "Employee name: " + libraryReturning.getEmployeeName() + ", Book Type: " + libraryReturning.getBookType() + ", Issue Date: " + libraryReturning.getIssueDate() + ", Return Date: " + libraryReturning.getReturnDate());
			modelAndView.addObject("latefee", "Late Fee is " + libraryReturning.getLateFee());
			modelAndView.addObject("libraries", lib);		
			modelAndView.setViewName("ReturnBookSearch");
			modelAndView.addObject("employeeId", employee.getEmployeeId());


			return modelAndView;
		}
		

		// =================Borrowed booksController=======================

		  @RequestMapping("/borrowBooks")
		    public ModelAndView borrowBookController(@RequestParam("bId") int bookId, HttpSession session) {

		        ModelAndView modelAndView=new ModelAndView();

		        Employee employee=(Employee)session.getAttribute("employee");
		        
		       
//		        modelAndView.addObject("books", );

		        Library lib = libraryService.borrowBook(bookId, 1, employee);

		        if(lib != null) {
		            modelAndView.addObject("libraries", lib);
		            modelAndView.addObject("employeeId", employee.getEmployeeId());
		            modelAndView.setViewName("ListOfBooksBorrowed");
		        } else {
		            modelAndView.addObject("message2", "Sorry, this book is not available!");
		            modelAndView.setViewName("ReturnMessages");
		        }

		        return modelAndView;
		    }
}
	


	

