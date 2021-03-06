package com.vpnbeast.encryptionservice.service;

import com.vpnbeast.encryptionservice.model.response.CheckResponse;
import com.vpnbeast.encryptionservice.model.response.EncryptResponse;

public interface ResponseService {

    EncryptResponse buildEncryptResponse(String tag, String output);
    CheckResponse buildCheckResponse(String tag, Boolean status);

}