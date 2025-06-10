package com.example.demo.Config;

import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class student_config {
	
	
	
	
	@Bean
	public WebClient.Builder builder(){
		return WebClient.builder();
	}
	
	
	@Bean
	public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
		return http
		.cors(c->c.configurationSource(Configurationsorce()))
		.csrf(c->c.disable())
				.authorizeHttpRequests(a->a
				.anyRequest().authenticated())
				.oauth2ResourceServer(o->o.jwt(j->j.jwtAuthenticationConverter(jwtconverter())))
				.build();
	}


	@Bean
   public JwtAuthenticationConverter jwtconverter(){
    JwtGrantedAuthoritiesConverter granted=new JwtGrantedAuthoritiesConverter();
    granted.setAuthorityPrefix("ROLE_");
    granted.setAuthoritiesClaimName("role");
    JwtAuthenticationConverter jwtAuthenticationConverter=new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(granted);
    return jwtAuthenticationConverter;
   }


	@Bean
	public JwtDecoder jwtdecoder(){
		String secretkey="fwajtMZIwQ7eLuZD93M0p1qXZWXOr/uz2Ipz8T0rICw=";
		byte[] keybyte=Base64.getDecoder().decode(secretkey);
		SecretKeySpec spec=new SecretKeySpec(keybyte,"HmacSHA256");
		return NimbusJwtDecoder.withSecretKey(spec).build();
	}

	@Bean
	public CorsConfigurationSource Configurationsorce(){
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173"));
		config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;

	}
}
