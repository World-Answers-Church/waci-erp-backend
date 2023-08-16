package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.LookupDTO;
import com.waci.erp.dtos.LookupValueDTO;
import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.impl.LookupServiceImpl;
import com.waci.erp.shared.api.BaseResponse;
import com.waci.erp.shared.api.ResponseList;
import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.models.Country;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/lookups")
public class LookupsController {

    @Autowired
    LookupValueService lookupService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint to register a microservice
     *
     * @return
     */
    @GetMapping("/genders")
    public ResponseEntity<ResponseList<LookupDTO>> getGenders() {
        List<LookupDTO> genders=   Arrays.stream(Gender.values()).map(r->new LookupDTO(r.ordinal(),r.getUiName())).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ResponseList<>(genders,genders.size(),0,0));
    }

    /**
     * Endpoint to register a microservice
     *
     * @return
     */
    @GetMapping("/lookup-types")
    public ResponseEntity<ResponseList<LookupDTO>> getLookupTypes() {
        List<LookupDTO> genders=   Arrays.stream(LookupType.values()).map(r->new LookupDTO(r.ordinal(),r.getUiName())).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ResponseList<>(genders,genders.size(),0,0));
    }

    @PostMapping("/lookup-values")
    public ResponseEntity<BaseResponse> saveLookupValue(@RequestBody LookupValueDTO lookupValueDTO) {
        lookupService.save(lookupValueDTO);
        return ResponseEntity.ok().body(new BaseResponse(true));
    }
    /**
     * Endpoint to register a microservice
     *
     * @return
     */
    @GetMapping("/lookup-values")
    public ResponseEntity<ResponseList<LookupValueDTO>> getLookupValues(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                                                        @RequestParam(name = "lookupTypeId", required = false) Long lookupTypeId,
                                                                        @RequestParam(name = "offset", required = false) int limit,
                                                                        @RequestParam(name = "limit",required = false) int offset) {
        Search search = LookupServiceImpl.composeSearchObjectForLookupValue(searchTerm);


        if(lookupTypeId!=null){
            LookupType lookupType= LookupType.getById(lookupTypeId);
            if(lookupType!=null) {
                search.addFilterEqual("type", lookupType);
            }
        }
        List<LookupValueDTO> members = lookupService.getList(search, offset, limit).stream().map(r -> new LookupValueDTO().fromModel(r)).collect(Collectors.toList());
        int totalRecords = (int) lookupService.countLookupValues(search);
        return ResponseEntity.ok().body(new ResponseList<>(members, totalRecords, offset, limit));
    }
    //Build get members
    @GetMapping("/countries")
    public ResponseEntity<ResponseList<Country>> getCountries(@RequestParam( required = false, value="searchTerm"  ) String searchTerm,
                                                              @RequestParam("offset") int limit,
                                                              @RequestParam("limit") int offset) {
        List<Country> countries = lookupService.getCountries(LookupServiceImpl.composeSearchObjectForCountry(searchTerm),offset,limit);
        return ResponseEntity.ok().body(new ResponseList<>(countries, countries.size(), 0,0));

    }
}
