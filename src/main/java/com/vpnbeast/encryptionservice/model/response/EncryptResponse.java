package com.vpnbeast.encryptionservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EncryptResponse {

    private String tag;
    private String output;

}