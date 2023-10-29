package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.impl.MemberServiceImpl;
import com.waci.erp.shared.api.ResponseList;
import com.waci.erp.shared.api.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @PostMapping("")
    public ResponseEntity<MemberDTO> saveMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO responseDTO = MemberDTO.fromModel(memberService.saveMember(memberDTO));
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<MemberDTO>> getMemberById(@PathVariable(value = "id") Long id) {
        MemberDTO responseDTO = MemberDTO.fromModel(memberService.getMemberById(id));
        return ResponseEntity.ok().body(new ResponseObject<>( responseDTO));
    }

    //Build get members
    @GetMapping("")
    public ResponseEntity<ResponseList<MemberDTO>> getMembers(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                              @RequestParam(value = "offset", required = true) int offset,
                                                              @RequestParam(value = "limit", required = true) int limit) {
        Search search = MemberServiceImpl.composeSearchObject(searchTerm);
        List<MemberDTO> members = memberService.getMembers(search, offset, limit).stream().map(MemberDTO::fromModel).collect(Collectors.toList());
        int totalRecords = memberService.countMembers(search);
        return ResponseEntity.ok().body(new ResponseList<>(members, totalRecords, offset, limit));

    }
}
