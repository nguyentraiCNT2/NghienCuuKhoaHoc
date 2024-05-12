package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.HistoryOutPut;
import com.KnowledgeHubbackend.service.HistoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quan-ly-hoat-dong")
public class QuanLyLichSuHoatDongAPI {
    @Autowired
    private final HistoryService historyService;

    public QuanLyLichSuHoatDongAPI(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/hien-thi-tat-ca")
    public HistoryOutPut getALL(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        HistoryOutPut result = new HistoryOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(historyService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (historyService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/hien-thi-theo-ma-nguoi-dung/{userid}")
    public HistoryOutPut getByUserid(@PathVariable String userid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        HistoryOutPut result = new HistoryOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(historyService.getByUserid(userid,pageable));
        result.setTotalPage((int) Math.ceil((double) (historyService.totalItem()) / limit));
        return result;
    }  @GetMapping("/hien-thi-theo-ma-tai-lieu/{documetid}")
    public HistoryOutPut getByDocumentid(@PathVariable Integer documetid, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        HistoryOutPut result = new HistoryOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(historyService.getByDocumentid(documetid,pageable));
        result.setTotalPage((int) Math.ceil((double) (historyService.totalItem()) / limit));
        return result;
    }

    @Transactional
    @DeleteMapping("/xoa-thong-tin-lich-su/{historyid}")
    public ResponseEntity<String> deleteDocument(@PathVariable Integer historyid) {
        try {
            historyService.deleteByHistoryid(historyid);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
