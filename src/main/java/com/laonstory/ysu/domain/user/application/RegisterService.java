package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.major.exception.MajorNotFoundException;
import com.laonstory.ysu.domain.major.persistance.MajorJpaRepository;
import com.laonstory.ysu.domain.major.persistance.MajorRepositorySupport;
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

    private final MajorRepositorySupport majorRepositorySupport;

    /**
     * 회원가입 메서드
     * @param dto : 회원가입 정보
     * @return TokenResponse
     */
    public TokenResponse register(RegisterRequest dto) {

        // 중복 확인
        duplicateStudentId(dto.getStudentId());
        duplicateEmail(dto.getEmail());

        // 유저 생성
        User user = User.create(
                dto
                , passwordEncoder.encode(dto.getPassword())
                , getMajor(dto.getMajorId())
                , dto.getSubMajorId() != null ? getMajor(dto.getSubMajorId()) : null);

        // 유저 저장
        userJpaRepository.save(user);

        // 토큰 발행
        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());

        return new TokenResponse(token, user);
    }

    /**
     * 학번 중복확인
     * @param studentId : 학번
     */
    private void duplicateStudentId(String studentId) {
        Boolean exist = userRepositorySupport.existByStudentId(studentId);
        if (exist) throw new StudentIdDuplicatedException(studentId);
    }

    /**
     * 이메일 중복 확인
     * @param email : 이메일
     */
    private void duplicateEmail(String email) {
        Boolean exist = userRepositorySupport.existByEmail(email);
        if (exist) throw new EmailDuplicateException(email);
    }

    /**
     * 전공 가져오기
     * @param majorId : 전공 PK
     * @return Major
     */
    private Major getMajor(Long majorId) {
        Major major = majorRepositorySupport.findById(majorId);

        if (major == null) throw new MajorNotFoundException(majorId);

        return major;
    }


}
