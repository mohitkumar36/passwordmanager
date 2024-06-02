package com.dj.passwordmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String username;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	@Column(name = "verification_code", length = 64)
    private String verificationCode;
    
	@Column(name = "password_reset_code", length = 64)
    private String passwordResetCode;
	
    private boolean enabled;
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Password> passwords;
}
