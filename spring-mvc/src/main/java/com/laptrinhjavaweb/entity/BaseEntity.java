package com.laptrinhjavaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass //định nghĩa baseEntity là parent Entity spring data JPA hỗ trợ
@EntityListeners(AuditingEntityListener.class) // khai báo để sử dụng JpaAuditing
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng (auto increment) vì vậy nên không cần hàm setId
	private Long id;
	
	//Các annotation như @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy được JPA Auditing hỗ trợ
	// Auditing tự động lấy thông tin username,... trong spring security gắn vào các cột @CreatedDate,...
	@Column(name = "createddate")
	@CreatedDate // khai báo ngày tạo
	private Date createdDate;
	
	@Column(name = "modifieddate")
	@LastModifiedDate // khai báo ngày sửa
	private Date modifiedDate;
	
	@Column(name = "createdby")
	@CreatedBy // khai báo ai là người tạo
	private String createdBy;
	
	@Column(name = "modifiedby")
	@LastModifiedBy // khai báo ai là người sửa
	private String modifiedBy;
	
// Vì giá trị đã được tự động làm nên ko cần setter nữa
	public Long getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

}
