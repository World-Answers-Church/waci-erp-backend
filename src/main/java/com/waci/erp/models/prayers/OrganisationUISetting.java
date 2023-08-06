package com.waci.erp.models.prayers;

import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "organisation_ui_settings")
public class OrganisationUISetting extends OrganisationBaseEntity {


    //Ui Settings

    @Column(name = "theme_color")
    private String themeColor;
    @Column(name = "menuMode")
    private String menuMode;
    @Column(name = "inline_menu_position")
    private String InlineMenuPosition;
    @Column(name = "input_background")
    private String inputBackground;
    @Column(name = "rtl")
    private boolean rtl;
    @Column(name = "menu_theme")
    private String menuTheme;
    @Column(name = "topbar_theme")
    private String topbarTheme;
    @Column(name = "component_theme")
    private String componentTheme;
    @Column(name = "theme_scale")
    private int themeScale;



}
