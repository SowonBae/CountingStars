package com.ssafy.cstars.security.jwt;

import java.security.SignatureException;
import java.util.Date;

import com.ssafy.cstars.security.services.BrandAdminDetailsImpl;
import com.ssafy.cstars.security.services.StoreAdminDetailsImpl;
import com.ssafy.cstars.security.services.UserDetailsImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${bezkoder.app.jwtSecret}")
  private String jwtSecret;

  @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(UserDetailsImpl userPrincipal) {
    return generateTokenFromEmail(userPrincipal.getEmail());
  }

  public String generateJwtTokenStoreAdmin(StoreAdminDetailsImpl storeAdminPrincipal){
    return generateTokenFromEmail(storeAdminPrincipal.getEmail());
  }

  public String generateJwtTokenBrandAdmin(BrandAdminDetailsImpl brandAdminPrincipal){
    return generateTokenFromEmail(brandAdminPrincipal.getEmail());
  }

  public String generateTokenFromEmail(String email) {
    return Jwts.builder().setSubject(email).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getEmailFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } 
    // catch (SignatureException e) {
    //   logger.error("Invalid JWT signature: {}", e.getMessage());
    // }
    catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

}