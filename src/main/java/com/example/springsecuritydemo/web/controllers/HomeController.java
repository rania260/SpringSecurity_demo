package com.example.springsecuritydemo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/home", "/" })
	public String getHomePage() {
		return "home";
	}




	@GetMapping("/admin")
	public String getAdminPage() {
		return "admin-page";
	}

	@GetMapping("/moderator")
	public String getModeratorPage() {
		return "moderator-page";
	}

	@GetMapping("/user")
	public String getUserPage() {
		return "user-page";
	}

	@GetMapping("/common")
	public String getCommonPage() {
		return "common-page";
	}

	@GetMapping("/access-denied")
	public String getAccessDeniedPage() {
		return "access-denied-page";
	}
}
