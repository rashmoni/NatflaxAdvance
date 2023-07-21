package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Entity.Documentary;
import com.novare.natflax.NatflaxAdvance.Payloads.DocumentaryDto;
import java.util.List;

public interface DocumentaryService {
    DocumentaryDto createDocumentary(DocumentaryDto documentoryDto);

    void deleteDocumentary(Integer did);

   List<DocumentaryDto> getAllDocumentaries();

   DocumentaryDto getDocumentaryById(Integer documentoryId);

    DocumentaryDto documentaryToDto(Documentary user);

    DocumentaryDto updateDocumentary(DocumentaryDto documentoryDto);
}
