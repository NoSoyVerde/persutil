package net.ausiasmarch.persutil.service;

import org.springframework.stereotype.Service;

import net.ausiasmarch.persutil.bean.SessionBean;

@Service
public class SessionService {

    public Long login(SessionBean oSessionBean) {
        // Lógica de autenticación aquí
        // Por ejemplo, verifica el usuario y la contraseña
        if ("u".equals(oSessionBean.getUsername()) && "contraseña".equals(oSessionBean.getPassword())) {
            return 1L; // Retorna un ID de sesión simulado
        } else {
            return null; // Autenticación fallida
        }
    }
}
