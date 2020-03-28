package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.Model.MyUser;
import com.example.demo.repositorie.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"+username));
		List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_" + myUser.getRoles());
		return new User(myUser.getUsername(), myUser.getPassword(), authorityList);
	}

}
