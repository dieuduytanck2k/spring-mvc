package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {
	
	@Autowired
	private INewService newService;

	@PostMapping("/api/new") // trả ra newDTO để khi cần dùng đến, load lại trang,...
	public NewDTO createNew(@RequestBody NewDTO newDTO) { // @RequestBody nhận chuỗi json và đổ dữ liệu vào DTO
		return newService.save(newDTO); 
	}
	
	@PutMapping("/api/new") // trả ra newDTO để khi cần dùng đến, load lại trang,...
	public NewDTO updateNew(@RequestBody NewDTO updateNew) { 
		return newService.save(updateNew);  // return 1 chuỗi json 
	}
	
	@DeleteMapping("/api/new") // phương thức delete
	public void deleteNew(@RequestBody long[] ids) { // xóa nhiều id cùng 1 lúc (dựa vào id để xóa nhiều bài viết cùng một lúc)
		newService.delete(ids);
	}
}
