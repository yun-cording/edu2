package com.ict.edu2.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
  
  @Override
  public void configure(HttpSecurity http) throws Exception{
  http
    .csrf().disable()
    .authorizeRequests()
    .antMatchers("/**").permitAll()
    .and()
    .sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
    .formLogin()
    .disable()
    .cors();
  }
  // http.csrf().disable().cors().and().authorizeRequests().antMatchers("/**").permitAll().and()
  //   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().disable();
  // http
  //   .csrf().disable()
  //   .authorizeRequests()
  //   .antMatchers("/**").permitAll()
  //   .and()
  //   .sessionManagement()
  //   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  //   .and()
  //   .formLogin()
  //   .disable()
  //   .cors();

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}

