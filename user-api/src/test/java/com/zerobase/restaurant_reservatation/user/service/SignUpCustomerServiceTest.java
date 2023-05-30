package com.zerobase.restaurant_reservatation.user.service;

import com.zerobase.restaurant_reservatation.user.domain.SignUpForm;
import com.zerobase.restaurant_reservatation.user.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignUpCustomerServiceTest {
    @Autowired
    private SignUpCustomerService service;

    @Test
    void signUp(){
        SignUpForm form = SignUpForm.builder()
                .email("test@gmail.com")
                .name("test")
                .password("1234")
                .phone("01012345678")
                .birth(LocalDate.now())
                .build();
        Customer c = service.signUp(form);
        assertNotNull(c.getId());
        assertNotNull(c.getCreatedAt());
    }
}