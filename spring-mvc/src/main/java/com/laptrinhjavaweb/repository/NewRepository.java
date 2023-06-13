package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.NewEntity;

// Do đã khai báo thư viện JpaRepository nên không cần viết @Repository trước public interface
// Tham số đầu tiên truyền vào là NewEntity, khai báo kiểu dữ liệu khóa chính là Long (trong database là bigint)
// Do thư viện JpaRepository đã làm sẵn các hàm nên không có @Repository như DAO
// Để sử dụng được các phương thức bên trong thư viện JpaRepository thì phải cấu hình trong JPAconfig
public interface NewRepository extends JpaRepository<NewEntity, Long>{

}
