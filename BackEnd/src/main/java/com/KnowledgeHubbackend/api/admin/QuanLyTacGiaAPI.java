package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.AuthorOutPut;
import com.KnowledgeHubbackend.api.output.SuppliersOutPut;
import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import com.KnowledgeHubbackend.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quan-ly-tac-gia")
public class QuanLyTacGiaAPI {
    @Autowired
    private final AuthorService authorService;

    public QuanLyTacGiaAPI(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/hien-thi-tat-ca")
    public AuthorOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        AuthorOutPut result = new AuthorOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(authorService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (authorService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }

    @GetMapping("/hien-thi-theo-ten-tac-gia/{suppliersname}")
    public AuthorOutPut getBySuppliername(@PathVariable String authorname, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        AuthorOutPut result = new AuthorOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(authorService.getByAuthorname(authorname,pageable));
        result.setTotalPage((int) Math.ceil((double) (authorService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{authorid}")
    public ResponseEntity<?> getByRoleid(@PathVariable Integer authorid){
        try {
            AuthorDTO authorDTO = authorService.getByAuthorid(authorid);

            return new ResponseEntity<>(authorDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( "khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/them-moi-nha-tac-gia")
    public ResponseEntity<String> createRole(@RequestBody AuthorDTO authorDTO) {
        try {
            authorService.createAuthor(authorDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-tac-gia/{authorid}")
    public ResponseEntity<String> updateRole(@PathVariable Integer authorid, @RequestBody AuthorDTO authorDTO) {
        try {
            authorDTO.setAuthorid(authorid);
            authorService.updateAuthor(authorDTO);
            return new ResponseEntity<>(authorDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-tac-gia/{authorid}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer authorid) {
        try {
            authorService.deleteByAuthorid(authorid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
