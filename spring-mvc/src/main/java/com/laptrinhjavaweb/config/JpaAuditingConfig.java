package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration // Bật tính năng java config trong spring
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") // String security có nhiệm vụ chứa thông tin, nhiệm vụ của Auditing là vào get thông tin ra
public class JpaAuditingConfig {
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	public static class AuditorAwareImpl implements AuditorAware<String>{

		@Override // nơi get (create date, create by, modifiedby) ra
		public String getCurrentAuditor() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // vào database lấy thông tin
			if(authentication == null) { // trong trường hợp chờ đợi hoặc không có thông tin trong database
				return null;
			}
			return authentication.getName(); // lấy tên ra(user)
		}
	}
}
