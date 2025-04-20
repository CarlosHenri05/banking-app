package br.com.banking.app.auth.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails, long jwtExpiration) {
    return Jwts.builder()
               .setClaims(extraClaims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
               .signWith(getSigningKey(), SignatureAlgorithm.HS256)
               .compact();
               
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  /*
   * 
   * TO-DO 
   * 
   */

  private boolean isTokenExpired(String token) {
    return false;
  }

  private boolean isTokenValid(String token, UserDetails userDetails) {
    return false;
  }

  private Claims extractAllClaims (String token) {
    return null;
  }



}
