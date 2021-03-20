package br.com.rafael.naturassp.security;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rafael.naturassp.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTTokenUtils {
	
	public static final String SECRET_KEY= "*N@tur@SSpW3bS3curityT0k3n202101";
	private static final int EXPIRATION = 2*60*1000;
	private static final String TK_PREFIX="Bearer ";
	private static final String HEADER_AUTH = "Authorization"; 
	
	public static String generateToken(Usuario usuario) {
		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		String jwt = Jwts.builder()
						  .setSubject(usuario.getUsername())
						  .setIssuer("Rafael Marinheiro")
						  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION ))
						  .signWith(secretKey,SignatureAlgorithm.HS256)
						  .compact();
		
		return TK_PREFIX + jwt;
		
	}
	
	public static boolean isIssuerValid(String issuer) {
		return issuer.equals("Rafael Marinheiro");
	}
	
	public static boolean isSubjectValid(String subject) {
		return subject != null && subject.length() > 0;
	}
	
	public static boolean isExpirationValid(Date expiration) {
		return expiration.after( new Date(System.currentTimeMillis()));
	}
	
	public static Authentication decodeToken(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_AUTH);
		token = token.replace(TK_PREFIX, ""); // retiro bearer
		Jws<Claims> jwsClaims = Jwts.parserBuilder()
									.setSigningKey(SECRET_KEY.getBytes())
									.build()	
									.parseClaimsJws(token);
		String username = jwsClaims.getBody().getSubject();
		String emissor = jwsClaims.getBody().getIssuer();
		Date expiration = jwsClaims.getBody().getExpiration();
		
		if(isSubjectValid(username) && isIssuerValid(emissor) && isExpirationValid(expiration)) {
			return 	new UsernamePasswordAuthenticationToken(username,null,Collections.emptyList());
		}
		
		return null;
		
	}

}
