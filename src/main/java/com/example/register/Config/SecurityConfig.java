package com.example.register.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.register.Security.JwtAuthenticationEntryPoint;
import com.example.register.Security.JwtAuthenticationFilter;

import jakarta.activation.DataSource;

@Configuration
//@EnableWebSecurity
class SecurityConfig {

	public static String[] PUBLIC_URL = { "/user/login", "/register/**" };
	public static String[] PRIVATE_URL = { "user/logout", "user/current", "user/getAll", "user/get/{id}",
			"user/update/{id}" };
	public static String[] ROLES = { "ADMIN", "STAFF", "GUEST", };

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable()).cors((cors) -> cors.disable())

				.authorizeHttpRequests(
						(auth) -> auth.requestMatchers(PUBLIC_URL).permitAll().requestMatchers(PRIVATE_URL).permitAll())
//				.authorizeHttpRequests((auth) -> auth.requestMatchers(PRIVATE_URL).authenticated())
//				.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll())
//				.authorizeHttpRequests((auth) -> auth.anyRequest().hasAnyRole(ROLES))
//						.permitAll())
//						).permitAll())
//				.requestMatchers("/register/**").permitAll()
//				.requestMatchers("/user/**").authenticated())
				.logout((logout) -> logout.logoutUrl("/logout").permitAll().clearAuthentication(true))
				.exceptionHandling((ex) -> ex.authenticationEntryPoint(entryPoint))
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}