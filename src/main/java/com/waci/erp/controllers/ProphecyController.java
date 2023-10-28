package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.ProphecyDTO;
import com.waci.erp.services.ProphecyService;
import com.waci.erp.services.impl.ProphecyServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/prophecies")
public class ProphecyController {

    @Autowired
    ProphecyService dbService;

    /**
     * Endpoint to register a microservice
     *
     * @param prophecyDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ProphecyDTO> save(@RequestBody ProphecyDTO prophecyDTO) {
        ProphecyDTO responseDTO = new ProphecyDTO().fromModel( dbService.save(prophecyDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("")
    public ResponseEntity<ResponseList<ProphecyDTO>> search(@RequestParam("searchTerm") String searchTerm,
                                                          @RequestParam("offset") int limit,
                                                          @RequestParam("limit") int offset,
                                                                     @RequestParam("memberId") Long memberId) {


        Search search= ProphecyServiceImpl.composeSearchObject(searchTerm);

        if(memberId!=null){
            search.addFilterEqual("member.id",memberId);
        }
        long recordCount= dbService.count(search);
        List<ProphecyDTO> records= dbService.getList(search,offset,limit).stream().map(r->new ProphecyDTO().fromModel(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ResponseList<>(records,recordCount,offset,limit));

    }
}
