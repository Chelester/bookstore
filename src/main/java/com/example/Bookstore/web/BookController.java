package com.example.Bookstore.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value={"/", "booklist"})
	public String Booklist(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/add")
	public String addbook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String Editbook(@PathVariable("id")long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId).get());
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String saveEdit(@PathVariable("id")long id, Book book, Model model) {
		book.setId(id);
		brepository.save(book);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String DeleteBook(@PathVariable("id")long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
}