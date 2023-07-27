package electro.store.security;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import electro.store.entity.Account;
import electro.store.security.oauth.CustomOAuth2UserService;
import electro.store.security.oauth.OAuth2LoginSuccessHandler;
import electro.store.service.AccountService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService accountService;
	
	@Lazy
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	
	@Autowired
	private OAuth2LoginSuccessHandler auth2LoginSuccessHandler;
	
	//cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username);
				String password = pe.encode(user.getPassword());
				String[] roles = user.getAuthorities().stream()
						.map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found!");
			}
		});
	}
	
	//phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/order/**","/favorite/list").authenticated()
			.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
			.antMatchers("/rest/authorities").hasRole("DIRE")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/home/index", false)
			.failureUrl("/security/login/error")
			.and()
			.oauth2Login()
				.loginPage("/security/login/form")
				.userInfoEndpoint().userService(oAuth2UserService)
				.and()
				.successHandler(auth2LoginSuccessHandler)
			;
		
		http.rememberMe()
			.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
			.accessDeniedPage("/security/unauthoried");
		
		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff/success");
	}
	
	//cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//cho phép truy xuất REST API từ bên ngoài(domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
}
