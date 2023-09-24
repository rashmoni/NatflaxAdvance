package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Documentary;
import com.novare.natflax.NatflaxAdvance.Exceptions.ResourceNotFoundException;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentaryDto;
import com.novare.natflax.NatflaxAdvance.Repositories.DocumentoryRepo;
import com.novare.natflax.NatflaxAdvance.Services.DocumentaryService;
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
public class DocumentaryServiceImpl implements DocumentaryService {

    @Autowired
    private DocumentoryRepo documentoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IStorageService fileSystemStorageService;


    @Override
    public DocumentaryDto createDocumentary(DocumentaryDto documentaryDto) {
        String logMessage;

        if(documentaryDto.getBanner_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(documentaryDto.getBanner_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String baseURL = "http://20.240.55.130:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL + " ---";
            log.info(logMessage);
            documentaryDto.setBanner_url(complete_banner_URL);
        }

        if(documentaryDto.getThumbnail_url() != null){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String thumbDataBytes = FileUtil.getImageFromBase64(documentaryDto.getThumbnail_url());
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is: " + complete_thumb_URL;
            log.info(logMessage);
            documentaryDto.setThumbnail_url(complete_thumb_URL);
        }

        Documentary documentary= this.dtoToDocumentary(documentaryDto);
        Documentary savedDocumentary = this.documentoryRepo.save(documentary);
        logMessage = "Documentary is saved in database";
        log.info(logMessage);
        return this.documentaryToDto(savedDocumentary);
    }

    @Override
    public void deleteDocumentary(Integer did) {
        Documentary documentary = this.documentoryRepo.findById(did).orElseThrow(() -> new ResourceNotFoundException("Documentary", "Id", did));
        this.documentoryRepo.delete(documentary);
    }

    @Override
    public List<DocumentaryDto> getAllDocumentaries() {
        List<Documentary> movies= this.documentoryRepo.findAll();
        List<DocumentaryDto> documentaryDtos = movies.stream().map(user -> this.documentaryToDto(user)).collect(Collectors.toList());
        return documentaryDtos;
    }

    @Override
    public DocumentaryDto getDocumentaryById(Integer documentaryId) {
        Documentary documentary = this.documentoryRepo.findById(documentaryId).orElseThrow(() -> new ResourceNotFoundException("Documentary", "Id", documentaryId));
        return this.documentaryToDto(documentary);
    }
    @Override
    public DocumentaryDto documentaryToDto(Documentary documentary) {
        DocumentaryDto documentaryDto = this.modelMapper.map(documentary, DocumentaryDto.class);
        return documentaryDto;
    }

    @Override
    public DocumentaryDto updateDocumentary(DocumentaryDto documentaryDto) {

        String logMessage;

        Integer documentaryID =  documentaryDto.getId();

        Documentary documentary = this.documentoryRepo.findById(documentaryID).orElseThrow(() -> new ResourceNotFoundException("Documentary", "Id", 0));

        if(!documentaryDto.getBanner_url().contains(".png")){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String bannerDataBytes = FileUtil.getImageFromBase64(documentaryDto.getBanner_url());
            byte [] bannerDecodedBytes = Base64.decodeBase64(bannerDataBytes);
            String bannerURL = this.fileSystemStorageService.storeBase64(bannerDecodedBytes);
            String baseURL = "http://20.240.55.130:9090/files/";
            String complete_banner_URL = baseURL + bannerURL;

            logMessage = "image successfully stored, image url is: "+ complete_banner_URL;
            log.info(logMessage);
            documentaryDto.setBanner_url(complete_banner_URL);
        }

        if(!documentaryDto.getThumbnail_url().contains(".png")){
            logMessage = "Trying to convert base64 image and store it to filesystem..";
            log.info(logMessage);

            String thumbDataBytes = FileUtil.getImageFromBase64(documentaryDto.getThumbnail_url());
            byte [] thumbDecodedBytes = Base64.decodeBase64(thumbDataBytes);
            String thumbURL = this.fileSystemStorageService.storeBase64(thumbDecodedBytes);
            String baseURL = "http://localhost:9090/files/";
            String complete_thumb_URL = baseURL + thumbURL;

            logMessage = "image successfully stored, image url is:" + complete_thumb_URL;
            log.info(logMessage);
            documentaryDto.setThumbnail_url(complete_thumb_URL);
        }


        documentary = this.dtoToDocumentary(documentaryDto);

        Documentary savedDocumentary= this.documentoryRepo.save(documentary);
        logMessage = "Documentary is updated!";
        log.info(logMessage);
        return this.documentaryToDto(savedDocumentary);
    }

    private Documentary dtoToDocumentary(DocumentaryDto documentaryDto) {
        Documentary documentary = this.modelMapper.map(documentaryDto, Documentary.class);
        return documentary;
    }

}

