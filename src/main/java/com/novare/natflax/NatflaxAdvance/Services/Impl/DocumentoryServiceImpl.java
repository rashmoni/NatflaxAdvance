package com.novare.natflax.NatflaxAdvance.Services.Impl;

import com.novare.natflax.NatflaxAdvance.Entity.Documentory;
import com.novare.natflax.NatflaxAdvance.Entity.Movie;
import com.novare.natflax.NatflaxAdvance.Exceptions.ResourceNotFoundException;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentoryDto;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;
import com.novare.natflax.NatflaxAdvance.Repositories.DocumentoryRepo;
import com.novare.natflax.NatflaxAdvance.Repositories.MovieRepo;
import com.novare.natflax.NatflaxAdvance.Services.DocumentoryService;
import lombok.extern.log4j.Log4j2;
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


    @Override
    public DocumentoryDto createDocumentory(DocumentoryDto documentoryDto) {
        return null;
    }

    @Override
    public void deleteDocumentory(Integer did) {
        Documentory documentory = this.documentoryRepo.findById(did).orElseThrow(() -> new ResourceNotFoundException("Documentory", "Id", did));
        this.documentoryRepo.delete(documentory);
    }

    @Override
    public List<DocumentoryDto> getAllDocumentory() {
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

    private Documentory dtoToDocumentory(DocumentoryDto documentoryDto) {
        Documentory documentory = this.modelMapper.map(documentoryDto, Documentory.class);
        return documentory;
    }

}

