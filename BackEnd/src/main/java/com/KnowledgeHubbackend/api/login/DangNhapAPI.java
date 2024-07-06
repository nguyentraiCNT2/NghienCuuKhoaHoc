package com.KnowledgeHubbackend.api.login;

import com.KnowledgeHubbackend.algorithm.RandomId;
import com.KnowledgeHubbackend.algorithm.TokenUtil;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.service.LoginService;
import com.KnowledgeHubbackend.service.UserTokenService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class DangNhapAPI {
    @Autowired
    private final LoginService loginService;
    @Autowired
    private final UserTokenService userTokenService;

    public DangNhapAPI(LoginService loginService, UserTokenService userTokenService) {
        this.loginService = loginService;
        this.userTokenService = userTokenService;
    }

    // Phương thức POST được sử dụng để đăng nhập với vai trò quản trị viên
    @PostMapping("/dang-nha-cua-quan-tri-vien")
    public ResponseEntity<?> signinAdmin(String username, String password) {
        try {
            // Gọi service để thực hiện đăng nhập quản trị viên
            UsersDTO userDTO = loginService.loginAdmin(username, password);
            // Thiết lập thời gian hết hạn của token là 3600 giây (1 giờ)
            long expirationMillis = 3600;
            // Tạo token chính từ thông tin của người dùng
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            // Tạo một map chứa các thông tin phản hồi
            Map<String, Object> response = new HashMap<>();
            ResponseCookie cookie = ResponseCookie.from("token", mainToken)
                    .httpOnly(true) // Thiết lập httponly
                    .maxAge(Duration.ofSeconds(3600)) // Thiết lập thời gian sống của cookie
                    .sameSite("None") // Thiết lập SameSite
                    .secure(true) // Đánh dấu cookie chỉ được gửi qua kênh an toàn (HTTPS)
                    .path("/")
                    .build();

            // Thêm cookie vào header của phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            response.put("token", mainToken);
            response.put("user", userDTO);
            // Trả về phản hồi thành công (HTTP status code 200) kèm theo các thông tin đã tạo
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dang-nha-cua-cong-tac-vien")
    public ResponseEntity<?> signinCTV(String username, String password) {
        try {
            UsersDTO userDTO = loginService.loginCTV(username, password);
            // Thiết lập thời gian hết hạn của token là 3600 giây (1 giờ)
            long expirationMillis = 3600;
            // Tạo token chính từ thông tin của người dùng
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            // Tạo một map chứa các thông tin phản hồi
            Map<String, Object> response = new HashMap<>();
            ResponseCookie cookie = ResponseCookie.from("token", mainToken)
                    .httpOnly(true) // Thiết lập httponly
                    .maxAge(Duration.ofSeconds(3600)) // Thiết lập thời gian sống của cookie
                    .sameSite("None") // Thiết lập SameSite
                    .secure(true) // Đánh dấu cookie chỉ được gửi qua kênh an toàn (HTTPS)
                    .path("/")
                    .build();

            // Thêm cookie vào header của phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            response.put("token", mainToken);
            response.put("user", userDTO);
            userTokenService.createToken(mainToken, userDTO);
            // Trả về phản hồi thành công (HTTP status code 200) kèm theo các thông tin đã tạo
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> signinUser(String username, String password) {
        try {
            UsersDTO userDTO = loginService.loginUser(username, password);
            // Thiết lập thời gian hết hạn của token là 3600 giây (1 giờ)
            long expirationMillis = 3600;
            // Tạo token chính từ thông tin của người dùng
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            // Tạo một map chứa các thông tin phản hồi
            Map<String, Object> response = new HashMap<>();
            // Tạo một cookie chứa token để gửi về client
            // Tạo cookie với token
            ResponseCookie cookie = ResponseCookie.from("token", mainToken)
                    .httpOnly(true) // Thiết lập httponly
                    .maxAge(Duration.ofSeconds(3600)) // Thiết lập thời gian sống của cookie
                    .sameSite("None") // Thiết lập SameSite
                    .secure(true) // Đánh dấu cookie chỉ được gửi qua kênh an toàn (HTTPS)
                    .path("/")
                    .build();

            // Thêm cookie vào header của phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            response.put("token", mainToken);
            response.put("user", userDTO);
            userTokenService.createToken(mainToken, userDTO);
            // Trả về phản hồi thành công (HTTP status code 200) kèm theo các thông tin đã tạo
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dang-ky")
    public ResponseEntity<?> signup(@RequestBody UsersDTO userDTO) {
        try {
            String randomId = RandomId.generateRandomId(30);
            userDTO.setUserid(randomId);
            userDTO.setStatus(true);
            loginService.SignUp(userDTO);
            return new ResponseEntity<>(randomId, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/kiem-tra-ma-xac-thuc")
    public ResponseEntity<?> getByCheckcore(@RequestParam("code") String code, @RequestParam("userid") String userid) {
        try {
            UsersDTO userDTO = loginService.ComfirmEmail(code,userid);
            long expirationMillis = 3600;
            // Tạo token chính từ thông tin của người dùng
            String mainToken = TokenUtil.generateToken(userDTO.getUserid(), expirationMillis);
            // Tạo một map chứa các thông tin phản hồi
            Map<String, Object> response = new HashMap<>();
            // Tạo một cookie chứa token để gửi về client
            // Tạo cookie với token
            ResponseCookie cookie = ResponseCookie.from("token", mainToken)
                    .httpOnly(true) // Thiết lập httponly
                    .maxAge(Duration.ofSeconds(3600)) // Thiết lập thời gian sống của cookie
                    .sameSite("None") // Thiết lập SameSite
                    .secure(true) // Đánh dấu cookie chỉ được gửi qua kênh an toàn (HTTPS)
                    .path("/")
                    .build();

            // Thêm cookie vào header của phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            response.put("token", mainToken);
            response.put("user", userDTO);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
