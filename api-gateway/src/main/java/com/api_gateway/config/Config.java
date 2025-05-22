//package com.api_gateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//public class Config {
//
//    private final CustomFilter customFilter;
//
//    @Autowired
//    public Config(CustomFilter customFilter) {
//        this.customFilter = customFilter;
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.authorizeHttpRequests((auh)->{
//            auh.anyRequest().permitAll();
//        })
//                .build();
//
//    }
//}
