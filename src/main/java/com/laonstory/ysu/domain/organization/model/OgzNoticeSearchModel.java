package com.laonstory.ysu.domain.organization.model;

import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OgzNoticeSearchModel {

    private StudentOgz.OgzType ogzType;
    private OgzTabType tabType;
    private List<OgzNoticeMenu> menus;
    private Long ogzId;
    private Boolean isWhole;
    private String query;
    private String year;


    public OgzNoticeSearchModel (OgzTabType tabType, List<OgzNoticeMenu> menus) {
        this.tabType = tabType;
        this.menus = menus;
    }

    public OgzNoticeSearchModel (OgzTabType tabType, List<OgzNoticeMenu> menus, Long ogzId) {
        this.tabType = tabType;
        this.menus = menus;
        this.ogzId = ogzId;
    }

    public OgzNoticeSearchModel (OgzTabType tabType, List<OgzNoticeMenu> menus, Boolean isWhole, String query) {
        this.tabType = tabType;
        this.menus = menus;
        this.isWhole = isWhole != null ? isWhole : false;
        this.query = query;
    }

    public OgzNoticeSearchModel (OgzTabType tabType, List<OgzNoticeMenu> menus, Long ogzId, Boolean isWhole, String query) {
        this.tabType = tabType;
        this.menus = menus;
        this.ogzId = ogzId;
        this.isWhole = isWhole != null ? isWhole : false;
        this.query = query;
    }

    public OgzNoticeSearchModel (OgzTabType tabType, Long ogzId) {
        this.tabType = tabType;
        this.ogzId = ogzId;
    }

    public OgzNoticeSearchModel (StudentOgz.OgzType ogzType, OgzTabType tabType) {
        this.ogzType = ogzType;
        this.tabType = tabType;
    }

    //
    public OgzNoticeSearchModel (OgzNoticeSearchModel model, List<OgzNoticeMenu> menus) {
        this.ogzType = model.getOgzType();
        this.tabType = model.getTabType();
        this.menus = menus;
    }

    public OgzNoticeSearchModel (OgzNoticeSearchModel model, List<OgzNoticeMenu> menus, Long ogzId) {
        this.ogzType = model.getOgzType();
        this.tabType = model.getTabType();
        this.menus = menus;
        this.ogzId = ogzId;
    }

}
