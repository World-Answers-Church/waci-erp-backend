package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.PledgeDTO;
import com.waci.erp.services.PledgeService;
import com.waci.erp.services.impl.PledgeServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/pledges")
public class PledgeController {

    @Autowired
    PledgeService PledgeService;

    /**
     * Endpoint to register a microservice
     *
     * @param PledgeDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<PledgeDTO> savePledge(@RequestBody PledgeDTO PledgeDTO) {
        PledgeDTO responseDTO = PledgeDTO.fromModel(PledgeService.save(PledgeDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get Pledges
    @GetMapping("")
    public ResponseEntity<ResponseList<PledgeDTO>> getPledges(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                              @RequestParam(value = "offset", required = true) int offset,
                                                              @RequestParam(value = "limit", required = true) int limit) {
        Search search = PledgeServiceImpl.composeSearchObject(searchTerm);
        List<PledgeDTO> Pledges = PledgeService.getList(search, offset, limit).stream().map(new PledgeDTO()::fromModel).collect(Collectors.toList());
        int totalRecords = PledgeService.count(search);
        return ResponseEntity.ok().body(new ResponseList<>(Pledges, totalRecords, offset, limit));

    }
}
