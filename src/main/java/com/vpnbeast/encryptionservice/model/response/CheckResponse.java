package com.vpnbeast.encryptionservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckResponse {

    private String tag;
    private Boolean status;

}