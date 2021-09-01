package org.generation.demodb;

import org.generation.demodb.jwt.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemodbApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemodbApplication.class, args);
	}//main

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> registrationBean =
				new FilterRegistrationBean<>();
		registrationBean.setFilter( new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	} // jwtFilter

}//class
