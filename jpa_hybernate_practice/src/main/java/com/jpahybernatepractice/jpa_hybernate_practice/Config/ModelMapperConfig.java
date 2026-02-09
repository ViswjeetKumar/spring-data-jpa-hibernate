package com.jpahybernatepractice.jpa_hybernate_practice.Config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
public ModelMapper modelMapper(){return new ModelMapper();}
}
