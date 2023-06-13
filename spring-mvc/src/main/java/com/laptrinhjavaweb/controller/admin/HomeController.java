package com.laptrinhjavaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// Controller chuyển file java thông thường sang thành controller của mô hình spring mvc
@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	// RequestMapping là nơi nhận các uml vào.
	// Lấy data từ database dùng method get.
	@RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
		//Trả về model và view 
	   public ModelAndView homePage() {
			//Trong file dispatcher-servlet.xml đã định nghĩa 
			//file có đuôi jsp thông qua đối tượng hỗ trợ tìm file ViewResolver
	      ModelAndView mav = new ModelAndView("admin/home");
	      return mav;
	   }
}