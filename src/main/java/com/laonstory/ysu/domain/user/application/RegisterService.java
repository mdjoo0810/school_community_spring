package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.RegisterRequest;
import com.laonstory.ysu.domain.user.dto.TokenResponse;
import com.laonstory.ysu.domain.user.dto.UserResponse;
import com.laonstory.ysu.domain.user.exception.EmailDuplicateException;
import com.laonstory.ysu.domain.user.exception.StudentIdDuplicatedException;
import com.laonstory.ysu.domain.user.persistance.UserJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserRepositorySupport;
import com.laonstory.ysu.global.component.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RegisterService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserJpaRepository userJpaRepository;
    private final UserRepositorySupport userRepositorySupport;

    public TokenResponse register(RegisterRequest dto) {

        if (duplicateStudentId(dto.getStudentId())){
            throw new StudentIdDuplicatedException(dto.getStudentId());
        }

        if (duplicateEmail(dto.getEmail())){
            throw new EmailDuplicateException(dto.getEmail());
        }

        User user = User.create(dto, passwordEncoder.encode(dto.getPassword()));

        userJpaRepository.save(user);

        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());

        return new TokenResponse(token, user);
    }

    private Boolean duplicateStudentId(String studentId) {
        return userRepositorySupport.existByStudentId(studentId);
    }

    private Boolean duplicateEmail(String email) {
        return userRepositorySupport.existByEmail(email);
    }


}
