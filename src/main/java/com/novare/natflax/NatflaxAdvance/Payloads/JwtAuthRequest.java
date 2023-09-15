package com.novare.natflax.NatflaxAdvance.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String email;

    private String password;

}
