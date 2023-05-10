package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.models.Member;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.impl.MemberServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * Endpoint to register a microservice
     *
     * @param memberDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<MemberDTO> saveMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO responseDTO = MemberDTO.fromModel( memberService.saveMember(memberDTO));
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get members
    @GetMapping("/get")
    public ResponseEntity<ResponseList<MemberDTO>> getMembers(@RequestParam("searchTerm") String searchTerm,
                                                           @RequestParam("offset") int limit,
                                                           @RequestParam("limit") int offset) {
        Search search= MemberServiceImpl.composeSearchObject(searchTerm);
        List<MemberDTO> members= memberService.getMembers(search,offset,limit).stream().map(r->MemberDTO.fromModel(r)).collect(Collectors.toList());

      int totalRecords =memberService.countMembers(search);
        return ResponseEntity.ok().body(new ResponseList<>(members,totalRecords,offset,limit));

    }
}
