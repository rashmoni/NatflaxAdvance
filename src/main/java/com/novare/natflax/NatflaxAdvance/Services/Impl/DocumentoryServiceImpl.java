package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Documentory;
import com.novare.natflax.NatflaxAdvance.Entity.Movie;
import com.novare.natflax.NatflaxAdvance.Exceptions.ResourceNotFoundException;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentoryDto;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Repositories.DocumentoryRepo;
import com.novare.natflax.NatflaxAdvance.Repositories.MovieRepo;
import com.novare.natflax.NatflaxAdvance.Services.DocumentoryService;
import com.novare.natflax.NatflaxAdvance.Services.IStorageService;
import com.novare.natflax.NatflaxAdvance.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
public class DocumentoryServiceImpl implements DocumentoryService {

    @Autowired
    private DocumentoryRepo documentoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IStorageService fileSystemStorageService;


    @Override
    public DocumentoryDto createDocumentory(DocumentoryDto documentoryDto) {
        String logMessage;
        if(documentoryDto.getBanner_url() != null || documentoryDto.getThumbnail_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(documentoryDto.getBanner_url());
            String thumbDataBytes = FileUtil.getImageFromBase64(documentoryDto.getThumbnail_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL + " ---" + complete_thumb_URL;
            log.info(logMessage);
            documentoryDto.setBanner_url(complete_banner_URL);
            documentoryDto.setThumbnail_url(complete_thumb_URL);
        }
        Documentory documentory= this.dtoToDocumentory(documentoryDto);
        Documentory savedDocumentory = this.documentoryRepo.save(documentory);
        logMessage = "Documentory is saved in database";
        log.info(logMessage);
        return this.documentoryToDto(savedDocumentory);
    }

    @Override
    public void deleteDocumentory(Integer did) {
        Documentory documentory = this.documentoryRepo.findById(did).orElseThrow(() -> new ResourceNotFoundException("Documentory", "Id", did));
        this.documentoryRepo.delete(documentory);
    }

    @Override
    public List<DocumentoryDto> getAllDocumentoies() {
        List<Documentory> movies= this.documentoryRepo.findAll();
        List<DocumentoryDto> documentoryDtos = movies.stream().map(user -> this.documentoryToDto(user)).collect(Collectors.toList());
        return documentoryDtos;
    }

    @Override
    public DocumentoryDto getDocumentoryById(Integer documentoryId) {
        Documentory documentory = this.documentoryRepo.findById(documentoryId).orElseThrow(() -> new ResourceNotFoundException("Documentory", "Id", documentoryId));
        return this.documentoryToDto(documentory);
    }
    @Override
    public DocumentoryDto documentoryToDto(Documentory documentory) {
        DocumentoryDto documentoryDto = this.modelMapper.map(documentory, DocumentoryDto.class);
        return documentoryDto;
    }

    @Override
    public DocumentoryDto updateDocumentory(DocumentoryDto documentoryDto) {

        String logMessage;

        Documentory documentory = this.documentoryRepo.findByTitle(documentoryDto.getTitle()).orElseThrow(() -> new ResourceNotFoundException("Documentory", "Id", 0));

        Integer documentoryId = documentory.getDocument_id();

        if(documentoryDto.getBanner_url() != null || documentoryDto.getThumbnail_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(documentoryDto.getBanner_url());
            String thumbDataBytes = FileUtil.getImageFromBase64(documentoryDto.getThumbnail_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL + " ---" + complete_thumb_URL;
            log.info(logMessage);
            documentoryDto.setBanner_url(complete_banner_URL);
            documentoryDto.setThumbnail_url(complete_thumb_URL);
        }
        documentory = this.dtoToDocumentory(documentoryDto);
        documentory.setDocument_id(documentoryId);

        Documentory savedDocumentory= this.documentoryRepo.save(documentory);
        logMessage = "Documentory is updated!";
        log.info(logMessage);
        return this.documentoryToDto(savedDocumentory);
    }

    private Documentory dtoToDocumentory(DocumentoryDto documentoryDto) {
        Documentory documentory = this.modelMapper.map(documentoryDto, Documentory.class);
        return documentory;
    }

}

