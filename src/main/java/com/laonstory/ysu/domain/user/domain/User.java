package com.laonstory.ysu.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laonstory.ysu.domain.common.BaseTimeEntity;
import com.laonstory.ysu.domain.user.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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


    public static User create(RegisterRequest dto, String encodedPassword) {
        return User.builder()
                .username(dto.getStudentId())
                .name(dto.getName())
                .password(encodedPassword)
                .nickname(createRandomNickname())
                .studentID(dto.getStudentId())
                .email(dto.getEmail())
                .roles(Collections.singletonList("USER"))
                .build();
    }

    private static String createRandomNickname() {
        return "";
    }
}
