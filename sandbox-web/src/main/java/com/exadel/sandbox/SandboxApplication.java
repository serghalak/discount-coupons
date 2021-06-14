package com.exadel.sandbox;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper=new ModelMapper();
        return mapper;
    }
}
