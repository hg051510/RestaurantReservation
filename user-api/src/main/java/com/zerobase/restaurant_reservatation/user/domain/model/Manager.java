package com.zerobase.restaurant_reservatation.user.domain.model;

import com.zerobase.restaurant_reservatation.user.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Manager extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDate birth;
    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;
    private boolean partnerVerify;

    public static Manager from(SignUpForm form){
        return Manager.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(form.getPassword())
                .phone(form.getPhone())
                .birth(form.getBirth())
                .verify(false)
                .partnerVerify(false)
                .build();
    }
}
