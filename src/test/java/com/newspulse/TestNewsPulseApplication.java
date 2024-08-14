package com.newspulse;

import org.springframework.boot.SpringApplication;

public class TestNewsPulseApplication {

	public static void main(String[] args) {
		SpringApplication.from(NewsPulseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
