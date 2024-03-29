package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.models.prayers.Member;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Member}
 */
public interface MemberService {
    /**
     * Saves a microservice to the database
     * @param microservice
     * @return
     */
    Member saveMember(MemberDTO memberDTO);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param search
     * @return
     */
    List<Member> getMembers(Search search,int offset, int limit);

   int countMembers(Search search);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    Member getMemberById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param phoneNumber
     * @return
     */
    Member getMemberByPhoneNumber(String phoneNumber);

}
