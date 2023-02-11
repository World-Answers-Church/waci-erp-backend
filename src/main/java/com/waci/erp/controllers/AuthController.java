package com.waci.erp.controllers;

import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.models.Member;
import com.waci.erp.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    MemberService memberService;

    /**
     * Endpoint to register a microservice
     *
     * @param memberDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<MemberDTO> saveMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO responseDTO = new ModelMapper().map( memberService.saveMember(memberDTO.toMember()),MemberDTO.class);
        return ResponseEntity.ok().body(responseDTO);
    }


    //Build get members
    @PostMapping("/refresh/token")
    public ResponseEntity<List<MemberDTO>> getMembers(@RequestParam("searchTerm") String searchTerm,
                                                      @RequestParam("offset") int limit,
                                                      @RequestParam("limit") int offset) {
        List<Member> members= memberService.getMembers(searchTerm,offset,limit);
        List<MemberDTO> membersDTO= (List<MemberDTO>) new ModelMapper().map(members,List.class);
        return ResponseEntity.ok().body(membersDTO);

    }
}
