package net.ausiasmarch.persutil.helper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {
    
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // Clave secreta de 256 bits

    public static String generateJWT(String username) {
        return Jwts.builder()
                .setIssuer("ausiasmarch.net")
                .setSubject("DAWsiasmarchPERSUTIL")
                .claim("username", username)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 10800000)) // 3 HORAS
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    }
    
}
