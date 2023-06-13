package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	//@ModelAttribute có chức năng scan các paramter tương ứng vào model tương ứng
	// dependency injection các controller muốn dùng service này đều được
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	// RequestMapping là nơi nhận các uml vào.
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, //page là số trang, limit là giới hạn số trang trong 1 page
								 @RequestParam("limit") int limit, HttpServletRequest request) {
		// Trong file dispatcher-servlet.xml đã định nghĩa
		// file có đuôi jsp thông qua đối tượng hỗ trợ tìm file ViewResolver
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/new/list");
		Pageable pageable = new PageRequest(page - 1, limit); // trong sql phần tử đầu tiên là 0 nên cần trừ 1, limit là số phần tử giới hạn lấy
		model.setListResult(newService.findAll(pageable)); // đưa danh sách theo cầu từ form vào dto
		model.setTotalItem(newService.getTotalItem()); // lấy tổng số lượng bài viết
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit())); // số page
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model); // đẩy data từ controller ra view
		return mav;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) { // với required = false có id thì lấy, không có id thì không lấy
		// Trong file dispatcher-servlet.xml đã định nghĩa
		// file có đuôi jsp thông qua đối tượng hỗ trợ tìm file ViewResolver
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO(); 
		if (id != null) {
			model = newService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
}