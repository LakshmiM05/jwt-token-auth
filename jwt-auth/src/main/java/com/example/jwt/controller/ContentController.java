package com.example.jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.service.JwtService;
import com.example.jwt.service.MyUserDetailService;

@RestController
public class ContentController {
	
	  @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private JwtService jwtService;
	    @Autowired
	    private MyUserDetailService myUserDetailService;

	
	@GetMapping("/home")
	public String getHomeContent() {
		return "home";
	}
	
	@GetMapping("/admin/home")
	public String getAdminHomeContent() {
		return "admin_home";
	}
	
	@GetMapping("/user/home")
	public String getUserHomeContent() {
		return "user_home1234";
	}
	
	 @PostMapping("/authenticate")
	    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginForm.getUsername(), loginForm.getPassword()
	        ));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.getUsername()));
	        } else {
	            throw new UsernameNotFoundException("Invalid credentials");
	        }
	    }


}
