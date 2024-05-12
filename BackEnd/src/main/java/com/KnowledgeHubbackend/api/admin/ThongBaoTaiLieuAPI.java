package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.NotificationsOutPut;
import com.KnowledgeHubbackend.service.NotificationsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/thong-bao-tai-lieu")
public class ThongBaoTaiLieuAPI {
    @Autowired
    private final NotificationsService notificationsService;
    public ThongBaoTaiLieuAPI(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }
    @GetMapping("/hien-thi-tat-ca")
    public NotificationsOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        NotificationsOutPut result = new NotificationsOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(notificationsService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (notificationsService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/hien-thi-theo-ma-nguoi-dung/{userid}")
    public NotificationsOutPut getByUserid(@PathVariable String userid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        NotificationsOutPut result = new NotificationsOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(notificationsService.getByUserid(userid,pageable));
        result.setTotalPage((int) Math.ceil((double) (notificationsService.totalItem()) / limit));
        return result;
    }  @GetMapping("/hien-thi-theo-ma-tai-lieu/{documetid}")
    public NotificationsOutPut getByDocumentid(@PathVariable Integer documetid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        NotificationsOutPut result = new NotificationsOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(notificationsService.getByDocumentid(documetid,pageable));
        result.setTotalPage((int) Math.ceil((double) (notificationsService.totalItem()) / limit));
        return result;
    }

    @Transactional
    @DeleteMapping("/xoa-thong-tin-thong-bao/{notificationsid}")
    public ResponseEntity<String> deleteDocument(@PathVariable Integer notificationsid) {
        try {
            notificationsService.deleteByNotificationid(notificationsid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
