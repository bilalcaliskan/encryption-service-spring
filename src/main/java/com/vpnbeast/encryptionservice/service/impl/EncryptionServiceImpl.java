package com.vpnbeast.encryptionservice.service.impl;

import com.vpnbeast.encryptionservice.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final StrongPasswordEncryptor strongPasswordEncryptor;

    @Override
    public String encrypt(String plainText) {
        return strongPasswordEncryptor.encryptPassword(plainText);
    }

    @Override
    public Boolean checkPassword(String plainPassword, String encryptedPassword) {
        return strongPasswordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }

}