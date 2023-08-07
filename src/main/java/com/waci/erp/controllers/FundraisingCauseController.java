package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.FundraisingCauseDTO;
import com.waci.erp.services.FundraisingCauseService;
import com.waci.erp.services.impl.FundraisingCauseServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/fundraising-causes")
public class FundraisingCauseController {

    @Autowired
    FundraisingCauseService fundraisingCauseService;

    /**
     * Endpoint to register a microservice
     *
     * @param FundraisingCauseDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<FundraisingCauseDTO> saveFundraisingCause(@RequestBody FundraisingCauseDTO FundraisingCauseDTO) {
        FundraisingCauseDTO responseDTO = FundraisingCauseDTO.fromModel(fundraisingCauseService.save(FundraisingCauseDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get FundraisingCauses
    @GetMapping("")
    public ResponseEntity<ResponseList<FundraisingCauseDTO>> getFundraisingCauses(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                              @RequestParam(value = "offset", required = true) int offset,
                                                              @RequestParam(value = "limit", required = true) int limit) {
        Search search = FundraisingCauseServiceImpl.composeSearchObject(searchTerm);
        List<FundraisingCauseDTO> fundraisingCauseDTOS = fundraisingCauseService.getList(search, offset, limit).stream().map(new FundraisingCauseDTO()::fromModel).collect(Collectors.toList());
        int totalRecords = fundraisingCauseService.count(search);
        return ResponseEntity.ok().body(new ResponseList<>(fundraisingCauseDTOS, totalRecords, offset, limit));

    }
}
