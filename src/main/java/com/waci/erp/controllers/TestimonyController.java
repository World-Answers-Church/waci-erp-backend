package com.waci.erp.controllers;

import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.models.Member;
import com.waci.erp.models.Testimony;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.TestimonyService;
import com.waci.erp.shared.searchutils.FilterRequest;
import com.waci.erp.shared.searchutils.SearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/testimonies")
public class TestimonyController {

    @Autowired
    TestimonyService dbService;

    /**
     * Endpoint to register a microservice
     *
     * @param testimonyDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<TestimonyDTO> save(@RequestBody TestimonyDTO testimonyDTO) {
        TestimonyDTO responseDTO = new ModelMapper().map( dbService.save(testimonyDTO), TestimonyDTO.class);
        return ResponseEntity.ok().body(responseDTO);
    }


    /**
     *
     * @param criteria
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<List<Testimony>> search(BaseCriteria criteria) {
        List<Testimony> members= dbService.getList(criteria);
        return ResponseEntity.ok().body(members);

    }
}
