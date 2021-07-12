package com.icaroelucas.restauranteorlajk.secutiry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(autenticacaoService)
		.passwordEncoder(new BCryptPasswordEncoder())
		;
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/images/**").permitAll()
		.mvcMatchers("/recepcao/**").hasAuthority("RECEPCAO")
		.mvcMatchers("/mesas/**").hasAuthority("MESAS")
		.mvcMatchers("/cozinha/**").hasAuthority("COZINHA")
		.mvcMatchers("/caixa/**").hasAuthority("CAIXA")
		.mvcMatchers("/estoque/**").hasAuthority("ESTOQUE")
		.mvcMatchers("/administracao/**").hasAuthority("ADMINISTRACAO")
		.anyRequest().authenticated()
		.and().formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/home", true)
				.permitAll()
				)
		.logout(logout -> logout.logoutUrl("/logout")).csrf().disable()
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
	
}
