package com.vpnbeast.encryptionservice.service;

public interface EncryptionService {

    String encrypt(String plainPassword);
    Boolean checkPassword(String plainPassword, String encryptedPassword);

}