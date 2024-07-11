package com.redbus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RedbusApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedbusApplication.class, args);
	}

    @Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

}
