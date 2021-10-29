package com.acetex.acetexdasboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

import static com.acetex.acetexdasboard.Constants.FileConstant.USER_FOLDER;

@SpringBootApplication
public class AcetexDasboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcetexDasboardApplication.class, args);
		new File(USER_FOLDER).mkdirs();
	}

	//TODO - CLI Insert Admin Account at startup

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
