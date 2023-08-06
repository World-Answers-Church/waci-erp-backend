package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.PledgePaymentDTO;
import com.waci.erp.services.PledgePaymentService;
import com.waci.erp.services.impl.PledgePaymentServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/pledge-payments")
public class PledgePaymentController {

    @Autowired
    PledgePaymentService pledgePaymentService;

    /**r
     * Endpoint to register a microservice
     *
     * @param pledgePaymentDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<PledgePaymentDTO> savePledgePayment(@RequestBody PledgePaymentDTO pledgePaymentDTO) {
        PledgePaymentDTO responseDTO = new PledgePaymentDTO().fromModel(pledgePaymentService.save(pledgePaymentDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get pledgePayments
    @GetMapping("")
    public ResponseEntity<ResponseList<PledgePaymentDTO>> getPledgePayments(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                              @RequestParam(value = "offset", required = true) int offset,
                                                              @RequestParam(value = "limit", required = true) int limit) {
        Search search = PledgePaymentServiceImpl.composeSearchObject(searchTerm);
        List<PledgePaymentDTO> pledgePayments = pledgePaymentService.getList(search, offset, limit).stream().map(new PledgePaymentDTO()::fromModel).collect(Collectors.toList());
        int totalRecords = pledgePaymentService.count(search);
        return ResponseEntity.ok().body(new ResponseList<>(pledgePayments, totalRecords, offset, limit));

    }
}
