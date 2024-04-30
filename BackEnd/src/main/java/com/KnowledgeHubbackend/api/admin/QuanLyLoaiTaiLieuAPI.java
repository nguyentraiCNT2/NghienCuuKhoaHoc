package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.CategoryOutPut;
import com.KnowledgeHubbackend.api.output.MenuOutPut;
import com.KnowledgeHubbackend.dto.CategoryDTO;
import com.KnowledgeHubbackend.dto.MenuDTO;
import com.KnowledgeHubbackend.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quan-ly-loai-tai-lieu")
public class QuanLyLoaiTaiLieuAPI {
    @Autowired
    private final CategoryService categoryService;


    public QuanLyLoaiTaiLieuAPI(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/them-moi-loai-tai-lieu")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.createCategory(categoryDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/hien-thi-tat-ca")
    public CategoryOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        CategoryOutPut result = new CategoryOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(categoryService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-ten-loai-tai-lieu/{categoryname}")
    public CategoryOutPut getByCategoryname(@PathVariable String categoryname,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        CategoryOutPut result = new CategoryOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(categoryService.getByCategoryname(categoryname,pageable));
        result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
        model.addAttribute("getByMenuname", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{categoryid}")
    public ResponseEntity<?> getByCategoryid(@PathVariable Integer categoryid){
        try {
            CategoryDTO dto = categoryService.getByCategoryid(categoryid);
            if (dto==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+categoryid, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-loai-tai-lieu/{categoryid}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer categoryid, @RequestBody CategoryDTO categoryDTO) {
        try {
            categoryDTO.setCategoryid(categoryid);
            categoryService.updateCategory(categoryDTO);
            return new ResponseEntity<>(categoryDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-loai-tai-lieu/{categoryid}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryid) {
        try {
            categoryService.deleteByCategoryid(categoryid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
