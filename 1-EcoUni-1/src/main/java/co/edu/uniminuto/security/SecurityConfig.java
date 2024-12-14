package co.edu.uniminuto.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userManager() {
		List<UserDetails> users= List.of(
				User
				.withUsername("Paola")
				.password("{noop}123paola")
				.roles("USERS")
				.build(),
				User
				.withUsername("Julian")
				.password("{noop}123julian")
				.roles("ADMINS")
				.build()
				
				);
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(cus->cus.disable())
		.authorizeHttpRequests(aut->
		aut.requestMatchers(HttpMethod.GET,"/api/usuarios").hasAnyRole("USERS")
		.requestMatchers(HttpMethod.GET,"/api/usuarios/{id}").hasAnyRole("ADMINS")
		.requestMatchers(HttpMethod.DELETE,"/api/usuarios/{id}").hasAnyRole("ADMINS")
		.requestMatchers(HttpMethod.POST,"/api/usuarios").authenticated()
		.requestMatchers(HttpMethod.GET,"api/EmpresasRecicladora/EmpresasRecicladora").hasAnyRole("USERS","ADMINS")
		.requestMatchers(HttpMethod.DELETE,"/api/recompensas/{id}").hasAnyRole("ADMINS")
		.requestMatchers(HttpMethod.POST,"/api/recompensas").hasAnyRole("ADMINS")
		.requestMatchers("/unimin/swagger-ui/index.html",
                "/v3/api-docs/**",         
                "/swagger-ui/**",          
                "/swagger-ui.html" ).permitAll().anyRequest().authenticated()
				
				)
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}