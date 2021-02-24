package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.user.domain.SMSAuth;
import com.laonstory.ysu.domain.user.dto.CheckSMSRequest;
import com.laonstory.ysu.domain.user.dto.SendSMSRequest;
import com.laonstory.ysu.domain.user.exception.ExpiredSMSAuthException;
import com.laonstory.ysu.domain.user.exception.SMSCodeNotMatchedException;
import com.laonstory.ysu.domain.user.exception.SMSNotFoundException;
import com.laonstory.ysu.domain.user.persistence.SMSJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SMSService {

    private final SMSJpaRepository smsJpaRepository;

    public Boolean sendSMS(SendSMSRequest dto) {
        Optional<SMSAuth> before = smsJpaRepository.findByPhone(dto.getPhone());

        before.ifPresent(smsJpaRepository::delete);

        String code = ((Integer) createRandomCode()).toString();

        SMSAuth smsAuth = SMSAuth.create(dto.getPhone(), code);

        smsJpaRepository.save(smsAuth);

        return true;
    }

    public Boolean checkSMSAuth(CheckSMSRequest dto){
        Optional<SMSAuth> smsAuth = smsJpaRepository.findByPhoneAndCheckedFalse(dto.getPhone());

        if (smsAuth.isEmpty()) throw new SMSNotFoundException(dto.getPhone());

        if (smsAuth.get().getExpiredTime().isBefore(LocalDateTime.now())) throw new ExpiredSMSAuthException(dto.getCode());

        if (!smsAuth.get().getCode().equals(dto.getCode())) throw new SMSCodeNotMatchedException(dto.getCode());

        smsAuth.get().success();

        return true;
    }

    private int createRandomCode() {
         return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

}
