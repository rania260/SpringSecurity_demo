package com.example.springsecuritydemo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springsecuritydemo.business.iservices.IUserService;
import com.example.springsecuritydemo.dao.entities.User;

@Controller
public class AuthController {

    @Autowired
    private IUserService userService;
    
    
	@GetMapping("/login")
	public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // L'utilisateur est déjà connecté, rediriger vers une autre page
            return "redirect:/access-denied";
        }
        // Sinon, afficher la page de connexion
		return "login";
	}
    // Go to Registration Page
    @GetMapping("/register")
    public String register() {
        return "register-user";
    }

    // Read Form data to save into DB
    @PostMapping("/saveUser")
    public String saveUser(
            @ModelAttribute User user,
            Model model) {
        Integer id = userService.saveUser(user);
        String message = "User '" + id + "' saved successfully !";
        model.addAttribute("msg", message);
        return "register-user";
    }
}