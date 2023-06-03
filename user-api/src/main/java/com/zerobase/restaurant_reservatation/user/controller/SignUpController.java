package com.zerobase.restaurant_reservatation.user.controller;

import com.zerobase.restaurant_reservatation.user.application.SignUpApplication;
import com.zerobase.restaurant_reservatation.user.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> customerSignup(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @GetMapping("/customer/verify")
    public ResponseEntity<String> verifyCustomer(String email, String code){
        signUpApplication.customerVerify(email, code);

        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

    @PostMapping("/manager")
    public ResponseEntity<String> managerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.managerSignUp(form));
    }

    @GetMapping("/manager/verify")
    public ResponseEntity<String> verifyManager(String email, String code){
        signUpApplication.managerVerify(email, code);

        return ResponseEntity.ok("인증이 완료되었습니다.");
    }
}
