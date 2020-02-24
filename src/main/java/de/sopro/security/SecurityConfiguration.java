package de.sopro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


	@Autowired
	CustomUserDetailsService userDetailsService;

	@Bean
	protected PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().configurationSource(corsConfigurationSource()).and().antMatcher("/**").authorizeRequests()
				.antMatchers("/", "/images/*", "/css/*", "/webjars/**").permitAll()
				.antMatchers("/hello/*", "/search", "/api/search/*").permitAll()
				.anyRequest()
				.authenticated().and()
				.httpBasic();
				/** 
				//.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll()
			.and()
				.logout().permitAll();
				**/

		// Comment in to enable H2 console on test server (not recommended for release version!)
		//http.csrf().disable();
		//http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

}
