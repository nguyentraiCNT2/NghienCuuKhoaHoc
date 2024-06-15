package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.AuthorOutPut;
import com.KnowledgeHubbackend.api.output.DocumentOutput;
import com.KnowledgeHubbackend.dto.*;
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
@RequestMapping("/admin/quan-ly-tai-lieu")
public class QuanlydocumentAPI {
    @Autowired
    private final DocumentService documentService;

    public QuanlydocumentAPI(DocumentService documentService) {
        this.documentService = documentService;
    }

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

    @GetMapping("/hien-thi-theo-tac-gia/{authorid}")
    public DocumentOutput getByAuthorid(@PathVariable Integer authorid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByAuthorID(authorid, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getByAuthorid", result);
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

    @GetMapping("/hien-thi-theo-danh-muc/{menuid}")
    public DocumentOutput getByMenuid(@PathVariable Integer menuid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByMenuid(menuid, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        model.addAttribute("getByAuthorid", result);
        return result;
    }

    @GetMapping("/hien-thi-theo-nha-xuat-ban/{publisherid}")
    public DocumentOutput getByPublisherid(@PathVariable Integer publisherid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByPublisherid(publisherid, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-nha-cung-cap/{supplierid}")
    public DocumentOutput getBySupplierid(@PathVariable Integer supplierid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getBySupplierid(supplierid, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-ten-tai-lieu/{documentname}")
    public DocumentOutput getBySupplierid(@PathVariable String documentname, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getByDocumentname(documentname, pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-luot-xem-tang-dan")
    public DocumentOutput getAllByViewsOrderByDesc(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getAllByViewsOrderByDesc(pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-luot-xem-giam-dan")
    public DocumentOutput getAllByViewsOrderByAsc(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getAllByViewsOrderByAsc(pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-luot-tai-tang-dan")
    public DocumentOutput getAllByCountDownloadOrderByDesc(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getAllByCountDownloadOrderByDesc(pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-luot-tai-giam-dan")
    public DocumentOutput getAllByCountDownloadOrderByAsc(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(documentService.getAllByCountDownloadOrderByAsc(pageable));
        result.setTotalPage((int) Math.ceil((double) (documentService.totalItem()) / limit));
        return result;
    }

    @GetMapping("/hien-thi-theo-id/{documentid}")
    public ResponseEntity<?> getByDuctumentid(@PathVariable Integer documentid) {
        try {
            DocumentDTO documentDTO = documentService.getByDocumentid(documentid);
            return new ResponseEntity<>(documentDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("khong thanh cong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/them-moi-tai-lieu")
    public ResponseEntity<String> createDocument(@RequestParam("documentname") String documentname
            , @RequestParam("file") MultipartFile file, @RequestParam("categoryid") Integer categoryid
            , @RequestParam("description") String description, @RequestParam("status") Boolean status
            , @RequestParam("supplierid") Integer supplierid, @RequestParam("authorID") Integer authorID
            , @RequestParam("publisherid") Integer publisherid, @RequestParam("menuid") Integer menuid
            , @RequestParam("userid") String userid) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryid(categoryid);
        SuppliersDTO suppliersDTO = new SuppliersDTO();
        suppliersDTO.setSupplierid(supplierid);
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorid(authorID);
        PublishersDTO publishersDTO = new PublishersDTO();
        publishersDTO.setPublisherid(publisherid);
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenuid(menuid);
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentname(documentname);
        documentDTO.setCategoryid(categoryDTO);
        documentDTO.setDescription(description);
        documentDTO.setStatus(status);
        documentDTO.setSupplierid(suppliersDTO);
        documentDTO.setViews(0);
        documentDTO.setCountDownload(0);
        documentDTO.setAuthorID(authorDTO);
        documentDTO.setMenuid(menuDTO);
        documentDTO.setPublisherid(publishersDTO);
        try {
            documentService.createDocument(documentDTO, file, userid);
            return new ResponseEntity<>(  " Thêm mới tài liệu "+documentDTO.getDocumentname()+" thành công", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/chinh-sua-thong-tin-tai-lieu/{documentid}")
    public ResponseEntity<String> updateDocument(@PathVariable Integer documentid, @RequestParam("documentname") String documentname
            , @RequestParam("file") MultipartFile file
            , @RequestParam("categoryid") Integer categoryid, @RequestParam("description") String description
            , @RequestParam("status") Boolean status, @RequestParam("supplierid") Integer supplierid
            , @RequestParam("authorID") Integer authorID, @RequestParam("publisherid") Integer publisherid
            , @RequestParam("menuid") Integer menuid, @RequestParam("userid") String userid) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryid(categoryid);
        SuppliersDTO suppliersDTO = new SuppliersDTO();
        suppliersDTO.setSupplierid(supplierid);
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorid(authorID);
        PublishersDTO publishersDTO = new PublishersDTO();
        publishersDTO.setPublisherid(publisherid);
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenuid(menuid);
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentname(documentname);
        documentDTO.setCategoryid(categoryDTO);
        documentDTO.setDescription(description);
        documentDTO.setStatus(status);
        documentDTO.setSupplierid(suppliersDTO);
        documentDTO.setAuthorID(authorDTO);
        documentDTO.setMenuid(menuDTO);
        documentDTO.setPublisherid(publishersDTO);
        try {
            documentDTO.setDocumentid(documentid);
            documentService.updateDocument(documentDTO, file, userid);
            return new ResponseEntity<>(documentDTO + " Cập nhật thành công", HttpStatus.OK);
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
