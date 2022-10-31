package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

	public List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);
}
