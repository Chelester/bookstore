package com.example.Bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;

@Controller
@ResponseBody
public class BookController {
	@RequestMapping("/index")
	public String index() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("The Hobbit", "J. R. R. Tolkien", 1937, "978-0345339683", 6.14));
		return "List of books" + books;
	}
}