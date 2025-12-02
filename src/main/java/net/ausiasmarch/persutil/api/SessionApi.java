package net.ausiasmarch.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.persutil.bean.SessionBean;
import net.ausiasmarch.persutil.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/session")
public class SessionApi {

    @Autowired
    SessionService oSessionService;

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody SessionBean oSessionBean) {
        Long sessionId = oSessionService.login(oSessionBean);
        if (sessionId != null) {
            return new ResponseEntity<>(sessionId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
