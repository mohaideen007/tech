package com.example.demo.Config;

import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class work_confih implements WebMvcConfigurer {


    @Bean
    public SecurityFilterChain sfc(HttpSecurity http) throws Exception{
        return http
        .cors(c->c.configurationSource(corsConfigurationSource()))
        .csrf(c->c.disable())
        .authorizeHttpRequests(a->a.requestMatchers("/homework/getwork/**").permitAll()
         .requestMatchers("/uploads/**").permitAll()   
        .anyRequest().authenticated())
        .oauth2ResourceServer(a->a.jwt(j->j.jwtAuthenticationConverter(jwtconverter())))
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
   public JwtDecoder jwtdecode(){
    String secretkey="fwajtMZIwQ7eLuZD93M0p1qXZWXOr/uz2Ipz8T0rICw=";
    byte[]keybytes=Base64.getDecoder().decode(secretkey);
    SecretKeySpec spec=new SecretKeySpec(keybytes,"HmacSHA256");
    return NimbusJwtDecoder.withSecretKey(spec).build();
   }


   @Bean
   public WebClient.Builder web(){
    return WebClient.builder();
   }
  @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // React frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry regis){
        regis.addResourceHandler("/uploads/**")
        .addResourceLocations("file:uploads/")
        .setCachePeriod(3600);

    }

    }

