package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.OrganisationDao;
import com.waci.erp.daos.OrganisationSettingsDao;
import com.waci.erp.dtos.OrganisationDTO;
import com.waci.erp.dtos.OrganisationSettingsDTO;
import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.models.prayers.LookupValue;
import com.waci.erp.models.prayers.Organisation;
import com.waci.erp.models.prayers.OrganisationSetting;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.OrganisationService;
import com.waci.erp.services.UserService;
import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.models.User;
import com.waci.erp.shared.utils.CustomSearchUtils;
import com.waci.erp.shared.utils.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.waci.erp.shared.constants.MessageConstants.ORGANISATION_REGISTRATION_EMAIL_CONTENT;
import static com.waci.erp.shared.constants.MessageConstants.ORGANISATION_REGISTRATION_EMAIL_SUBJECT;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {
    @Autowired
    OrganisationDao organisationDao;

    @Autowired
    @Qualifier("vertxMailServiceImpl")
    MailService mailService;
    @Autowired
    UserService userService;

    @Autowired
    OrganisationSettingsDao organisationSettingsDao;

    @Autowired
    LookupValueService lookupValueDao;

    @Override
    public Organisation save(OrganisationDTO dto) {
        Organisation organisation = new Organisation();
        if (dto.getId() > 0) {
            organisation = getOrganisationById(dto.getId());
        }

        if (StringUtils.isBlank(dto.getName())) {
            throw new OperationFailedException("Missing name");
        }
        if (StringUtils.isBlank(dto.getCode())) {
            throw new OperationFailedException("Missing code");
        }

        if (StringUtils.isBlank(dto.getPrimaryPhoneNumber())) {
            throw new OperationFailedException("Missing primary phone number");
        }
        Organisation existsWithCode = getOrganisationByCode(dto.getCode());
        if (existsWithCode != null && existsWithCode.getId() != dto.getId()) {
            throw new OperationFailedException("Organisation with same code exists");
        }
        Organisation existsWithPhone = getOrganisationByPhoneNumber(dto.getPrimaryPhoneNumber());
        if (existsWithPhone != null && existsWithPhone.getId() != dto.getId()) {
            throw new OperationFailedException("Organisation with same primary phone number exists");
        }

        if(dto.getCategoryId()>0) {
            LookupValue category = lookupValueDao.getLookupValueByTypeAndValue(LookupType.ORGANISATION_CATEGORIES, (int) dto.getCategoryId());
            organisation.setCategory(category);
        }
        organisation.setId(dto.getId());
        organisation.setName(dto.getName());
        organisation.setCode(dto.getCode().toUpperCase());
        organisation.setOrganisationCode(dto.getCode().toUpperCase());
        organisation.setEmailAddress(dto.getEmailAddress());
        organisation.setPhysicalAddress(dto.getPhysicalAddress());
        organisation.setPrimaryPhoneNumber(dto.getPrimaryPhoneNumber());
        organisation.setOtherPhoneNumber(dto.getOtherPhoneNumber());
        organisation.setLogoUrl(dto.getLogoUrl());
        organisation.setWebsite(dto.getWebsite());

        if (organisation.isNew()) {
            organisation.setSettings(new OrganisationSetting());
            Organisation savedOrganisation = organisationDao.merge(organisation);
            createDefaultUser(savedOrganisation);
            return savedOrganisation;
        }

        return organisationDao.save(organisation);
    }

    private void createDefaultUser(Organisation organisation) {
        User user = new User();
        user.setEmailAddress(organisation.getEmailAddress());
        user.setFirstName(organisation.getCode());
        user.setLastName("Admin");
        user.setPhoneNumber(organisation.getPrimaryPhoneNumber());
        user.setUsername(organisation.getCode());
        user.setPassword(organisation.getPrimaryPhoneNumber());
        user.setOrganisationCode(organisation.getCode());
        userService.saveUser(user);
        new Thread(() -> {
            try {
                mailService.sendEmail(
                        user.getEmailAddress(),
                        ORGANISATION_REGISTRATION_EMAIL_SUBJECT,
                        MessageFormat.format(ORGANISATION_REGISTRATION_EMAIL_CONTENT,
                                user.getOrganisationCode(), user.getUsername(), user.getPhoneNumber())
                );

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).run();
    }

    @Override
    public OrganisationSetting save(OrganisationSettingsDTO dto) {
        OrganisationSetting model = new OrganisationSetting();
        if (dto.getId() > 0) {
            model = getById(dto.getId());
            if (model == null) {
                throw new OperationFailedException("Settings No Found with Id");
            }
        }
        if (dto.getOrganisationId() == 0) {
            throw new OperationFailedException("Missing organisation");
        }

        Organisation organisation = getOrganisationById(dto.getOrganisationId());

        model.setId(dto.getId());
        model.setOrganisation(organisation);
        model.setRtl(dto.isRtl());
        model.setComponentTheme(dto.getComponentTheme());
        model.setMenuMode(dto.getMenuMode());
        model.setMenuTheme(dto.getMenuTheme());
        model.setInputBackground(dto.getInputBackground());
        model.setInlineMenuPosition(dto.getInlineMenuPosition());
        model.setTopbarTheme(dto.getTopbarTheme());
        model.setThemeColor(dto.getThemeColor());
        model.setThemeScale(dto.getThemeScale());

        return organisationSettingsDao.save(model);
    }

    @Override
    public OrganisationSetting getSettings(Long organisationId) {
        return organisationSettingsDao.searchUnique(new Search().addFilterEqual("organisation.id", organisationId));

    }

    @Override
    public List<Organisation> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return organisationDao.search(search);
    }


    @Override
    public int count(Search search) {
        return organisationDao.count(search);
    }

    @Override
    public Organisation getOrganisationById(long id) {
        return organisationDao.findById(id).orElseThrow(() -> new OperationFailedException("Organisation Not found"));
    }

    public OrganisationSetting getById(long id) {
        return organisationSettingsDao.findById(id).orElseThrow(() -> new OperationFailedException("Organisation Settings Not found"));
    }

    @Override
    public Organisation getOrganisationByPhoneNumber(String phoneNumber) {
        return organisationDao.searchUnique(new Search().addFilterEqual("primaryPhoneNumber", phoneNumber));
    }

    public Organisation getOrganisationByCode(String code) {
        return organisationDao.searchUnique(new Search().addFilterEqual("code", code.toUpperCase()));
    }

    public static Search composeSearchObject(String searchTerm) {
        Search search = CustomSearchUtils.generateSearchTerms(searchTerm,
                Arrays.asList(
                        "themeColor",
                        "menuMode",
                        "inputBackground",
                        "topbarTheme",
                        "componentTheme"));

        return search;
    }
}
