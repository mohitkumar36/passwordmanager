package com.dj.passwordmanager.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
public class WebSecurityConfig {
	@Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
 
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityContextLogoutHandler securityContextLogoutHandler() {
    	return new SecurityContextLogoutHandler();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
         
        http.authenticationProvider(authenticationProvider());
         
        http.authorizeHttpRequests(auth ->
            auth.requestMatchers("/home", "/addpassword", "/update", "/copytoclip").authenticated()
            .anyRequest().permitAll()
            )
            .formLogin(login ->
            login
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .usernameParameter("email")
            .defaultSuccessUrl("/home")
//            .failureHandler(authenticationFailureHandler())
            .permitAll())
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID").logoutSuccessUrl("/"));
         
        return http.build();
    }  
    
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
