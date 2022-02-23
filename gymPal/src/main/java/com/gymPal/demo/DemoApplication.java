package com.gymPal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run (UserService userService){
//		return args -> {
//			userService.saveRole(new Role("ROLE_USER"));
//			userService.saveRole(new Role("ROLE_MANAGER"));
//			userService.saveRole(new Role("ROLE_ADMIN"));
//			userService.saveRole(new Role("ROLE_SUPER_ADMIN"));
//
//		};
//	}

}
