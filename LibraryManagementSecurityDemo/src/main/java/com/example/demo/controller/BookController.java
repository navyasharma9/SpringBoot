package com.example.demo.controller;

import java.security.Principal;
//import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	public BookController() {
		System.out.println("book controller");
	}
	@Autowired
	private BookService bookService;
	@GetMapping("/list")
	public String getAllBooks(Map<String, List<Book>> map)
	{
		System.out.println("get books");
		List<Book> books = this.bookService.findAll();
		System.out.println(books);
		map.put("Books", books);
		return "booklist";
	}
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Book theBook = new Book();

		theModel.addAttribute("Book", theBook);

		return "bookaddform";
	}

	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theId,
			Model theModel) {

		// get the Book from the service
		Book theBook = bookService.findById(theId);


		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Book", theBook);

		// send over to our form
		return "bookaddform";			
	}


	@RequestMapping("/save")
	public String saveBook(@RequestParam("bookid") int bookid,
			@RequestParam("name") String name,
			@RequestParam("category") String category,@RequestParam("author") String author) {

		System.out.println(bookid);
		Book theBook;
		if(bookid!=0)
		{
			theBook=bookService.findById(bookid);
			theBook.setName(name);
			theBook.setCategory(category);
			theBook.setAuthor(author);
		}
		else
			theBook=new Book(name, category, author);
		// save the Book
		bookService.save(theBook);

		// use a redirect to prevent duplicate submissions
		return "redirect:books";

	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("bookId") int theId) {

		// delete the Book
		bookService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:books";

	}


	@RequestMapping("/search")
	public String search(@RequestParam("name") String name,
			@RequestParam("author") String author,
			Model theModel) {

		// check names, if both are empty then just give list of all Books

		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			return "redirect:books/list";
		}
		else {
			// else, search by first name and last name
			List<Book> theBooks =
					bookService.searchBy(name, author);

			// add to the spring model
			theModel.addAttribute("Books", theBooks);

			// send to list-Books
			return "booklist";
		}

	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		System.out.println(user);
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
