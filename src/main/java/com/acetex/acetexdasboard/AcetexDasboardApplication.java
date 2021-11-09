package com.acetex.acetexdasboard;

import com.acetex.acetexdasboard.Model.User;
import com.acetex.acetexdasboard.Repository.UserRepository;
import com.acetex.acetexdasboard.Resource.UserResource;
import com.acetex.acetexdasboard.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.File;
import java.util.Arrays;

import static com.acetex.acetexdasboard.Constants.FileConstant.USER_FOLDER;

@SpringBootApplication
public class AcetexDasboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcetexDasboardApplication.class, args);
		new File(USER_FOLDER).mkdirs();
	}

	//TODO - CLI Insert Admin Account at startup



	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.addNewUser("Acetex","Admin","AcetexAdmin","email@email.cz","ROLE_SUPER_ADMIN",true,true,null);
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setExposedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
