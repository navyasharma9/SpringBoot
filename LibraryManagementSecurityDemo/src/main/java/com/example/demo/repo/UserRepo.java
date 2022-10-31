package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

public interface UserRepo extends CrudRepository<User, Long>{

	public User findByUsername(String username);
}
