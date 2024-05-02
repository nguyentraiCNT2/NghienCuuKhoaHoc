package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.AuthorOutPut;
import com.KnowledgeHubbackend.api.output.DocumentOutput;
import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.service.DocumentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/quan-ly-tai-lieu")
public class QuanLyTaiLieuAPI {
    @Autowired
    private final DocumentService documentService;

    public QuanLyTaiLieuAPI(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/hien-thi-tat-ca")
    public DocumentOutput getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }

    @GetMapping("/hien-thi-theo-tac-gia/{authorid}")
    public DocumentOutput getByAuthorid(@PathVariable Integer authorid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByAuthorID(authorid,pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getByAuthorid", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{authorid}")
    public ResponseEntity<?> getByDuctumentid(@PathVariable Integer documentid){
        try {
            DocumentDTO documentDTO = documentService.getByDocumentid(documentid);

            return new ResponseEntity<>(documentDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( "khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/them-moi-tai-lieu")
    public ResponseEntity<Void> createDocument(@RequestBody DocumentDTO documentDTO, @RequestParam("file") MultipartFile file) {
        try {
            documentService.createDocument(documentDTO,file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/chinh-sua-thong-tin-tai-lieu/{documentid}")
    public ResponseEntity<String> updateDocument(@PathVariable Integer documentid, @RequestBody DocumentDTO documentDTO) {
        try {
            documentDTO.setDocumentid(documentid);
            documentService.updateDocument(documentDTO);
            return new ResponseEntity<>(documentDTO+" Cập nhật thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/xoa-thong-tin-tai-lieu/{documentid}")
    public ResponseEntity<String> deleteDocument(@PathVariable Integer documentid) {
        try {
            documentService.deleteByDocumentid(documentid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
