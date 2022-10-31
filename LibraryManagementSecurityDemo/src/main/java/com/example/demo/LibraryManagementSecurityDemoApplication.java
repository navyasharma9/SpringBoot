package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;

@SpringBootApplication(scanBasePackages={"com.example.demo.controller","com.example.demo.entity","com.example.demo.repo","com.example.demo.service"})
public class LibraryManagementSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSecurityDemoApplication.class, args);
	}
	
	@Autowired
	private BookRepo repo;
	
	@Bean
	public void initialize()
	{
		Book b1 = new Book();
		b1.setAuthor("Java");
		b1.setName("Java");
		b1.setCategory("Programming");
		Book b2 = new Book();
		b2.setAuthor("Nimona");
		b2.setName("Noelle Stevenson");
		b2.setCategory("Comic");
		Book b3 = new Book();
		b3.setAuthor("Python");
		b3.setName("Python");
		b3.setCategory("Programming");
		repo.save(b1);
		repo.save(b2);
		repo.save(b3);
	}

}
