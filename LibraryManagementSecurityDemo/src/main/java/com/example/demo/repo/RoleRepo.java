package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Role;

public interface RoleRepo extends CrudRepository<Role, Integer>{

}
