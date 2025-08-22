package com.emimol.hotel.config;
import com.emimol.hotel.constants.ErrorCode;
import com.emimol.hotel.dto.ErrorRS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final PasswordEncoder passwordEncoder;
	private final ObjectMapper objectMapper;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/public/**").permitAll()
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.exceptionHandling(ex -> ex
						.authenticationEntryPoint((request, response, authException) -> {
							ErrorRS errorResponse = new ErrorRS(
									HttpStatus.UNAUTHORIZED.value(),
									ErrorCode.UNAUTHORIZED,
									"Authentication required. Please provide valid credentials."
							);

							response.setStatus(HttpStatus.UNAUTHORIZED.value());
							response.setContentType("application/json");
							response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
						})
						.accessDeniedHandler((request, response, accessDeniedException) -> {
							ErrorRS errorResponse = new ErrorRS(
									HttpStatus.FORBIDDEN.value(),
									ErrorCode.FORBIDDEN,
									"Access denied. You don't have permission to access this resource."
							);

							response.setStatus(HttpStatus.FORBIDDEN.value());
							response.setContentType("application/json");
							response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
						})
				)
				.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("test-supplier")
				.password(passwordEncoder.encode("test@123"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}