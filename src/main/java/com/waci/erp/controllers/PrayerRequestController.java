package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.PrayerRequestDTO;
import com.waci.erp.services.PrayerRequestService;
import com.waci.erp.services.PrayerRequestService;
import com.waci.erp.services.impl.PrayerRequestServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/prayer-requests")
public class PrayerRequestController {

    @Autowired
    PrayerRequestService dbService;

    /**
     * Endpoint to register a microservice
     *
     * @param prayerRequestDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<PrayerRequestDTO> save(@RequestBody PrayerRequestDTO prayerRequestDTO) {
        PrayerRequestDTO responseDTO = new PrayerRequestDTO().fromModel( dbService.save(prayerRequestDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("")
    public ResponseEntity<ResponseList<PrayerRequestDTO>> search(@RequestParam("searchTerm") String searchTerm,
                                                          @RequestParam("offset") int limit,
                                                          @RequestParam("limit") int offset) {
        Search search= PrayerRequestServiceImpl.composeSearchObject(searchTerm);
        long recordCount= dbService.count(search);
        List<PrayerRequestDTO> records= dbService.getList(search,offset,limit).stream().map(r->new PrayerRequestDTO().fromModel(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ResponseList<>(records,recordCount,offset,limit));

    }
}
