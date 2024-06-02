package com.dj.passwordmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="passwords")
public class Password {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer passwordId;
	
	private String websitename;
	private String url;
	private String username;
	private String email;
	private String pass;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
