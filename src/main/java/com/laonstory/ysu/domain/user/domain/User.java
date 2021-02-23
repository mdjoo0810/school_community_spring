package com.laonstory.ysu.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laonstory.ysu.domain.common.BaseTimeEntity;
import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.user.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USER")
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false , unique = true)
    private String nickname;

    @Column(nullable = false, name = "student_id", unique = true)
    private String studentID;

    @Column(nullable = false, unique = true)
    private String email;

    private String profile;

    private String fcmToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_major_id")
    private Major subMajor;

    @ColumnDefault("0")
    private Long point;

    private LocalDateTime lastLoginDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private final List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 유저 생성
    public static User create(RegisterRequest dto, String encodedPassword, Major major, Major subMajor) {
        return User.builder()
                .username(dto.getStudentId())
                .name(dto.getName())
                .password(encodedPassword)
                .nickname(createRandomNickname(dto.getStudentId()))
                .studentID(dto.getStudentId())
                .email(dto.getEmail())
                .point(0L)
                .major(major)
                .subMajor(subMajor)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

    // 로그인 처리
    public void login() {
        this.lastLoginDate = LocalDateTime.now();
    }

    // FCM Token 업데이트
    public void updateFcmToken (String fcmToken) {
        this.fcmToken = fcmToken;
    }

    // 포인트 증가
    public void addPoint(Long point) {
        this.point += point;
    }

    // 포인트 감소
    public void minusPoint(Long point) {
        Long result = this.point -= point;
        this.point = result > 0 ? result : 0;
    }

    // 랜덤 닉네임 생성
    private static String createRandomNickname(String studentId) {
        Random rnd = new Random();

        String firstRandomStr = String.valueOf((char) ((int) (rnd.nextInt(26)) + 65));
        String SecondRandomStr = String.valueOf((char) ((int) (rnd.nextInt(26)) + 65));

        return studentId.substring(2,4) + firstRandomStr + studentId.substring(4,7) + SecondRandomStr + studentId.substring(7,10);
    }
}
