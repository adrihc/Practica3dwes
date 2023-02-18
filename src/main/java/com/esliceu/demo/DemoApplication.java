package com.esliceu.demo;

import com.esliceu.demo.Interceptors.MyAuthInterceptor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new MyAuthInterceptor())
				.addPathPatterns("/objects")
				.addPathPatterns("/objects/{bucket}")
				.addPathPatterns("/objects/{bucket}/{object}")
				.addPathPatterns("/settings")
				.addPathPatterns("/deleteobj/{bucket}/{object}")
				.addPathPatterns("/download/{objid}/{fid}")
				.addPathPatterns("/deleteuser");
	}
}
