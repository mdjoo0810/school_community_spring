package com.laonstory.ysu.global.application;

import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.exception.UserNotFoundException;
import com.laonstory.ysu.domain.user.persistance.UserJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public User loadUserByUsername(String userPK) throws UsernameNotFoundException {
        return userJpaRepository.findById(Long.valueOf(userPK)).orElseThrow();
    }
}
