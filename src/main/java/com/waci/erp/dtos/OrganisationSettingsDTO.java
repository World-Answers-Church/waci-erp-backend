package com.waci.erp.dtos;

import com.waci.erp.models.prayers.Organisation;
import com.waci.erp.models.prayers.OrganisationSetting;
import com.waci.erp.shared.api.BaseDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class OrganisationSettingsDTO extends BaseDTO {
    private String themeColor;
    private String menuMode;
    private String inlineMenuPosition;
    private String inputBackground;
    private boolean rtl;
    private String menuTheme;
    private String topbarTheme;
    private String componentTheme;
    private int themeScale;
    private long organisationId;
    private String organisationName;
    public static OrganisationSettingsDTO fromModel(OrganisationSetting model){
        OrganisationSettingsDTO dto= new OrganisationSettingsDTO();
        dto.setThemeColor(model.getThemeColor());
        dto.setMenuMode(model.getMenuMode());
        dto.setInlineMenuPosition(model.getInlineMenuPosition());
        dto.setInputBackground(model.getInputBackground());

        dto.setRtl(model.isRtl());
        dto.setMenuTheme(model.getMenuTheme());
        dto.setTopbarTheme(model.getTopbarTheme());
        dto.setComponentTheme(model.getComponentTheme());
        dto.setThemeScale(model.getThemeScale());
        dto.setOrganisationId(model.getOrganisation().id);
        dto.setOrganisationName(model.getOrganisation().getName());




        dto.setId(model.getId());
        dto.setRecordStatus(model.getRecordStatus().name());
        dto.setCreatedById(model.getCreatedById());
        dto.setCreatedByUsername(model.getCreatedByUsername());
        dto.setChangedById(model.getChangedById());
        dto.setChangedByUserName(model.getChangedByUsername());
        dto.setDateCreated(model.getDateCreated());
        dto.setDateChanged(model.getDateChanged());
        dto.setOrganisationCode(model.getOrganisationCode());
        return dto;
    }

}
