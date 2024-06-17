package com.example.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt.entity.MyUser;
import com.example.jwt.repository.MyRepository;
@Service
public class MyUserDetailService implements UserDetailsService {
@Autowired
private MyRepository myUserRepository;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// TODO Auto-generated method stub
	MyUser  myuser = myUserRepository.findByUserName(username);		
	return User.builder().
			username(myuser.getUserName()).
			password(myuser.getPassword()).
			roles(getRoles(myuser)).
			build();
}

private String[] getRoles(MyUser  myuser) {
	if (myuser.getRole() ==null) {
		return new String[]{"USER"};
	}
	return myuser.getRole().split(",");
	
}
}
