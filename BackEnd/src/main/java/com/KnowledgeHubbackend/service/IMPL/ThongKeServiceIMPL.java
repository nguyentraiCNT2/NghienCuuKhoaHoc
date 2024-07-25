package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.repository.DocumentRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThongKeServiceIMPL implements ThongKeService {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Override
    public int tongSoUser() {
        return (int) userRepository.count();
    }

    @Override
    public int tongSoTailieu() {
        return (int ) documentRepository.count();
    }
}
