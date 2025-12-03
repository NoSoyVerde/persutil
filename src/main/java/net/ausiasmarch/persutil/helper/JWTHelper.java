package net.ausiasmarch.persutil.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public class JWTHelper {
    
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret-must-be-long-enough-for-HS256";
    private static final long EXPIRATION_TIME = 10800000; // 3 horas en milisegundos
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Genera un token JWT para un usuario
     */
    public static String generateJWT(String username, Boolean isAdmin) {
        return Jwts.builder()
                .setIssuer("ausiasmarch.net")
                .setSubject(username)
                .claim("username", username)
                .claim("admin", isAdmin)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el username del token
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token
     */
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae un claim específico del token
     */
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los claims del token
     */
    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica si el token ha expirado
     */
    public static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Valida el token comparando username y verificando expiración
     */
    public static Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Extrae el rol admin del token
     */
    public static Boolean extractAdmin(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("admin", Boolean.class);
    }
}

