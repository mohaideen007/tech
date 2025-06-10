package com.user.user_datas.Config;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import lombok.val;



@Configuration
@EnableWebSecurity
public class user_config {




    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtValidate jwtvalidate;

    



    @Bean
    public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
    return http
    .cors(c->c.configurationSource(corsConfigurationSource()))
    .csrf(c -> c.disable())

    .authorizeHttpRequests(a -> a
        .requestMatchers("/user/adduser",
        "/user/login",
        "/user/getpaas/**").permitAll()  // allow all under /user/
        .anyRequest().authenticated()
        )
        
    .httpBasic(Customizer.withDefaults())
        
    .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .addFilterBefore(jwtvalidate, UsernamePasswordAuthenticationFilter.class)
        
    .build();
}



    @Bean
    public AuthenticationProvider authpro(){
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder(12));
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
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
}
