package com.laonstory.ysu.domain.user.persistence;

import com.laonstory.ysu.domain.user.domain.SMSAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SMSJpaRepository extends JpaRepository<SMSAuth, Long> {

    Optional<SMSAuth> findByPhone(String phone);

    Optional<SMSAuth> findByPhoneAndCheckedFalse(String phone);

    Optional<SMSAuth> findByPhoneAndCheckedTrue(String phone);

}
