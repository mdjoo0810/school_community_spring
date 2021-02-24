package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.major.exception.MajorNotFoundException;
import com.laonstory.ysu.domain.major.persistence.MajorRepositorySupport;
import com.laonstory.ysu.domain.point.domain.PointHistory;
import com.laonstory.ysu.domain.point.dto.PointHistoryRequest;
import com.laonstory.ysu.domain.point.persistence.PointHistoryJpaRepository;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.RegisterRequest;
import com.laonstory.ysu.domain.user.dto.TokenResponse;
import com.laonstory.ysu.domain.user.exception.*;
import com.laonstory.ysu.domain.user.persistence.UserJpaRepository;
import com.laonstory.ysu.domain.user.persistence.UserRepositorySupport;
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

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    /**
     * 회원가입 메서드
     * @param dto : 회원가입 정보
     * @return TokenResponse
     */
    public TokenResponse register(RegisterRequest dto) {
        // 탈퇴 확인
        isWithdrawUser(dto.getStudentId());

        // 중복 확인
        duplicateStudentId(dto.getStudentId());
        duplicateEmail(dto.getEmail());
        duplicatePhone(dto.getPhone());

        // 유저 생성
        User user = User.create(
                dto
                , passwordEncoder.encode(dto.getPassword())
                , getMajor(dto.getMajorId())
                , dto.getSubMajorId() != null ? getMajor(dto.getSubMajorId()) : null);

        // 유저 저장
        userJpaRepository.save(user);

        // 회원가입 포인트 추가
        user.addPoint(1000L);

        PointHistory history = PointHistory.create(new PointHistoryRequest(1000L, "회원가입 축하 포인트"), user);
        pointHistoryJpaRepository.save(history);

        // 토큰 발행
        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());

        return new TokenResponse(token, user);
    }

    /**
     * 탈퇴된 회원인지 확인
     * @param studentId : 학번
     */
    private void isWithdrawUser(String studentId) {
        Boolean withdraw = userRepositorySupport.isWithdrawUser(studentId);
        if (withdraw) throw new AlreadyWithdrawUserException(studentId);
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
     * 전화번호 중복 확인
     * @param phone : 전화번호
     */
    private void duplicatePhone(String phone) {
        Boolean exist = userRepositorySupport.existByPhone(phone);
        if (exist) throw new PhoneDuplicateException(phone);
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
