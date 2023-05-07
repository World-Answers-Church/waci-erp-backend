package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.models.Testimony;
import com.waci.erp.services.TestimonyService;
import com.waci.erp.services.impl.TestimonyServiceImpl;
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
    @PostMapping("")
    public ResponseEntity<TestimonyDTO> save(@RequestBody TestimonyDTO testimonyDTO) {
        TestimonyDTO responseDTO = new ModelMapper().map( dbService.save(testimonyDTO), TestimonyDTO.class);
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("")
    public ResponseEntity<List<Testimony>> search(@RequestParam("searchTerm") String searchTerm,
                                                  @RequestParam("offset") int limit,
                                                  @RequestParam("limit") int offset) {
        Search search= TestimonyServiceImpl.composeSearchObject(searchTerm);
        List<Testimony> members= dbService.getList(search,offset,limit);
        return ResponseEntity.ok().body(members);

    }
}
