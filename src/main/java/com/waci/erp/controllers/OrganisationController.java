package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.OrganisationDTO;
import com.waci.erp.dtos.OrganisationSettingsDTO;
import com.waci.erp.services.OrganisationService;
import com.waci.erp.services.impl.OrganisationServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/organisations")
public class OrganisationController {

    @Autowired
    OrganisationService organisationService;

    /**
     * Endpoint to register a microservice
     *
     * @param organisationDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<OrganisationDTO> saveOrganisation(@RequestBody OrganisationDTO organisationDTO) {
        OrganisationDTO responseDTO = OrganisationDTO.fromModel(organisationService.save(organisationDTO));
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/settings")
    public ResponseEntity<OrganisationSettingsDTO> saveOrganisationSettings(@RequestBody OrganisationSettingsDTO organisationSettingsDTO) {
        OrganisationSettingsDTO responseDTO = OrganisationSettingsDTO.fromModel(organisationService.save(organisationSettingsDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get organisations
    @GetMapping("")
    public ResponseEntity<ResponseList<OrganisationDTO>> getOrganisations(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                              @RequestParam(value = "offset", required = true) int offset,
                                                              @RequestParam(value = "limit", required = true) int limit) {
        Search search = OrganisationServiceImpl.composeSearchObject(searchTerm);
        List<OrganisationDTO> organisations = organisationService.getList(search, offset, limit).stream().map(OrganisationDTO::fromModel).collect(Collectors.toList());
        int totalRecords = organisationService.count(search);
        return ResponseEntity.ok().body(new ResponseList<>(organisations, totalRecords, offset, limit));

    }
}
