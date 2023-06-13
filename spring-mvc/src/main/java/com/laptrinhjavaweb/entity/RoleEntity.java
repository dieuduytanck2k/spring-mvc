package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//Định nghĩa entity và table là tạo tự động bảng new
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
	
	// Các column là các cột trong bảng table
	@Column(name = "name") // Đặt tên các cột trong MySQL
	private String name;
	
	@Column(name = "code")
	private String code;
	
	//ManytoMany làm hết các vấn đề innerJoin giữa 2 bảng
	@ManyToMany(mappedBy = "roles") // List bên UserEntity đã định nghĩa
    private List<UserEntity> users = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
}
