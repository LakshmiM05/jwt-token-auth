package com.example.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.entity.MyUser;

@Repository
public interface MyRepository extends JpaRepository<MyUser, Long> {
	
	MyUser findByUserName(String userName);

}
