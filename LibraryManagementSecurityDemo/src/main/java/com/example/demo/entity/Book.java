package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor

public class Book {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookid;
	private String name;
	private String author;
	private String category;
	public Book(String name, String author, String category) {
		super();
		this.name = name;
		this.author = author;
		this.category = category;
	}
	
	
	
}
