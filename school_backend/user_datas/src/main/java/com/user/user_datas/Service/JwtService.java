package com.user.user_datas.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.user.user_datas.Model.user_dao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretkey;

    public String generatetoken(user_dao userdao) {
      Map<String,Object>claims=new HashMap<>();

      claims.put("role", userdao.getRole());

      return Jwts.builder()
      .claims()
      .add(claims)
      .subject(userdao.getUsername())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
      .and()
      .signWith(getKey())
      .compact();
    }

    private SecretKey getKey() {
       byte[]keybytes=Base64.getDecoder().decode(secretkey);
       return Keys.hmacShaKeyFor(keybytes);
    }

    public String extractusername(String token) {
      Claims claims=extractAllclaims(token);
      return extractallusername(claims);
        
    }

    private String extractallusername(Claims claims) {
      return claims.getSubject();
    }

    public boolean validatetoken(String token, UserDetails userDetails) {
      String username=extractusername(token);
      return(username.equals(userDetails.getUsername())&&!isTokenExpired(token));
      
    }

    private boolean isTokenExpired(String token) {
      return extractexpiration(token).before(new Date());
    }

    private Date extractexpiration(String token) {
      Claims claims=extractAllclaims(token);
      return expirationallfrom(claims);
      
    }

    private Date expirationallfrom(Claims claims) {
      return claims.getExpiration();
    }

    public String extractrole(String token) {
      Claims claims=extractAllclaims(token);
      return axtractallroles(claims);
    }

    private String axtractallroles(Claims claims) {
      return claims.get("role", String.class);
    }

    public Claims extractAllclaims(String token){
      return Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
    }

}
