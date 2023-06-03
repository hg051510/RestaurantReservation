package com.zerobase.restaurant_reservatation.user.service.manager;

import com.zerobase.restaurant_reservatation.user.domain.model.Manager;
import com.zerobase.restaurant_reservatation.user.domain.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public Optional<Manager> findByIdAndEmail(Long id, String email){
        return managerRepository.findByIdAndEmail(id, email);
    }

    public Optional<Manager> findValidManager(String email, String password){
        return managerRepository.findByEmailAndPasswordAndVerifyIsTrue(email, password);
    }
}
