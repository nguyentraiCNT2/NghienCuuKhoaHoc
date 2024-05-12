package com.KnowledgeHubbackend.api.ctv;

import com.KnowledgeHubbackend.api.output.SuppliersOutPut;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import com.KnowledgeHubbackend.service.SuppliersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ctv/quan-ly-nha-cung-cap")
public class QuanLyNhaCungCapAPI {
    @Autowired
    private final SuppliersService suppliersService;


    public QuanLyNhaCungCapAPI(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }
    @GetMapping("/hien-thi-tat-ca")
    public SuppliersOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        SuppliersOutPut result = new SuppliersOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(suppliersService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (suppliersService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }

    @GetMapping("/hien-thi-theo-ten-nha-cung-cap/{suppliersname}")
    public SuppliersOutPut getBySuppliername(@PathVariable String suppliersname, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        SuppliersOutPut result = new SuppliersOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(suppliersService.getBySuppliername(suppliersname,pageable));
        result.setTotalPage((int) Math.ceil((double) (suppliersService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{supplierid}")
    public ResponseEntity<?> getByRoleid(@PathVariable Integer supplierid){
        try {
            SuppliersDTO suppliersDTO = suppliersService.getBySupplierid(supplierid);

            return new ResponseEntity<>(suppliersDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( "khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/them-moi-nha-cung-cap")
    public ResponseEntity<String> createRole(@RequestBody SuppliersDTO suppliersDTO) {
        try {
            suppliersService.createSuppliers(suppliersDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-nha-cung-cap/{supplierid}")
    public ResponseEntity<String> updateRole(@PathVariable Integer supplierid, @RequestBody SuppliersDTO suppliersDTO) {
        try {
            suppliersDTO.setSupplierid(supplierid);
            suppliersService.updateSuppliers(suppliersDTO);
            return new ResponseEntity<>(suppliersDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-nha-cung-cap/{supplierid}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer supplierid) {
        try {
            suppliersService.deleteBySupplierid(supplierid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
