package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.ApiResponse;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentaryDto;
import com.novare.natflax.NatflaxAdvance.Services.DocumentaryService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/documentaries")
public class DocumentariesController {

    @Autowired
    private DocumentaryService documentaryService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<DocumentaryDto> createDocumentary(@Valid @RequestBody DocumentaryDto documentoryDto) {
        String message = "User tried to create new item with name: " + documentoryDto.getTitle();
        log.info(message);

        DocumentaryDto createdDocumentaryDto = this.documentaryService.createDocumentary(documentoryDto);
        return new ResponseEntity<>(createdDocumentaryDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentaryDto>> getAllDocumentary(){
        return ResponseEntity.ok(this.documentaryService.getAllDocumentaries());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<DocumentaryDto> updateDocumentary(@Valid @RequestBody DocumentaryDto documentoryDto){
        return ResponseEntity.ok(this.documentaryService.updateDocumentary(documentoryDto));
    }

    @GetMapping("/{documentaryId}")
    public ResponseEntity<DocumentaryDto> getSingleDocumentary(@PathVariable Integer documentoryId){
        return ResponseEntity.ok(this.documentaryService.getDocumentaryById(documentoryId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{documentaryId}")
    public ResponseEntity<ApiResponse> deleteDocumentary(@PathVariable("documentaryId") Integer dId){
        documentaryService.deleteDocumentary(dId);
        return new ResponseEntity(new ApiResponse("Documentary deleted successfully",true), HttpStatus.OK);
    }

}
