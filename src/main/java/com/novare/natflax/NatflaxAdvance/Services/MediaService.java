package com.novare.natflax.NatflaxAdvance.Services;

import com.novare.natflax.NatflaxAdvance.Payloads.MediaDto;
import com.novare.natflax.NatflaxAdvance.Payloads.MovieDto;


import java.util.List;

public interface MediaService {
    List<MediaDto> getAllMedia();
}
