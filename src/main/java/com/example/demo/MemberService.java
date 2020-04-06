package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Member100Dao;
import com.example.demo.entity.Member100;


@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private Member100Dao dao;
	
	
	
	public void setDao(Member100Dao dao) {
		this.dao = dao;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		//매개변수로 전달받은 username에 해당하는
		//회원의 정보를 db로 부터 꺼내와요
		Member100 m = dao.findById(username).get();
		
		//만약 username에 해당하는 회원이 없으면
		//예외를 발생시켜요.
		if (m == null) {
			throw new UsernameNotFoundException(username);
		}
		
		//우리가 db로 부터 뽑아온 회원의 정보를 
		// 스프링 시큐리가 인증절차를 할수 잇는 객체로
		// 만들어 일을 맡깁니다.
		
		
		return User.builder()
				.username(username)
				.password(m.getPwd())
				.roles(m.getRole())
				.build();
	}

}
