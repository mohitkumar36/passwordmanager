package com.dj.passwordmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dj.passwordmanager.entity.Password;
import com.dj.passwordmanager.jpa.PasswordDataRepo;
import com.dj.passwordmanager.jpa.UserDataRepo;
import com.dj.passwordmanager.security.AES;
import com.dj.passwordmanager.security.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@RestController
public class ManagerController {
	
	@Autowired
	private PasswordDataRepo passwordRepo;
	
	@Autowired
	private UserDataRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/home")
	public ModelAndView home(Authentication authetication) {
		ModelAndView mv = new ModelAndView("home");
		CustomUserDetails user = (CustomUserDetails) authetication.getPrincipal();
		List<Password> allPass = passwordRepo.findByUserUserId(user.getUserId());
		mv.addObject("passwords", allPass);
		return mv;
	}
	
	@GetMapping("/addpassword")
	public ModelAndView addPassword() {
		return new ModelAndView("addpassword");
	}
	
	@PostMapping("/addpassword")
	public RedirectView submitPassword(Authentication authetication, @ModelAttribute Password password, HttpServletRequest request) throws Exception {
		CustomUserDetails user = (CustomUserDetails) authetication.getPrincipal();
		String masterpass = request.getParameter("masterpass");
		if (!passwordEncoder.matches(masterpass, user.getPassword()))
			return new RedirectView("/wrong?message=Wrong masterpassword");
		
		password.setUser(userRepo.getReferenceById(user.getUserId()));
		password.setPass(AES.encrypt(password.getPass(), masterpass));
		passwordRepo.save(password);
		return new RedirectView("/home");
	}
	
	@PostMapping("/copytoclip")
	@ResponseBody
	public String copyToClip(HttpServletRequest request) {
		int passwordId = Integer.parseInt(request.getParameter("passwordId"));
		String masterPassword = request.getParameter("password");
//		System.err.println(masterPassword + " " + passwordId);
		String decryptedPassword = "";
		try{
			decryptedPassword = AES.decrypt(passwordRepo.getReferenceById(passwordId).getPass(), masterPassword);
		}
		catch(Exception e) {
			decryptedPassword = "Wrong MasterPassword";
		}
//		System.out.println(decryptedPassword);
		return decryptedPassword;
	}
	
	@GetMapping("/update")
	public ModelAndView update(@PathParam("passwordId") int passwordId) {
		ModelAndView mv = new ModelAndView("addpassword");
		mv.addObject("password", passwordRepo.getReferenceById(passwordId));
		return mv;
	}
	
	@GetMapping("/delete")
	public RedirectView delete(@PathParam("passwordId") int passwordId) {
		passwordRepo.deleteById(passwordId);
		return new RedirectView("home");
	}
	@GetMapping("/wrong")
	public String error(@PathParam("message") String message) {
		return message;
	}
	
}
