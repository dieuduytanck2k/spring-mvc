package com.laptrinhjavaweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// Controller chuyển file java thông thường sang thành controller của mô hình spring mvc
@Controller(value = "homeControllerOfWeb")
public class HomeController {
	// RequestMapping là nơi nhận các uml vào.
	// Lấy data từ database dùng method get.
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	// Trả về model và view
	public ModelAndView homePage() {
		// Trong file dispatcher-servlet.xml đã định nghĩa
		// file có đuôi jsp thông qua đối tượng hỗ trợ tìm file ViewResolver
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	// Khi người dùng nhấn nút thoát thì controller sẽ chuyển hướng về trang chủ
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		// đoạn bên dưới tương đương isAuthenticated() bên header.jsp của phần web 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// logout đã thực hiện chức năng tương đương remove session trong JSP Servlet
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
	
}