package com.laonstory.ysu.domain.organization.dto;

import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrganizationResponse {

    private Long id;

    private String title;
    private String description;
    private String profile;
    private String bgImage;
    private String instagram;
    private String facebook;
    private String naver;

    public OrganizationResponse (Organization organization) {
       this.id = organization.getId();
       this.title = organization.getTitle();
       this.description = organization.getDescription();
       this.profile = organization.getProfile();
       this.bgImage = organization.getBgImage();
       this.instagram = organization.getInstagram();
       this.facebook = organization.getInstagram();
       this.naver = organization.getNaver();
    }

}
