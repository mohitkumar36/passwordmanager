package com.dj.passwordmanager.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dj.passwordmanager.entity.User;
import com.dj.passwordmanager.jpa.UserDataRepo;

import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	@Autowired
	private UserDataRepo userRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private JavaMailSender mailSender;
    
    public void register(User user, String siteURL) throws Exception {
    	String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
         
        userRepo.save(user);
         
        sendVerificationEmail(user, siteURL);
    }
    
     
    private void sendVerificationEmail(User user, String siteURL) throws Exception{
    	String toAddress = user.getEmail();
        String fromAddress = "springboottest69@gmail.com";
        String senderName = "DJ";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "DJ.";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
         
        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
         
        content = content.replace("[[URL]]", verifyURL);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }
    
    public void resetPasswordLink(String email, String siteURL) throws Exception {
    	String randomCode = RandomString.make(64);
    	User user = userRepo.findByEmail(email);
    	if (user.isEnabled() == false) {
    		throw new IOException("User needs to be verified to reset password");   
    	}
    	user.setPasswordResetCode(randomCode);
    	
    	userRepo.save(user);
    	
    	sendResetEmail(user, siteURL);
    }
    
    private void sendResetEmail(User user, String siteURL) throws Exception{
    	String toAddress = user.getEmail();
        String fromAddress = "springboottest69@gmail.com";
        String senderName = "DJ";
        String subject = "Reset Password";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to reset your password:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">RESET</a></h3>"
                + "Thank you,<br>"
                + "DJ.";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
         
        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/newpassword?code=" + user.getPasswordResetCode();
         
        content = content.replace("[[URL]]", verifyURL);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }
    
    public void setPassword(String code, String password) throws IOException {
    	if (code == null) {
    		throw new IOException("Something went wrong"); 
    	}
    	User user = userRepo.findByPasswordResetCode(code);
    	
    	String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setPasswordResetCode(null);
         
        userRepo.save(user);
    	
    }
    
    public boolean verify(String verificationCode) {
        User user = userRepo.findByVerificationCode(verificationCode);
         
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepo.save(user);
             
            return true;
        }
         
    }
}
