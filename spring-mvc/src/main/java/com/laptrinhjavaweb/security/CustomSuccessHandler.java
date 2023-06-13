package com.laptrinhjavaweb.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.util.SecurityUtils;

// Authentication thành công nên được chuyển sang đây(đã cấu hình trong file security.xml)
// (Authorization) CustomSuccessHandler sẽ xử lí authorization(phân quyền:admin, user)
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication); // determineTargetUrl định tuyến trang url trả về
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	private String determineTargetUrl(Authentication authentication) {
		String url = "";
		// nếu role là admin thì redirect /quan-tri/trang-chu
		// nếu role là user thì redirect /trang-chu
		List<String> roles = SecurityUtils.getAuthorities();
		if(isAdmin(roles)) {
			url = "/quan-tri/trang-chu";
		}else if(isUser(roles)) {
			url = "/trang-chu";
		}
		return url;
	}
	
	private boolean isAdmin(List<String> roles) {
		if(roles.contains("ADMIN")) {
			return true;
		}
		return false;
	}
	
	private boolean isUser(List<String> roles) {
		if(roles.contains("USER")) {
			return true;
		}
		return false;
	}
}
