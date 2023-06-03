package com.zerobase.restaurant_reservatation.user.domain.repository;

import com.zerobase.restaurant_reservatation.user.domain.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByIdAndEmail(Long id, String email);
    Optional<Manager> findByEmailAndPasswordAndVerifyIsTrue(String email, String password);
    Optional<Manager> findByEmailAndPasswordAndVerifyIsTrueAndPartnerVerifyIsTrue(String email, String password);
    Optional<Manager> findByEmail(String email);
}
