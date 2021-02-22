package com.laonstory.ysu.domain.user.dto;

import com.laonstory.ysu.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private Long id;
    private String name;
    private String nickname;
    private String studentId;
    private String email;
    private String profile;
    private List<String> role;

    public UserResponse (final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.studentId = user.getStudentID();
        this.email = user.getEmail();
        this.profile = user.getProfile();
        this.role = user.getRoles();
    }

}
