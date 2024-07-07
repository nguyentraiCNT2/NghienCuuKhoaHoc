package com.KnowledgeHubbackend.api.user;

import com.KnowledgeHubbackend.api.output.DocumentOutput;
import com.KnowledgeHubbackend.api.output.FavoriteListOutPut;
import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.DocumentUserDTO;
import com.KnowledgeHubbackend.service.DocumentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private DocumentUserService documentUserService;
    @GetMapping("/hien-thi-tai-lieu-da-doc")
    public DocumentOutput getALL(@RequestParam("token") String token, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        DocumentOutput result = new DocumentOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<DocumentUserDTO> documentUserDTO =  documentUserService.getByUserid(token,pageable);
        List<DocumentDTO>documentDTOS = new ArrayList<>();
        for (DocumentUserDTO item : documentUserDTO) {
            documentDTOS.add(item.getDocumentid());
        }
        result.setListResult(documentDTOS);
        result.setTotalPage((int) Math.ceil((double) (documentUserService.totalItem()) / limit));
        model.addAttribute("getALL", result);
        return result;
    }
}
