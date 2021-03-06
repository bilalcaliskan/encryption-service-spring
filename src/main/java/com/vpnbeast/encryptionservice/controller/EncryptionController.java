package com.vpnbeast.encryptionservice.controller;

import com.vpnbeast.encryptionservice.model.request.CheckRequest;
import com.vpnbeast.encryptionservice.model.response.CheckResponse;
import com.vpnbeast.encryptionservice.model.request.EncryptRequest;
import com.vpnbeast.encryptionservice.model.response.EncryptResponse;
import com.vpnbeast.encryptionservice.service.EncryptionService;
import com.vpnbeast.encryptionservice.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/encryption-controller")
public class EncryptionController {

    private final EncryptionService encryptionService;
    private final ResponseService responseService;

    @PostMapping(value = "/encrypt")
    public ResponseEntity<EncryptResponse> encrypt(@Valid @RequestBody EncryptRequest encryptRequest) {
        log.info("/encrypt endpoint is called");
        log.info("Starting string encryption...");
        return new ResponseEntity<>(responseService.buildEncryptResponse("encryptPassword",
                encryptionService.encrypt(encryptRequest.getPlainText())), HttpStatus.OK);
    }

    @PostMapping(value = "/check")
    public ResponseEntity<CheckResponse> checkPassword(@Valid @RequestBody CheckRequest checkRequest) {
        log.info("/check endpoint is called, starting string checking...");
        final boolean isMatched = encryptionService.checkPassword(checkRequest.getPlainText(), checkRequest.getEncryptedText());
        log.info("String checking result is {}", isMatched);
        return new ResponseEntity<>(responseService.buildCheckResponse("checkPassword", isMatched), HttpStatus.OK);
    }

}