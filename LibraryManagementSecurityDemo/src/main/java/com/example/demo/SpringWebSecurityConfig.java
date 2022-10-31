package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter{
/**
 * database authemtication
 * 1. User
 * 2. Role
 * 3. Create a class of type UserDetails
 * 4. Create a service of type UserDetailsService for authentication
 * 5. configure in security for user details service class
 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user1").password(encoder().encode("user1")).roles("USER")
//		.and()
//		.withUser("admin").password(encoder().encode("admin123")).roles("ADMIN");
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new MyUserDetailsService();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(encoder());
		return dao;
	}
	
	@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.anyRequest().permitAll();
		
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/books","/books/showFormForAdd","/books/403")
		.hasAnyAuthority("USER", "ADMIN")
		.antMatchers(HttpMethod.POST,"/books/save")
		.hasAnyAuthority("USER", "ADMIN")
		.antMatchers("/books/showFormForUpdate","/books/delete")
		.hasAnyAuthority("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginProcessingUrl("/login")//.successForwardUrl("/books")
		.permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/books/403")
		.and().csrf().disable();
		}
	
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	//@Bean
	public void addData()
	{
		Role r1 = new Role();
		r1.setName("USER");
		r1.setId(1);
		Role r2 = new Role();
		r2.setId(2);
		r2.setName("ADMIN");
		roleRepo.save(r1);
		roleRepo.save(r2);
		
		User u1 = new User(1L, "shalini", encoder().encode("shalini123"), Arrays.asList(r1));
		User u2 = new User(2L, "admin", encoder().encode("admin123"), Arrays.asList(r1, r2));
		
		userRepo.save(u1);
		userRepo.save(u2);
		
	}
}
