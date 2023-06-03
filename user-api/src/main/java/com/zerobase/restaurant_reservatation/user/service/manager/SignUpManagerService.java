package com.zerobase.restaurant_reservatation.user.service.manager;

import com.zerobase.restaurant_reservatation.user.domain.SignUpForm;
import com.zerobase.restaurant_reservatation.user.domain.model.Manager;
import com.zerobase.restaurant_reservatation.user.domain.repository.ManagerRepository;
import com.zerobase.restaurant_reservatation.user.exception.CustomerException;
import com.zerobase.restaurant_reservatation.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpManagerService {
    private final ManagerRepository managerRepository;

    public Manager signUp(SignUpForm form){
        return managerRepository.save(Manager.from(form));
    }

    public boolean isEmailExist(String email){
        return managerRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code){
        Manager manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerException(ErrorCode.NOT_FOUND_USER));

        if(manager.isVerify()){
            throw new CustomerException(ErrorCode.ALREADY_VERIFY);
        }else if(!manager.getVerificationCode().equals(code)){
            throw new CustomerException(ErrorCode.WRONG_VERIFICATION);
        } else if (manager.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomerException(ErrorCode.EXPIRE_CODE);
        }
        manager.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeManagerValidateEmail(Long managerId, String verificationCode){
        Optional<Manager> managerOptional =
                managerRepository.findById(managerId);

        if(managerOptional.isPresent()){
            Manager manager = managerOptional.get();
            manager.setVerificationCode(verificationCode);

            manager.setVerifyExpiredAt(LocalDateTime.now().plusDays(7));

            return manager.getVerifyExpiredAt();
        }
        throw new CustomerException(ErrorCode.NOT_FOUND_USER);
    }
}
