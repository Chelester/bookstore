package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstore(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of categories");
			crepository.save(new Category("Category 1"));
			crepository.save(new Category("Category 2"));
			crepository.save(new Category("Category 3"));
			
			log.info("save a couple of books");
			brepository.save(new Book("Ensimmäinen", "author", "isbn 12312-3123", 2011, 10.0, crepository.findByName("Category 1").get(0)));
			brepository.save(new Book("Toinen kirja", "author", "isbn 1234-1234-23", 2012, 15.5, crepository.findByName("Category 2").get(0)));
			brepository.save(new Book("Kolmas kirja", "author", "isbn 123-123-123", 2013, 9.9, crepository.findByName("Category 3").get(0)));
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
