package com.user.user_datas.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import com.user.user_datas.Service.JwtService;
import com.user.user_datas.Service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtValidate extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtservice;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

               String path = request.getRequestURI();
if (path.startsWith("/user/adduser") ||
    path.startsWith("/user/login") ||
    path.startsWith("/user/getpaas")) {

    filterChain.doFilter(request, response);
    return;
}


        
                String header= request.getHeader("AUTHORIZATION");
                String token="";
                String username="";

                if(header!=null&&header.startsWith("Bearer ")){
                    token=header.substring(7);
                    username=jwtservice.extractusername(token);
                }

                if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails=context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
                    if(jwtservice.validatetoken(token,userDetails)){
                        String role=jwtservice.extractrole(token);
                        List<GrantedAuthority>GrantedAuthorit=List.of(new SimpleGrantedAuthority(role));
                        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails,null, GrantedAuthorit);
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
                filterChain.doFilter(request, response);
    }

}
