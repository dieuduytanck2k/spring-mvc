package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Định nghĩa entity và table là tạo tự động bảng new
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

	// Các column là các cột trong bảng table
	@Column(name = "name") // Đặt tên các cột trong MySQL
	private String name;

	@Column(name = "code")
	private String code;

	@OneToMany(mappedBy = "category") // cần khai báo tên chính xác với tên biến của thuộc tính tạo foreign key
	private List<NewEntity> news = new ArrayList<>();

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

	public List<NewEntity> getNews() {
		return news;
	}

	public void setNews(List<NewEntity> news) {
		this.news = news;
	}

}
