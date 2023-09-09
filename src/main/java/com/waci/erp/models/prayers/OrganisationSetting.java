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
@Table(name = "organisation_settings")
public class OrganisationSetting extends BaseEntity {


    //Ui Settings
    @Column(name = "theme_color")
    private String themeColor="#2f8ee5";

    @Column(name = "color_mode")
    private String colorMode="light";
    @Column(name = "menuMode")
    private String menuMode="static";
    @Column(name = "inline_menu_position")
    private String InlineMenuPosition="bottom";
    @Column(name = "input_background")
    private String inputBackground;
    @Column(name = "input_style")
    private String inputStyle="filled";
    @Column(name = "rtl")
    private boolean rtl=true;
    @Column(name = "menu_theme")
    private String menuTheme="light";
    @Column(name = "topbar_theme")
    private String topbarTheme="blue";
    @Column(name = "component_theme")
    private String componentTheme="indigo";
    @Column(name = "theme_scale")
    private int themeScale=1;

    @OneToOne(mappedBy = "settings")
    private Organisation organisation;



}
