package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(
//                new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        userList.add(
//                new User("tata", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))) ;
//
//        return new InMemoryUserDetailsManager(userList);
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeRequests()//this make all our pages authorized
//                .antMatchers("/user/**").hasRole("ADMIN")//we give authority to ADMIN modify anything under UserController
                .antMatchers("/user/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/project/**").hasRole("MANAGER")
//                .antMatchers("/task/employee/**").hasRole("EMPLOYEE")
//                .antMatchers("/task/**").hasRole("MANAGER")
               // .antMatchers("/task/**").hasAnyRole("EMPLOYEE", "MANAGER","ADMIN")
              //  .antMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll() //anyone can access
                .anyRequest().authenticated()
                .and()
             //   .httpBasic()
                .formLogin()//allow us to use our own defined login page
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error=true")
                .permitAll()
                .and().build();



    }




}