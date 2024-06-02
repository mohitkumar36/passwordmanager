package com.dj.passwordmanager.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dj.passwordmanager.entity.User;

public interface UserDataRepo extends JpaRepository<User, Integer>{

	User findByEmail(String username);
	User findByVerificationCode(String code);
	User findByPasswordResetCode(String code);

}
