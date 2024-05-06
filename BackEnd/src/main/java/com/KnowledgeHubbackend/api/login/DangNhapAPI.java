package com.KnowledgeHubbackend.api.login;

import com.KnowledgeHubbackend.algorithm.TokenUtil;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.service.LoginService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class DangNhapAPI {
    @Autowired
    private final LoginService loginService;

    public DangNhapAPI(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/dang-nha-cua-quan-tri-vien")
    public ResponseEntity<?> signinAdmin(String username, String password) {
        try {
            UsersDTO userDTO = loginService.loginAdmin(username, password);
            Date date  = new Date();
            long expirationMillis = 3600;
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            Map<String, Object> response = new HashMap<>();
            Cookie cookie = new Cookie("token", mainToken);
            String role = "User";
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.put("admintoken",mainToken);
            response.put("role",role);
            response.put("userid",userDTO.getUserid());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    } @PostMapping("/dang-nha-cua-cong-tac-vien")
    public ResponseEntity<?> signinCTV(String username, String password) {
        try {
            UsersDTO userDTO = loginService.loginCTV(username, password);
            Date date  = new Date();
            long expirationMillis = 3600;
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            Map<String, Object> response = new HashMap<>();
            Cookie cookie = new Cookie("token", mainToken);
            String role = "User";
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.put("Ctvtoken",mainToken);
            response.put("role",role);
            response.put("userid",userDTO.getUserid());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    } @PostMapping("/dang-nhap")
    public ResponseEntity<?> signinUser(String username, String password) {
        try {
            UsersDTO userDTO = loginService.loginUser(username, password);
            Date date  = new Date();
            long expirationMillis = 3600;
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            Map<String, Object> response = new HashMap<>();
            Cookie cookie = new Cookie("token", mainToken);
            String role = "User";
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.put("usertoken",mainToken);
            response.put("role",role);
            response.put("userid",userDTO.getUserid());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
