package com.KnowledgeHubbackend.api.user;

import com.KnowledgeHubbackend.api.output.DocumentOutput;
import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.DocumentUserDTO;
import com.KnowledgeHubbackend.service.DocumentService;
import com.KnowledgeHubbackend.service.DocumentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
public class DocumentAPI {
    @Autowired
    private  DocumentService documentService;
    @Autowired
    private DocumentUserService documentUserService;
    @GetMapping("/hien-thi-tat-ca")
    public DocumentOutput getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-the-loai/{categoryid}")
    public DocumentOutput getByCategoryid(@PathVariable Integer categoryid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByCategoryid(categoryid, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getByAuthorid", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-loai-chung/{id}")
    public DocumentOutput getByGenreid(@PathVariable Integer id, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByGenreid(id, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getByAuthorid", result);
        return result;
    }
    @GetMapping("/hien-thi-theo-id/{documentid}")
    public ResponseEntity<?> getByDuctumentid(@PathVariable Integer documentid, @RequestParam("token") String token) {
        try {
            DocumentDTO documentDTO = documentService.getByDocumentid(documentid);
            DocumentUserDTO documentUserDTO = new DocumentUserDTO();
            documentUserDTO.setDocumentid(documentDTO);
            documentUserService.create(documentUserDTO, token);
            return new ResponseEntity<>(documentDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
