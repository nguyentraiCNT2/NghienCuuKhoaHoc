package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.UsersOUTPUT;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quan-ly-nguoi-dung-admin")
public class QuanLyNguoiDungAPI {
    @Autowired
    private final UserService userService;

    public QuanLyNguoiDungAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hien-thi-toan-bo")
    public UsersOUTPUT getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UsersOUTPUT result = new UsersOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-email/{email}")
    public UsersOUTPUT getByEmail(@PathVariable String email,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UsersOUTPUT result = new UsersOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByEmail(email,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-tai-khoan/{username}")
    public UsersOUTPUT getByUsername(@PathVariable String username,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UsersOUTPUT result = new UsersOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByUserName(username,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-phone/{phone}")
    public UsersOUTPUT getByPhone(@PathVariable String phone,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UsersOUTPUT result = new UsersOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByPhone(phone,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{userid}")
    public ResponseEntity<?> getByUserid(@PathVariable String userid){
        try {
                UsersDTO user = userService.getByUserid(userid);
                if (user==null){
                    return new ResponseEntity<>( "không có người dùng nào có id là: "+userid, HttpStatus.NOT_FOUND);
                }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/hien-thi-theo-quyen-han/{roleid}")
    public UsersOUTPUT getByUserRole(@PathVariable Integer roleid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UsersOUTPUT result = new UsersOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByUserRole(roleid,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @PostMapping("/them-moi-nguoi-dung/{roleid}")
    public ResponseEntity<String> createUser(@PathVariable Integer roleid,@RequestBody UsersDTO usersDTO) {
        try {
            userService.createUser(usersDTO,roleid);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-nguoi-dung/{userid}")
    public ResponseEntity<String> updateUser(@PathVariable String userid, @RequestBody UsersDTO usersDTO) {
        try {
            usersDTO.setUserid(userid);
            userService.updateUser(usersDTO);
            return new ResponseEntity<>(usersDTO+" sua thành công! ", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-nguoi-dung/{userid}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable String userid) {
        try {
            userService.deleteByUserid(userid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
