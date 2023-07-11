package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Entity.Documentory;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentoryDto;
import java.util.List;

public interface DocumentoryService {
    DocumentoryDto createDocumentory(DocumentoryDto documentoryDto);

    void deleteDocumentory(Integer did);

   List<DocumentoryDto> getAllDocumentory();

   DocumentoryDto getDocumentoryById(Integer documentoryId);

    DocumentoryDto documentoryToDto(Documentory user);

    DocumentoryDto updateDocumentory(DocumentoryDto documentoryDto);
}
