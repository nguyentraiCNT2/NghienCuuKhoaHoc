package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.RolesOutPut;
import com.KnowledgeHubbackend.api.output.UsersOUTPUT;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.service.RolesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quan-ly-quyen-han")
public class QuanLyQuyenHanAPI {
    @Autowired
    private final RolesService  rolesService;

    public QuanLyQuyenHanAPI(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    @GetMapping("/hien-thi-tat-ca")
    public RolesOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        RolesOutPut result = new RolesOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(rolesService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (rolesService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-ten/{rolename}")
    public RolesOutPut getByRolename(@PathVariable String rolename,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        RolesOutPut result = new RolesOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(rolesService.getByRoleName(rolename,pageable));
        result.setTotalPage((int) Math.ceil((double) (rolesService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{roleid}")
    public ResponseEntity<?> getByRoleid(@PathVariable Integer roleid){
        try {
            RolesDTO role = rolesService.getByRoleid(roleid);

            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/them-moi-quyen-han")
    public ResponseEntity<String> createRole(@RequestBody RolesDTO rolesDTO) {
        try {
            rolesService.createRoles(rolesDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-quyen-han/{roleid}")
    public ResponseEntity<String> updateRole(@PathVariable Integer roleid, @RequestBody RolesDTO rolesDTO) {
        try {
            rolesDTO.setRoleid(roleid);
            rolesService.updateRoles(rolesDTO);
            return new ResponseEntity<>(rolesDTO+"Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-quyen-han/{roleid}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer roleid) {
        try {
            rolesService.deleteByRoleid(roleid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
