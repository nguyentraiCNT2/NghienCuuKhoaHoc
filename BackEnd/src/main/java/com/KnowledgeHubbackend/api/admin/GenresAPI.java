package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.GenresOUTPUT;
import com.KnowledgeHubbackend.api.output.GenresOUTPUT;
import com.KnowledgeHubbackend.dto.GenresDTO;
import com.KnowledgeHubbackend.dto.GenresDTO;
import com.KnowledgeHubbackend.service.GenresService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/genres")
public class GenresAPI {
    @Autowired
    private GenresService genresService;


    @PostMapping("/them-moi-loai-chung")
    public ResponseEntity<String> createCategory(@RequestBody GenresDTO dto) {
        try {
            genresService.save(dto);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/hien-thi-tat-ca")
    public GenresOUTPUT getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        GenresOUTPUT result = new GenresOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(genresService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (genresService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/hien-thi-theo-ten-loai-chung/{name}")
    public GenresOUTPUT getByCategoryname(@PathVariable String name, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        GenresOUTPUT result = new GenresOUTPUT();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(genresService.findByGenrename(name,pageable));
        result.setTotalPage((int) Math.ceil((double) (genresService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{id}")
    public ResponseEntity<?> getByCategoryid(@PathVariable Integer id){
        try {
            GenresDTO dto = genresService.getByGenreid(id);
            if (dto==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-loai-chung/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody GenresDTO genresDTO) {
        try {
            genresDTO.setGenreid(id);
            genresService.update(genresDTO);
            return new ResponseEntity<>(genresDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-loai-chung/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            genresService.deleteByGenreid(id);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
