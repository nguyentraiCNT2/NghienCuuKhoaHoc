package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.PublishersOutput;
import com.KnowledgeHubbackend.api.output.SuppliersOutPut;
import com.KnowledgeHubbackend.dto.PublishersDTO;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import com.KnowledgeHubbackend.service.PublishersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quan-ly-nha-xuat-ban")
public class QuanlynhaxuatbanAPI {
    @Autowired
    private final PublishersService publishersService;

    public QuanlynhaxuatbanAPI(PublishersService publishersService) {
        this.publishersService = publishersService;
    }
    @GetMapping("/hien-thi-tat-ca")
    public PublishersOutput getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        PublishersOutput result = new PublishersOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(publishersService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (publishersService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }

    @GetMapping("/hien-thi-theo-ten-nha-xuat-ban/{publishername}")
    public PublishersOutput getByPublishername(@PathVariable String publishername, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        PublishersOutput result = new PublishersOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(publishersService.getByPublishername(publishername,pageable));
        result.setTotalPage((int) Math.ceil((double) (publishersService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{publisherid}")
    public ResponseEntity<?> getByRoleid(@PathVariable Integer publisherid){
        try {
            PublishersDTO publishersDTO = publishersService.getByPublisherid(publisherid);

            return new ResponseEntity<>(publishersDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( "khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/them-moi-nha-xuat-ban")
    public ResponseEntity<String> createRole(@RequestBody PublishersDTO publishersDTO) {
        try {
            publishersService.createPublisher(publishersDTO);
            return new ResponseEntity<>("Thêm thành công" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/chinh-sua-thong-tin-nha-xuat-ban/{publisherid}")
    public ResponseEntity<String> updateRole(@PathVariable Integer publisherid, @RequestBody PublishersDTO publishersDTO) {
        try {
            publishersDTO.setPublisherid(publisherid);
            publishersService.updatePublisher(publishersDTO);
            return new ResponseEntity<>(publishersDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-nha-xuat-ban/{publisherid}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer publisherid) {
        try {
            publishersService.deleteByPublisherid(publisherid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
