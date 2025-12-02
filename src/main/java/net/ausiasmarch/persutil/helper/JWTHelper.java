package net.ausiasmarch.persutil.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {
    
    private static final String SECRET_KEY = "tu_clave_secreta_aqui_1234567890_DAWSIAS"; // Cambia esto por una clave segura

    public static String generateJWT(String username) {
        return Jwts.builder()
                .setIssuer("ausiasmarch.net")
                .setSubject("DAWsiasmarchPERSUTIL")
                .claim("username", username)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 10800000)) // 3 horas
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }



}
