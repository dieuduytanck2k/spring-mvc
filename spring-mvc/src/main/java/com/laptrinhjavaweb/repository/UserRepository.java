package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.UserEntity;
//JpaRepository đã có chức năng 
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	// Spring data JPA quy định findOne trả về single, find thì trả về list
	UserEntity findOneByUserNameAndStatus(String name, int status);  
}
