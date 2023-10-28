package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.services.TestimonyService;
import com.waci.erp.services.impl.TestimonyServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        TestimonyDTO responseDTO = new TestimonyDTO().fromModel( dbService.save(testimonyDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("")
    public ResponseEntity<ResponseList<TestimonyDTO>> search(@RequestParam("searchTerm") String searchTerm,
                                                          @RequestParam("offset") Integer limit,
                                                          @RequestParam("limit") Integer offset,
                                                             @RequestParam("memberId") Long memberId
                                                             ) {
        Search search= TestimonyServiceImpl.composeSearchObject(searchTerm);
     if(memberId!=null){
         search.addFilterEqual("member.id",memberId);
     }



        long recordCount= dbService.count(search);
        List<TestimonyDTO> records= dbService.getList(search,offset,limit).stream().map(r->new TestimonyDTO().fromModel(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ResponseList<>(records,recordCount,offset,limit));

    }
}
