package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.MenuOutPut;
import com.KnowledgeHubbackend.api.output.RolesOutPut;
import com.KnowledgeHubbackend.dto.MenuDTO;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.service.MenuService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quan-ly-danh-muc")
public class QuanLyMenuAPI {
    @Autowired
    private final MenuService menuService;

    public QuanLyMenuAPI(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/them-moi-danh-muc")
    public ResponseEntity<String> createMenu(@RequestBody MenuDTO menuDTO) {
        try {
            menuService.createMenu(menuDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/hien-thi-tat-ca")
    public MenuOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        MenuOutPut result = new MenuOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(menuService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (menuService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-ten-danh-muc/{menuname}")
    public MenuOutPut getByMenuname(@PathVariable String menuname,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        MenuOutPut result = new MenuOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(menuService.getByMenuName(menuname,pageable));
        result.setTotalPage((int) Math.ceil((double) (menuService.totalItem()) / limit));
        model.addAttribute("getByMenuname", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{menuid}")
    public ResponseEntity<?> getByMenuid(@PathVariable Integer menuid){
        try {
            MenuDTO menu = menuService.getByMenuid(menuid);
            if (menu==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+menuid, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-danh-muc/{menuid}")
    public ResponseEntity<String> updateMenu(@PathVariable Integer menuid, @RequestBody MenuDTO menuDTO) {
        try {
            menuDTO.setMenuid(menuid);
            menuService.updateMenu(menuDTO);
            return new ResponseEntity<>(menuDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-danh-muc/{menuid}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer menuid) {
        try {
            menuService.deleteByMenuid(menuid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
