package com.dj.passwordmanager.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dj.passwordmanager.entity.Password;

public interface PasswordDataRepo extends JpaRepository<Password, Integer>{
	List<Password> findByUserUserId(Integer userId);
}
