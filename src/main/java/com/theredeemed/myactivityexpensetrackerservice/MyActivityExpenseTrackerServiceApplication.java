package com.theredeemed.myactivityexpensetrackerservice;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class MyActivityExpenseTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyActivityExpenseTrackerServiceApplication.class, args);
	}

}
