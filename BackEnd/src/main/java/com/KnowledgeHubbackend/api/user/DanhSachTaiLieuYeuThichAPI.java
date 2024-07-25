package com.KnowledgeHubbackend.api.user;

import com.KnowledgeHubbackend.api.output.CategoryOutPut;
import com.KnowledgeHubbackend.api.output.FavoriteListOutPut;
import com.KnowledgeHubbackend.dto.CategoryDTO;
import com.KnowledgeHubbackend.dto.FavoriteListDTO;
import com.KnowledgeHubbackend.service.FavoriteListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/user/danh-sach-yeu-thich")
public class DanhSachTaiLieuYeuThichAPI {
    @Autowired
    private final FavoriteListService favoriteListService;

    public DanhSachTaiLieuYeuThichAPI(FavoriteListService favoriteListService) {
        this.favoriteListService = favoriteListService;
    }
    @PostMapping("/them-vao-danh-sach-yeu-thich")
    public ResponseEntity<String> createCategory(@RequestBody FavoriteListDTO favoriteListDTO) {
        try {
            LocalDate currentDate = LocalDate.now();
            Date currentSqlDate = Date.valueOf(currentDate);
            favoriteListDTO.setDateAdd(currentSqlDate);
            favoriteListService.createFavoriteList(favoriteListDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/hien-thi-tat-ca")
    public FavoriteListOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        FavoriteListOutPut result = new FavoriteListOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(favoriteListService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (favoriteListService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-theo-ngay-them/{dateadd}")
    public FavoriteListOutPut getByCategoryname(@PathVariable Date dateadd,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        FavoriteListOutPut result = new FavoriteListOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(favoriteListService.getByDateAdd(dateadd,pageable));
        result.setTotalPage((int) Math.ceil((double) (favoriteListService.totalItem()) / limit));
        model.addAttribute("getByMenuname", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-ma-nguoi-dung")
    public FavoriteListOutPut getByCategoryname(@RequestParam("token") String token,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        FavoriteListOutPut result = new FavoriteListOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(favoriteListService.getByUserid(token,pageable));
        result.setTotalPage((int) Math.ceil((double) (favoriteListService.totalItem()) / limit));
        model.addAttribute("getByMenuname", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-ma-tai-lieu/{documentid}")
    public FavoriteListOutPut getByDocumentid(@PathVariable Integer documentid,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        FavoriteListOutPut result = new FavoriteListOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(favoriteListService.getByDocumentid(documentid,pageable));
        result.setTotalPage((int) Math.ceil((double) (favoriteListService.totalItem()) / limit));
        model.addAttribute("getByDocumentid", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{favoriteListid}")
    public ResponseEntity<?> getByCategoryid(@PathVariable Integer favoriteListid){
        try {
            FavoriteListDTO dto = favoriteListService.getByFavoriteListid(favoriteListid);
            if (dto==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+favoriteListid, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-danh-sach-tai-lieu/{favoriteListid}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer favoriteListid, @RequestBody FavoriteListDTO favoriteListDTO) {
        try {
            favoriteListDTO.setFavoriteListid(favoriteListid);
            favoriteListService.updateFavoriteList(favoriteListDTO);
            return new ResponseEntity<>(favoriteListDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-danh-sach-tai-lieu/{favoriteListid}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer favoriteListid) {
        try {
            favoriteListService.deleteByFavoriteListid(favoriteListid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
