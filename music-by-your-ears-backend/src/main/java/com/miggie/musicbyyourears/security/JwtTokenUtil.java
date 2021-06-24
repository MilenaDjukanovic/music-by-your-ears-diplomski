package com.miggie.musicbyyourears.security;

import com.miggie.musicbyyourears.repo.entity.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";

    private final String jwtIssuer = "musicByYourEars.com";

    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(this.jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            // #TODO logger e1
        } catch (MalformedJwtException ex) {
            // #TODO logger e2
        } catch (ExpiredJwtException ex) {
            // #TODO logger e3
        } catch (UnsupportedJwtException ex) {
            // #TODO logger e4
        } catch (IllegalArgumentException ex) {
            // #TODO logger e5
        }
        return false;
    }
}
