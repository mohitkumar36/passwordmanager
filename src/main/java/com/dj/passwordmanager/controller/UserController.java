package com.dj.passwordmanager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dj.passwordmanager.entity.User;
import com.dj.passwordmanager.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

@RestController
public class UserController {
	
	
	@Autowired
	private SecurityContextLogoutHandler logoutHandler;
	
	
    @Autowired
    private UserService service;
	
	@GetMapping("")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
     
     
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
     
    @GetMapping("/register")
    public ModelAndView userRegistration() {
        return new ModelAndView("register");
    }
 
     
    @PostMapping("/process-registration")
    public ModelAndView processRegister(User user, HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView("verifyregister");
    	try {
	        service.register(user, getSiteURL(request));   
	        mv.addObject("line1", "You have signed up successfully!");
	        mv.addObject("line2", "Please check your email to verify your account.");
    	}
    	catch(Exception e) {
    		mv.addObject("line1", "Email Already exists");
    	}
        return mv;
    }
     
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    
    @GetMapping("/verify")
    public ModelAndView verifyUser(@PathParam("code") String code) {
    	ModelAndView mv = new ModelAndView("verifyregister");
        if (service.verify(code)) {
            mv.addObject("line1", "Congratulations, your account has been verified.");
        } else {
            mv.addObject("line1", "Sorry, we could not verify account. "
            		+ "It maybe already verified or verification code is incorrect.");
        }
        return mv;
    }
    
    @GetMapping("/resetpassword")
    public ModelAndView resetPassword() {
    	return new ModelAndView("resetpassword");
    }
    
    @PostMapping("/resetpassword")
    public ModelAndView sendResetLink(HttpServletRequest request) {
    	String email = request.getParameter("email");
    	ModelAndView mv = new ModelAndView("verifyregister");
    	try {
			service.resetPasswordLink(email, getSiteURL(request));
			mv.addObject("line1", "Password reset link is sent.");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("line1", "No account found!");
		}
        return mv;
    }
    
//    "/newpassword"
    @GetMapping("/newpassword")
    public ModelAndView newPassword(@PathParam("code") String code) {
    	ModelAndView mv = new ModelAndView("newpassword");
        mv.addObject("code", code);
        return mv;
    }
    
    @PostMapping("/newpassword")
    public RedirectView saveNewPassword(HttpServletRequest request) {
    	String newPassword = request.getParameter("password");
    	String code = request.getParameter("code");
    	
    	try {
			service.setPassword(code, newPassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return new RedirectView("/login");
    }
    
     
//    @PostMapping("/process-registration")
//    public RedirectView processRegistration(@ModelAttribute User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//         
//        userRepo.save(user);
//         
//        return new RedirectView("/login");
//    }
    
    @GetMapping("/logout")
    public RedirectView performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        // .. perform logout
        logoutHandler.logout(request, response, authentication);
        return new RedirectView("/");
    }
}
