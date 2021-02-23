package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.major.exception.MajorNotFoundException;
import com.laonstory.ysu.domain.major.persistance.MajorRepositorySupport;
import com.laonstory.ysu.domain.user.domain.SMSAuth;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.*;
import com.laonstory.ysu.domain.user.exception.PasswordNotMatchedException;
import com.laonstory.ysu.domain.user.exception.SMSNotFoundException;
import com.laonstory.ysu.domain.user.exception.UserNotFoundException;
import com.laonstory.ysu.domain.user.persistance.SMSJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserRepositorySupport;
import com.laonstory.ysu.global.component.JwtTokenProvider;
import com.laonstory.ysu.global.error.exception.FileSaveException;
import com.laonstory.ysu.global.utils.ResourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserJpaRepository userJpaRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final SMSJpaRepository smsJpaRepository;
    private final MajorRepositorySupport majorRepositorySupport;
    /**
     * 내 정보 요청 및 토큰 갱신 메서드
     * @param user : token 에 맞는 유저
     * @return TokenResponse
     */
    public TokenResponse me (User user) {
        //
        User result = userJpaRepository.findById(user.getId()).orElseThrow();
        // 토큰 발행
        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
        return new TokenResponse(token, result);
    }

    /**
     * 푸시 메세지를 위한 FCM Token 업데이트 메서드
     * @param user : token 에 맞는 유저
     * @param dto : token 업데이트에 필요한 정보
     * @return UserResponse
     */
    @Transactional
    public Boolean updateFcmToken(User user, UpdateFcmTokenRequest dto) {
        user.updateFcmToken(dto.getFcmToken());
        userJpaRepository.save(user);
        return true;
    }

    /**
     * 계정 찾기 시 해당 정보에 맞는 유저가 있는지 확인 메서드
     * @param dto : 계정 찾기에 필요한 정보
     * @return UserResponse
     */
    public UserResponse checkUser(CheckUserRequest dto) {
        User user = userRepositorySupport.findByStudentIdAndPhone(dto);

        return new UserResponse(user);
    }


    /**
     * 비밀번호 변경 메서드
     * @param dto : 비밀번호 변경 시 필요한 정보
     * @return UserResponse
     */
    @Transactional
    public UserResponse changePassword(ChangePasswordRequest dto) {

        // 문자인증을 성공했는가?
        Optional<SMSAuth> smsAuth = smsJpaRepository.findByPhoneAndCheckedTrue(dto.getPhone());

        if(smsAuth.isEmpty()) throw new SMSNotFoundException(dto.getPhone());

        smsJpaRepository.delete(smsAuth.get());

        User user = userRepositorySupport.findByPhone(dto.getPhone());

        // 비밀번호 변경
        user.changePassword(passwordEncoder.encode(dto.getPassword()));

        return new UserResponse(user);
    }


    /**
     * 현재 비밀번호로 비밀번호를 변경하는 메서드
     * @param user : token 에 맞는 유저
     * @param dto : 비밀번호 변경에 필요한 정보
     * @return UserResponse
     */
    @Transactional
    public UserResponse changePasswordByCurrentPassword(User user, ChangePasswordByCurrentPasswordRequest dto) {
        User userInfo = userJpaRepository.findById(user.getId()).orElseThrow();

        matchPassword(user.getPassword(), dto.getCurrentPassword());

        userInfo.changePassword(passwordEncoder.encode(dto.getNewPassword()));

        return new UserResponse(userInfo);
    }

    /**
     * 학적 정보 변경 메서드
     * @param user : token 에 맞는 유저
     * @param dto : 학적 정보 변경에 필요한 정보
     * @return UserResponse
     */
    @Transactional
    public UserResponse changeMajor(User user, ChangeMajorRequest dto){
        User userInfo = userJpaRepository.findById(user.getId()).orElseThrow();

        userInfo.changeMajor(getMajor(dto.getMajorId()), dto.getSubMajorId() != null ? getMajor(dto.getSubMajorId()) : null);

        return new UserResponse(userInfo);
    }

    /**
     * 프로필 변경
     * @param user : token 에 맞는 유저
     * @param file : 프로필 사진
     * @return UserResponse
     */
    @Transactional
    public UserResponse changeProfile(User user, MultipartFile file) {
        User userInfo = userJpaRepository.findById(user.getId()).orElseThrow();

        final String FILE_LOCATION = "/profile/" + user.getStudentID() + "/" + file.getOriginalFilename();

        try {
            if (userInfo.getProfile() != null) {
                ResourceUtil.deleteFile(user.getProfile());
            }

            userInfo.changeProfile(FILE_LOCATION);

            ResourceUtil.saveFile(file, FILE_LOCATION);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileSaveException(file.getOriginalFilename());
        }

        return new UserResponse(userInfo);
    }

    /**
     * 프로필 삭제 메서드
     * @param user : token 에 맞는 유저
     * @return UserResponse
     */
    @Transactional
    public UserResponse deleteProfile(User user) {
        User userInfo = userJpaRepository.findById(user.getId()).orElseThrow();

        userInfo.deleteProfile();

        ResourceUtil.deleteFile(user.getProfile());

        return new UserResponse(userInfo);

    }

    @Transactional
    public Boolean withdraw(User user) {
        user.withdraw();
        userJpaRepository.save(user);
        return true;
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

    /*
     * 비밀번호가 맞는 지 확인
     * @param encodedPassword : User 정보에 저장된 암호화된 비밀번호
     * @param password 유저가 입력한 비밀번호
     */
    private void matchPassword(String encodedPassword, String password) {
        if (!passwordEncoder.matches(password, encodedPassword)) throw new PasswordNotMatchedException(password);
    }

}
