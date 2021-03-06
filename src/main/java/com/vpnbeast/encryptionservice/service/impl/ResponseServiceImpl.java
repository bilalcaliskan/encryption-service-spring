package com.vpnbeast.encryptionservice.service.impl;

import com.vpnbeast.encryptionservice.model.response.CheckResponse;
import com.vpnbeast.encryptionservice.model.response.EncryptResponse;
import com.vpnbeast.encryptionservice.service.ResponseService;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Override
    public EncryptResponse buildEncryptResponse(String tag, String output) {
        return EncryptResponse.builder()
                .tag(tag)
                .output(output)
                .build();
    }

    @Override
    public CheckResponse buildCheckResponse(String tag, Boolean status) {
        return CheckResponse.builder()
                .tag(tag)
                .status(status)
                .build();
    }

}