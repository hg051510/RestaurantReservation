package com.zerobase.restaurant_reservatation.user.service.customer;

import com.zerobase.restaurant_reservatation.user.domain.SignUpForm;
import com.zerobase.restaurant_reservatation.user.domain.model.Customer;
import com.zerobase.restaurant_reservatation.user.domain.repository.CustomerRepository;
import com.zerobase.restaurant_reservatation.user.exception.CustomerException;
import com.zerobase.restaurant_reservatation.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class
SignUpCustomerService {
    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email){
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code){
        Customer customer = customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new CustomerException(ErrorCode.NOT_FOUND_USER));

        if(customer.isVerify()){
            throw new CustomerException(ErrorCode.ALREADY_VERIFY);
        } else if (!customer.getVerificationCode().equals(code)) {
            throw new CustomerException(ErrorCode.WRONG_VERIFICATION);
        } else if (customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomerException(ErrorCode.EXPIRE_CODE);
        }

        customer.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeCustomerValidateEmail(Long customerId, String verificationCode){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(7));

            return customer.getVerifyExpiredAt();
        }
        throw new CustomerException(ErrorCode.NOT_FOUND_USER);
    }
}
