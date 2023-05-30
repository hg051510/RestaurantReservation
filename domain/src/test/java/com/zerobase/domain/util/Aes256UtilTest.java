package com.zerobase.domain.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Aes256UtilTest {

    @Test
    @DisplayName("암호화 성공 케이스")
    void encrypt(){
        String encrypt = Aes256Util.encrypt("Hello world");
        assertEquals(Aes256Util.decrypt(encrypt), "Hello world");
    }
}