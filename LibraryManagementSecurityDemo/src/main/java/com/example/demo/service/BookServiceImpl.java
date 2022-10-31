package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepo repo;
	
	@Override
	public List<Book> findAll() {
		return repo.findAll();
	}

	@Override
	public Book findById(int theId) {
		return repo.findById(theId).get();
	}

	@Override
	public void save(Book theBook) {
		repo.save(theBook);
		
	}

	@Override
	public void deleteById(int theId) {
		repo.deleteById(theId);
		
	}

	@Override
	public List<Book> searchBy(String name, String author) {
		// TODO Auto-generated method stub
		return repo.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
	}

}
