package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//Định nghĩa entity và table là tạo tự động bảng new
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	// Các column là các cột trong bảng table
	@Column(name = "username") // name dùng để đặt tên các cột trong MySQL
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullName;

	@Column
	private Integer status;

	// tạo bảng trung gian user_role
	// đối với EAGER thì khi load entity lên roles sẽ load và làm giảm performance hệ thống
	// đối với lazy thì khi load entity thì roles sẽ chưa load
	// để dùng lazy thì cần phải cấu hình trong JPAConfig
	@ManyToMany(fetch = FetchType.LAZY) // nếu không khai báo fetch thì mặc định vẫn là LAZY
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), // Lưu ý nếu để ở UserEntity thì phải để userid trong joinColumns
								  inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<RoleEntity> roles = new ArrayList<>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

}
