package com.novare.natflax.NatflaxAdvance.Controllers;

import com.novare.natflax.NatflaxAdvance.Payloads.ApiResponse;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentoryDto;
import com.novare.natflax.NatflaxAdvance.Services.DocumentoryService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Log4j2
@RequestMapping("/api/documentories")
public class DocumentariesController {

    @Autowired
    private DocumentoryService documentoryService;


    @PostMapping("/")
    public ResponseEntity<DocumentoryDto> createDocumentary(@Valid @RequestBody DocumentoryDto documentoryDto) {
        String message = "User tried to create new item with name: " + documentoryDto.getTitle();
        log.info(message);

        DocumentoryDto createdDocumentoryDto = this.documentoryService.createDocumentory(documentoryDto);
        return new ResponseEntity<>(createdDocumentoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentoryDto>> getAllDocumentory(){
        return ResponseEntity.ok(this.documentoryService.getAllDocumentoies());
    }

    @PutMapping("/")
    public ResponseEntity<DocumentoryDto> updateDocumentory(@Valid @RequestBody DocumentoryDto documentoryDto){
        return ResponseEntity.ok(this.documentoryService.updateDocumentory(documentoryDto));
    }

    @GetMapping("/{documentoryId}")
    public ResponseEntity<DocumentoryDto> getSingleDocumentory(@PathVariable Integer documentoryId){
        return ResponseEntity.ok(this.documentoryService.getDocumentoryById(documentoryId));
    }

    @DeleteMapping("/{documentoryId}")
    public ResponseEntity<ApiResponse> deleteDocumentory(@PathVariable("documentoryId") Integer did){
        documentoryService.deleteDocumentory(did);
        return new ResponseEntity(new ApiResponse("Documentory deleted successfully",true), HttpStatus.OK);
    }


}
