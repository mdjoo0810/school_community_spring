package com.laonstory.ysu.domain.user.dto;

import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.major.dto.MajorResponse;
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
    private String phone;
    private String profile;
    private Long point;
    private MajorResponse major;
    private MajorResponse subMajor;
    private List<String> role;

    public UserResponse (final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.studentId = user.getStudentID();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.profile = user.getProfile();
        this.point = user.getPoint();
        this.major = new MajorResponse(user.getMajor());
        this.subMajor = user.getSubMajor() != null ? new MajorResponse(user.getSubMajor()) : null;
        this.role = user.getRoles();
    }

}
