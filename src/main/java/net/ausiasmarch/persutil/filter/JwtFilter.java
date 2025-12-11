package net.ausiasmarch.persutil.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import net.ausiasmarch.persutil.helper.JWTHelper;

@Component
public class JwtFilter extends GenericFilter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            jakarta.servlet.FilterChain chain) throws java.io.IOException, jakarta.servlet.ServletException {        
        if ("OPTIONS".equalsIgnoreCase(((jakarta.servlet.http.HttpServletRequest) request).getMethod())) {
            ((jakarta.servlet.http.HttpServletResponse) response).setStatus(jakarta.servlet.http.HttpServletResponse.SC_OK);
            return;
        } else {
            // aqui el código de una consulta que no es preflight
            String authToken = ((jakarta.servlet.http.HttpServletRequest) request).getHeader("Authorization");
            if (authToken != null && authToken.startsWith("Bearer ")) {
                authToken = authToken.substring(7);
            }
            // Validar el token JWT
            try {                
                // Aquí va la lógica para validar el token JWT
                // Si el token es válido, continuar con la cadena de filtros
                String username = JWTHelper.validate(authToken);
                if (username != null) {
                    // Si el token es válido, continuar con la cadena de filtros
                    ((jakarta.servlet.http.HttpServletRequest) request).setAttribute("username", username);
                    chain.doFilter(request, response);
                } else {
                     ((jakarta.servlet.http.HttpServletRequest) request).setAttribute("username", null);
                    // Si el token no es válido, devolver un error 401
                    ((jakarta.servlet.http.HttpServletResponse) response).setStatus(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (Exception e) {
                ((jakarta.servlet.http.HttpServletRequest) request).setAttribute("username", null);
                // Si el token no es válido, devolver un error 401
                ((jakarta.servlet.http.HttpServletResponse) response).setStatus(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
            }
        }





        chain.doFilter(request, response);
    }
    
}
