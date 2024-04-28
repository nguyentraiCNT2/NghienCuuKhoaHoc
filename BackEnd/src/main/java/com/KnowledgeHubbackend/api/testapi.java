package com.KnowledgeHubbackend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class testapi {
    @GetMapping("/testapi")
    public String api(){
        return "Thanh Cong";
    }
}
