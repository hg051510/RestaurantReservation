package com.zerobase.restaurant_reservatation.user.application;

import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserType;
import com.zerobase.restaurant_reservatation.user.domain.SignInForm;
import com.zerobase.restaurant_reservatation.user.domain.model.Customer;
import com.zerobase.restaurant_reservatation.user.exception.CustomerException;
import com.zerobase.restaurant_reservatation.user.exception.ErrorCode;
import com.zerobase.restaurant_reservatation.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form){
        // 1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomerException(ErrorCode.LOGIN_CHECK_FAIL));

        // 2. 토큰 발행 후 response;
        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

}
