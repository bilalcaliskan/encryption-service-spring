package com.vpnbeast.encryptionservice.model.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CheckRequest {

    @NotNull(message = "plainText field should not be null!")
    @Size(max = 20, message = "plainText field should be max 20 chars length!")
    private String plainText;

    @NotNull(message = "encryptedText field should not be null!")
    @Size(max = 200, message = "encryptedText field should be max 200 chars length!")
    private String encryptedText;

}