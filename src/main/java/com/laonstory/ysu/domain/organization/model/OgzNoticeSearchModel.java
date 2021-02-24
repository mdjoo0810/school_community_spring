package com.laonstory.ysu.domain.organization.model;

import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OgzNoticeSearchModel {

    private StudentOgz.OgzType ogzType;
    private OgzTabType tabType;
    private List<OgzNoticeMenu> menus;
    private Long ogzId;

    public OgzNoticeSearchModel (StudentOgz.OgzType ogzType, OgzTabType tabType) {
        this.ogzType = ogzType;
        this.tabType = tabType;
    }

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
