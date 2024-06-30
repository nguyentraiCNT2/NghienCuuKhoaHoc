package com.KnowledgeHubbackend.api.user;

import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileAPI {
    @Autowired
    private final LoginService service;

    public UserProfileAPI(LoginService service) {
        this.service = service;
    }
    @GetMapping("/hien-thi-theo-id")
    public ResponseEntity<?> getByUserid(@RequestParam("token") String token){
        try {
            UsersDTO user = service.getUserById(token);
            if (user==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+token, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
