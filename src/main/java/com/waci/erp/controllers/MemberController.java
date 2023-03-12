package com.waci.erp.controllers;

import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.models.Member;
import com.waci.erp.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

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
        MemberDTO responseDTO = new ModelMapper().map( memberService.saveMember(memberDTO.toMember()),MemberDTO.class);
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get members
    @GetMapping("/get")
    public ResponseEntity<List<Member>> getMembers(@RequestParam("searchTerm") String searchTerm,
                                                      @RequestParam("offset") int limit,
                                                      @RequestParam("limit") int offset) {
        BaseCriteria baseCriteria= new BaseCriteria(searchTerm,offset,limit);
        List<Member> members= memberService.getMembers(baseCriteria);
        return ResponseEntity.ok().body(members);

    }
}
