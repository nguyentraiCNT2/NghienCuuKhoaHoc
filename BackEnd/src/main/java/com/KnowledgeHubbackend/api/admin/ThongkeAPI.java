package com.KnowledgeHubbackend.api.admin;

import com.KnowledgeHubbackend.api.output.ThongkeOutPut;
import com.KnowledgeHubbackend.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/thongke")
public class ThongkeAPI {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/so-lieu")
    public ThongkeOutPut tongSoUserAndDocument() {
        ThongkeOutPut thongkeOutPut = new ThongkeOutPut();
        thongkeOutPut.setTongSoUser(thongKeService.tongSoUser());
        thongkeOutPut.setTongSoTailieu(thongKeService.tongSoTailieu());
        return thongkeOutPut;
    }
}

