package net.ausiasmarch.persutil.service;

import org.springframework.stereotype.Service;

import net.ausiasmarch.persutil.bean.SessionBean;
import net.ausiasmarch.persutil.helper.JWTHelper;

@Service
public class SessionService {


    public String login(SessionBean oSessionBean) {
        // Lógica de autenticación aquí
        // hardcoded
        if ("rafa".equals(oSessionBean.getUsername()) && "7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e".equalsIgnoreCase(oSessionBean.getPassword())) {
            // generar el token JWT
            return JWTHelper.generateJWT(oSessionBean.getUsername());            
        } else {
            return null; // Autenticación fallida Rafa -> cambiar por excepcion
        }
    }
    
}
