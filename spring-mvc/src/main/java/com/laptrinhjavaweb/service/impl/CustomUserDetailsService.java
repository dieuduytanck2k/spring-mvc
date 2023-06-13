package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;

@Service // khai báo service để sử dụng cơ chế Ioc và dependency
public class CustomUserDetailsService implements UserDetailsService{
	
	// Dùng Autowired để nhúng UserRepository vào
	@Autowired
	private UserRepository userRepository;
	
	// load user theo tên từ form security.xml
	// phần password đã được xử lí ngầm trong form security.xml
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		
		// Nếu user không tồn tại thì nhảy vào authentication-failure-url trong file security.xml
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		//Put thông tin user vào authorities 
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode())); // getCode là 'ADMIN', 'USER' trong sql
		}
		// MyUser đã custom trong file dto
		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true, true, authorities); // put data vào user
		myUser.setFullName(userEntity.getFullName());
		return myUser; // trả ra bằng lớp con được vì dư các trường dữ liệu không sao
	}

}
