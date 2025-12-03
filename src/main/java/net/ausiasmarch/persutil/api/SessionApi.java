package net.ausiasmarch.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.ausiasmarch.persutil.dto.LoginRequestDto;
import net.ausiasmarch.persutil.dto.LoginResponseDto;
import net.ausiasmarch.persutil.entity.UsuarioEntity;
import net.ausiasmarch.persutil.helper.JWTHelper;
import net.ausiasmarch.persutil.repository.UsuarioRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/session")
public class SessionApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Login con JWT - retorna token, username y rol admin
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            // Autenticar usuario con Spring Security
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            // Obtener usuario de la BD
            UsuarioEntity usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Generar token JWT
            String token = JWTHelper.generateJWT(usuario.getUsername(), usuario.getAdmin());

            // Construir respuesta
            LoginResponseDto response = new LoginResponseDto(
                token,
                usuario.getUsername(),
                usuario.getAdmin(),
                "Login exitoso"
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponseDto(null, null, null, "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LoginResponseDto(null, null, null, "Error en el servidor: " + e.getMessage()));
        }
    }

    /**
     * Logout - limpia el contexto de seguridad
     * El frontend debe eliminar el token del localStorage
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body("{\"message\": \"Logout exitoso\"}");
    }

    /**
     * Validar token JWT - retorna información del usuario autenticado
     */
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7); // Quitar "Bearer "
            String username = JWTHelper.extractUsername(token);
            Boolean admin = JWTHelper.extractAdmin(token);

            if (!JWTHelper.isTokenExpired(token)) {
                LoginResponseDto response = new LoginResponseDto(
                    token,
                    username,
                    admin,
                    "Token válido"
                );
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"Token expirado\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\": \"Token inválido\"}");
        }
    }

    /**
     * Obtener información del usuario autenticado actual
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("{\"message\": \"No autenticado\"}");
    }

    /**
     * Contar usuarios (solo admin)
     */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(usuarioRepository.count());
    }
}
