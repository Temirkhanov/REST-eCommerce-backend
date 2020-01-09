package com.goruslan.eCommerce.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // Call JWT properties from app.properties
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.token.prefix}")
    private String jwtTokenPrefix;

    @Value("${app.jwt.header.string}")
    private String jwtHeaderString;

    @Value("${app.jwt.expiration-in-ms}")
    private Long jwtExpirationInMs;


    // Authentication class keeps the credentials and roles of authenticated user.
    public String generateToken(Authentication auth) {
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
        // Token will contain username, roles and expiration time
        return Jwts.builder().setSubject(auth.getName()) //Getting username
                .claim("roles", authorities) //Getting roles of auth
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)) //Setting expiration time
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact(); //Sign the token with HS512 algorithm
    }

    // Parse token value and get username and roles from the token.
    public Authentication getAuthentication(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return null;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        final List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_")?role:"ROLE_"+role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return username!= null ? new UsernamePasswordAuthenticationToken(username, null, authorities): null;
    }

    // Validating the token. If expired, return false.
    public boolean validateToken(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return false;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    private String resolveToken(HttpServletRequest req){
        //Bearer key...
        String bearerToken = req.getHeader(jwtHeaderString); //Get authorization value from http header
        if(bearerToken!=null && bearerToken.startsWith(jwtTokenPrefix)){
            return bearerToken.substring(7, bearerToken.length()); //Token value
        }
        return null;
    }

}
