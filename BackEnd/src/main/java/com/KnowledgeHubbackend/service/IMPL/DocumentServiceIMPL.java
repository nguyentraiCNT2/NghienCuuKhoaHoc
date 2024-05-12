package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.*;
import com.KnowledgeHubbackend.entity.*;
import com.KnowledgeHubbackend.repository.*;
import com.KnowledgeHubbackend.service.DocumentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceIMPL implements DocumentService {
    @Value("D:/KnowledgeHub/uploaddocument/")
    // Đường dẫn để lưu ảnh, có thể đặt trong file properties/application.yml
    private String imageSavePath;
    @Autowired
    private final DocumentRepository documentRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final PublishersRepository publishersRepository;
    @Autowired
    private final SuppliersRepository suppliersRepository;
    @Autowired
    private final MenuRepository menuRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final HistoryRepository historyRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final NotificationsRepository notificationsRepository;
    public DocumentServiceIMPL(DocumentRepository documentRepository, ModelMapper modelMapper, AuthorRepository authorRepository, PublishersRepository publishersRepository, SuppliersRepository suppliersRepository, MenuRepository menuRepository, CategoryRepository categoryRepository, HistoryRepository historyRepository, UserRepository userRepository, NotificationsRepository notificationsRepository) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
        this.publishersRepository = publishersRepository;
        this.suppliersRepository = suppliersRepository;
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.notificationsRepository = notificationsRepository;
    }


    @Override
    public List<DocumentDTO> getAll(Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        List<DocumentEntity> documentEntities = documentRepository.findAll(pageable).getContent();
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getAllByViewsOrderByDesc(Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        List<DocumentEntity> documentEntities = documentRepository.findAllByViewsOrderByDesc(pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getAllByViewsOrderByAsc(Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        List<DocumentEntity> documentEntities = documentRepository.findAllByViewsOrderByAsc(pageable);
        for (DocumentEntity item: documentEntities
        ) {

            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getAllByCountDownloadOrderByDesc(Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        List<DocumentEntity> documentEntities = documentRepository.findAllByCountDownloadOrderByDesc(pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getAllByCountDownloadOrderByAsc(Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        List<DocumentEntity> documentEntities = documentRepository.findAllByCountDownloadOrderByAsc(pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getByDocumentname(String documentname, Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        List<DocumentEntity> documentEntities = documentRepository.findByDocumentname(documentname,pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getByCategoryid(Integer categoryid, Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        CategoryEntity category = categoryRepository.findByCategoryid(categoryid).orElseThrow(null);
        List<DocumentEntity> documentEntities = documentRepository.findByCategoryid(category,pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getByAuthorID(Integer authorID, Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        AuthorEntity author = authorRepository.findByAuthorid(authorID).orElseThrow(null);
        List<DocumentEntity> documentEntities = documentRepository.findByAuthorID(author,pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getByPublisherid(Integer publisherid, Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        PublishersEntity publishers = publishersRepository.findByPublisherid(publisherid).orElseThrow(null);
        List<DocumentEntity> documentEntities = documentRepository.findByPublisherid(publishers,pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getByMenuid(Integer menuid, Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        MenuEntity menu = menuRepository.findByMenuid(menuid).orElseThrow(null);
        List<DocumentEntity> documentEntities = documentRepository.findByMenuid(menu,pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentDTO> getBySupplierid(Integer supplierid, Pageable pageable) {
        List<DocumentDTO> results = new ArrayList<>();
        SuppliersEntity suppliers = suppliersRepository.findBySupplierid(supplierid).orElseThrow(null);
        List<DocumentEntity> documentEntities = documentRepository.findBySupplierid(suppliers,pageable);
        for (DocumentEntity item: documentEntities
        ) {
            DocumentDTO dto = modelMapper.map(item,DocumentDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public DocumentDTO getByDocumentid(Integer documentid) {
        try {
            DocumentEntity document = documentRepository.findByDocumentid(documentid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + documentid));
            return modelMapper.map(document, DocumentDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByDocumentid(Integer documentid)throws IOException {
        DocumentEntity existingDocument = documentRepository.findByDocumentid(documentid)
                .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + documentid));
        if (existingDocument.getFileURL() != null && !existingDocument.getFileURL().isEmpty()) {
            String oldFilePath = imageSavePath + existingDocument.getFileURL();
            Files.deleteIfExists(Paths.get(oldFilePath));
        }
        documentRepository.deleteByDocumentid(documentid);
    }

    @Override
    public void createDocument(DocumentDTO documentDTO, MultipartFile file, String userid) throws IOException {
        if (documentDTO != null) {
            UsersEntity users = userRepository.findByUserid(userid).orElse(null);
            DocumentEntity document = modelMapper.map(documentDTO, DocumentEntity.class);
            CategoryEntity category = categoryRepository.findByCategoryid(documentDTO.getCategoryid().getCategoryid()).orElse(null);
            MenuEntity menu = menuRepository.findByMenuid(documentDTO.getMenuid().getMenuid()).orElse(null);
            AuthorEntity author = authorRepository.findByAuthorid(documentDTO.getAuthorID().getAuthorid()).orElse(null);
            SuppliersEntity suppliers = suppliersRepository.findBySupplierid(documentDTO.getSupplierid().getSupplierid()).orElse(null);
            PublishersEntity publishers = publishersRepository.findByPublisherid(documentDTO.getPublisherid().getPublisherid()).orElse(null);
            document.setCategoryid(category);
            document.setMenuid(menu);
            document.setAuthorID(author);
            document.setSupplierid(suppliers);
            document.setPublisherid(publishers);
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = imageSavePath + filename;
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            document.setFileURL(filename);
          DocumentEntity saveDocument =  documentRepository.save(document);
            LocalDate currentDate = LocalDate.now();
            Date currentSqlDate = Date.valueOf(currentDate);
            String description=users.getUsername()+" Đã thêm thông tin tài liệu có tên "+saveDocument.getDocumentname();
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setDocumentid(saveDocument);
            historyEntity.setUserid(users);
            historyEntity.setDateupdate(currentSqlDate);
            historyEntity.setDescription(description);
            historyRepository.save(historyEntity);
            NotificationsEntity notificationsEntity = new NotificationsEntity();
            String descriptionnotification =users.getUsername()+" Đã thêm thông tin tài liệu có tên "+saveDocument.getDocumentname()
                    +"Đang chờ xác nhận!";
            notificationsEntity.setDocumentid(saveDocument);
            notificationsEntity.setUserid(users);
            notificationsEntity.setDateadd(currentSqlDate);
            notificationsEntity.setDescription(descriptionnotification);
            notificationsRepository.save(notificationsEntity);
        }
    }

    @Override
    public void updateDocument(DocumentDTO documentDTO,MultipartFile file, String userid) throws IOException {
        try {
            UsersEntity users = userRepository.findByUserid(userid).orElse(null);
            DocumentEntity existingDocument = documentRepository.findByDocumentid(documentDTO.getDocumentid())
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + documentDTO.getDocumentid()));
            CategoryEntity category = categoryRepository.findByCategoryid(documentDTO.getCategoryid().getCategoryid()).orElse(null);
            SuppliersEntity suppliers = suppliersRepository.findBySupplierid(documentDTO.getSupplierid().getSupplierid()).orElse(null);
            PublishersEntity publishers = publishersRepository.findByPublisherid(documentDTO.getPublisherid().getPublisherid()).orElse(null);
            AuthorEntity author = authorRepository.findByAuthorid(documentDTO.getAuthorID().getAuthorid()).orElse(null);
            MenuEntity menu = menuRepository.findByMenuid(documentDTO.getMenuid().getMenuid()).orElse(null);
            String fileurl = existingDocument.getFileURL();
            Integer Views = existingDocument.getViews();
            Integer countDown = existingDocument.getCountDownload();
            CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);
            SuppliersDTO suppliersDTO = modelMapper.map(suppliers,SuppliersDTO.class);
            PublishersDTO publishersDTO = modelMapper.map(publishers,PublishersDTO.class);
            AuthorDTO authorDTO = modelMapper.map(author,AuthorDTO.class);
            MenuDTO menuDTO = modelMapper.map(menu,MenuDTO.class);
            documentDTO.setPublisherid(publishersDTO);
            documentDTO.setSupplierid(suppliersDTO);
            documentDTO.setAuthorID(authorDTO);
            documentDTO.setMenuid(menuDTO);
            documentDTO.setCategoryid(categoryDTO);
            if (!file.isEmpty()){
                // Xóa tệp tin cũ nếu có
                if (existingDocument.getFileURL() != null && !existingDocument.getFileURL().isEmpty()) {
                    String oldFilePath = imageSavePath + existingDocument.getFileURL();
                    Files.deleteIfExists(Paths.get(oldFilePath));
                }

                modelMapper.map(documentDTO, existingDocument);
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String filePath = imageSavePath + filename;
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                existingDocument.setFileURL(filename);

                existingDocument.setCountDownload(countDown);
                existingDocument.setViews(Views);
                DocumentEntity saveDocument =  documentRepository.save(existingDocument);
                LocalDate currentDate = LocalDate.now();
                Date currentSqlDate = Date.valueOf(currentDate);
                String description=users.getUsername()+" Đã sửa thông tin tài liệu "+saveDocument.getDocumentname();
                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setDocumentid(saveDocument);
                historyEntity.setUserid(users);
                historyEntity.setDateupdate(currentSqlDate);
                historyEntity.setDescription(description);
                historyRepository.save(historyEntity);
                NotificationsEntity notificationsEntity = new NotificationsEntity();
                String descriptionnotification =users.getUsername()+" Đã thêm thông tin tài liệu có tên "+saveDocument.getDocumentname()
                        +"Đang chờ xác nhận!";
                notificationsEntity.setDocumentid(saveDocument);
                notificationsEntity.setUserid(users);
                notificationsEntity.setDateadd(currentSqlDate);
                notificationsEntity.setDescription(descriptionnotification);
                notificationsRepository.save(notificationsEntity);
            }
            else {
                modelMapper.map(documentDTO, existingDocument);
                existingDocument.setFileURL(fileurl);
                existingDocument.setCountDownload(countDown);
                existingDocument.setViews(Views);
                DocumentEntity saveDocument =    documentRepository.save(existingDocument);
                LocalDate currentDate = LocalDate.now();
                Date currentSqlDate = Date.valueOf(currentDate);
                String description=users.getUsername()+" Đã sửa thông tin tài liệu "+saveDocument.getDocumentname();
                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setDocumentid(saveDocument);
                historyEntity.setUserid(users);
                historyEntity.setDateupdate(currentSqlDate);
                historyEntity.setDescription(description);
                historyRepository.save(historyEntity);
                NotificationsEntity notificationsEntity = new NotificationsEntity();
                String descriptionnotification =users.getUsername()+" Đã thêm thông tin tài liệu có tên "+saveDocument.getDocumentname()
                        +"Đang chờ xác nhận!";
                notificationsEntity.setDocumentid(saveDocument);
                notificationsEntity.setUserid(users);
                notificationsEntity.setDateadd(currentSqlDate);
                notificationsEntity.setDescription(descriptionnotification);
                notificationsRepository.save(notificationsEntity);
            }
        }catch (Exception e){
        throw  new RuntimeException(e.getMessage());
        }

    }
}
