package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.AuthorOutPut;
import com.KnowledgeHubbackend.api.output.DocumentOutput;
import com.KnowledgeHubbackend.dto.*;
import com.KnowledgeHubbackend.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/quan-ly-tai-lieu")
public class QuanlydocumentAPI {
    @Autowired
    private final DocumentService documentService;
    @Autowired
    private DocumentUserService documentUserService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private UserService userService;
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
            , @RequestParam("publisherid") Integer publisherid, @RequestParam("genreid") Integer genreid
            , @RequestParam("userid") String userid) {

        UsersDTO dto = new UsersDTO();
        dto.setUserid(userid);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryid(categoryid);
        SuppliersDTO suppliersDTO = new SuppliersDTO();
        suppliersDTO.setSupplierid(supplierid);
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorid(authorID);
        PublishersDTO publishersDTO = new PublishersDTO();
        publishersDTO.setPublisherid(publisherid);
        GenresDTO genresDTO = new GenresDTO();
        genresDTO.setGenreid(genreid);
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentname(documentname);
        documentDTO.setCategoryid(categoryDTO);
        documentDTO.setDescription(description);
        documentDTO.setStatus(status);
        documentDTO.setSupplierid(suppliersDTO);
        documentDTO.setViews(0);
        documentDTO.setCountDownload(0);
        documentDTO.setAuthorID(authorDTO);
        documentDTO.setPublisherid(publishersDTO);
        documentDTO.setGenreid(genresDTO);
        documentDTO.setUpdaterid(dto);
        documentDTO.setUserid(dto);
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
            , @RequestParam("genreid") Integer genreid, @RequestParam("userid") String userid) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryid(categoryid);
        SuppliersDTO suppliersDTO = new SuppliersDTO();
        suppliersDTO.setSupplierid(supplierid);
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorid(authorID);
        PublishersDTO publishersDTO = new PublishersDTO();
        publishersDTO.setPublisherid(publisherid);
        GenresDTO genresDTO = new GenresDTO();
        genresDTO.setGenreid(genreid);
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentname(documentname);
        documentDTO.setCategoryid(categoryDTO);
        documentDTO.setDescription(description);
        documentDTO.setStatus(status);
        documentDTO.setSupplierid(suppliersDTO);
        documentDTO.setAuthorID(authorDTO);
        documentDTO.setPublisherid(publishersDTO);
        documentDTO.setGenreid(genresDTO);
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
            List<DocumentUserDTO> listdocumentuser = documentUserService.getByDocumentid(documentid);
            List<HistoryDTO> listhistory = historyService.getByDocumentid(documentid);
            List<NotificationsDTO> notificationsDTOList = notificationsService.getByDocumentid(documentid);
            if (notificationsDTOList.size() > 0){
                for (NotificationsDTO item: notificationsDTOList){
                    notificationsService.deleteByNotificationid(item.getNotificationid());
                }

            }
            if (listhistory.size() ==0){
                for (DocumentUserDTO documentUserDTO : listdocumentuser) {
                    documentUserService.delete(documentUserDTO.getDocumentuserid());
                }
                documentService.deleteByDocumentid(documentid);
            }
           else {
               for (HistoryDTO historyDTO : listhistory) {
                   historyService.deleteByHistoryid(historyDTO.getHistoryid());
               }
                for (DocumentUserDTO documentUserDTO : listdocumentuser) {
                    documentUserService.delete(documentUserDTO.getDocumentuserid());
                }
                documentService.deleteByDocumentid(documentid);
            }
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
