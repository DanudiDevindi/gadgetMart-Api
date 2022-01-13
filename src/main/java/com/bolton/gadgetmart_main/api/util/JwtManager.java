package com.bolton.gadgetmart_main.api.util;

import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtManager {

	
//	public static final String SECRET = "WJj3dD91YV5uy48olbDCDx11JaFzCR9q2e0T4BmAGU";
	
	public static final String SECRET = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzd2xjIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE2NDIwNTIyNTcsImV4cCI6MTY0MjY1NzA1NywidXNlclR5cGUiOiJBRE1JTiJ9.r5Jv5o4NkrNyPL-4JE3MMhLHQKMYZ1LT6W95iZ-Z1GM";

    public String signJWS(String username, String password, String userType) {
        Date issued = new Date();
        Date expiration;
        Calendar c = Calendar.getInstance();
        c.setTime(issued);
        c.add(Calendar.DATE, 7);
        expiration = c.getTime();

        SecretKey secretKey = Keys.hmacShaKeyFor((username + password + SECRET).getBytes());

        return Jwts.builder()
                .setIssuer("swlc")
                .setSubject(username)
                .setIssuedAt(issued)
                .setExpiration(expiration)
                .claim("userType", userType)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    Jws<Claims> verifyJWS(String jws, String username, String password) {
        SecretKey secretKey = Keys.hmacShaKeyFor((username + password + SECRET).getBytes());

        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jws);
        } catch (JwtException e) {
            return null;
        }
    }
	
	
	
	
}
