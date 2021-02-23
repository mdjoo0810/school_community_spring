package com.laonstory.ysu.domain.user.domain;

import com.laonstory.ysu.domain.user.dto.SendSMSRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SMS_AUTH")
public class SMSAuth {

    @Id @GeneratedValue
    private Long id;

    private String phone;

    private String code;

    private LocalDateTime expiredTime;

    private Boolean checked;


    public static SMSAuth create(String phone, String code) {
        return SMSAuth.builder()
                .phone(phone)
                .code(code)
                .expiredTime(LocalDateTime.now().plusMinutes(3))
                .checked(false)
                .build();
    }

    public void success() {
        this.checked = true;
    }
}
