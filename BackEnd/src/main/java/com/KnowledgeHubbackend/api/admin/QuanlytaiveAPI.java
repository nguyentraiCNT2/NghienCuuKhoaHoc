package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.AuthorOutPut;
import com.KnowledgeHubbackend.api.output.LoadOutPut;
import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.LoadsDTO;
import com.KnowledgeHubbackend.service.LoadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quan-ly-tai-tai-lieu")
public class QuanlytaiveAPI {
    @Autowired
    private LoadService loadService;
    @GetMapping("/hien-thi-tat-ca")
    public LoadOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        LoadOutPut result = new LoadOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(loadService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (loadService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-nguoi-dung/{userid}")
    public LoadOutPut getByUserid(@PathVariable String userid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        LoadOutPut result = new LoadOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(loadService.getByUserId(userid,pageable));
        result.setTotalPage((int) Math.ceil((double) (loadService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-tai-lieu/{documentid}")
    public LoadOutPut getByDocumentid(@PathVariable Integer documentid,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        LoadOutPut result = new LoadOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(loadService.getByDocumentid(documentid,pageable));
        result.setTotalPage((int) Math.ceil((double) (loadService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{loadid}")
    public ResponseEntity<?> getByRoleid(@PathVariable Integer loadid){
        try {
            LoadsDTO loadsDTO = loadService.getById(loadid);
            if (loadsDTO == null) {
                return new ResponseEntity<>("khong thanh cong", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(loadsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( "khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-tai-ve/{loadid}")
    public ResponseEntity<String> deleteByloadid(@PathVariable Integer loadid) {
        try {
            loadService.delete(loadid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tai-ve")
    public ResponseEntity<String> createRole(@RequestBody LoadsDTO loadsDTO) {
        try {
            loadService.create(loadsDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
