package com.laptrinhjavaweb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.laptrinhjavaweb.dto.MyUser;

public class SecurityUtils {
	
	public static MyUser getPrincipal() {
		// getPrincipal() lấy dữ liệu trong Principal.
		MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
		List<String> results = new ArrayList<>();
		// get authorities từ CustomUserDetailsService
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority()); // hàm get chuỗi code ('ADMIN' hoặc 'USER')
        }
		return results;
	}
}
