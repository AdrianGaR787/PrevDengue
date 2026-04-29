package pe.edu.upc.prevdengue.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    private static final long TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    // 1. Generamos la llave de forma segura a partir de los bytes del texto (SIN BASE64)
    private SecretKey getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) { return getClaim(token, Claims::getSubject); }
    public Date getExpirationDate(String token) { return getClaim(token, Claims::getExpiration); }
    public <T> T getClaim(String token, Function<Claims, T> resolver) { return resolver.apply(getAllClaims(token)); }

    // 2. Usamos el nuevo formato de Parser para JJWT 0.13.0
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isExpired(String token) { return getExpirationDate(token).before(new Date()); }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.joining(",")));
        return createToken(claims, userDetails.getUsername());
    }

    // 3. Usamos el nuevo formato de Builder para JJWT 0.13.0
    private String createToken(Map<String, Object> claims, String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_VALIDITY);
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey()) // Aquí ya no hay Base64, solo bytes puros
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token).equals(userDetails.getUsername()) && !isExpired(token);
    }
}