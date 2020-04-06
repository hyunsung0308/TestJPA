package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		http.authorizeRequests()
		.mvcMatchers("/").permitAll()
		.mvcMatchers("/manager/**").hasRole("MANAGER")
		.anyRequest().authenticated();
		//.anyRequest().authenticated();
						//로그인하면돼
		

		
		http.formLogin(); //스프링 시큐리티가 제공하는 로그인 폼을 사용하겠습니다.
		//로그인페이지를 사용자가 만들어 지정해 보자
		//http.formLogin().loginPage("/login").permitAll();
		
		//우리가 사용자 정의 loginPage를 지정하면
		//로그아웃제공하지 안하요.
		//로그아웃을 위한 서비스명도 지정해요.
		//http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		//.invalidateHttpSession(true);
		
		http.httpBasic(); // http 기본  프로토콜을 사용하겠습니다.
		http.csrf().disable();
	}

}
