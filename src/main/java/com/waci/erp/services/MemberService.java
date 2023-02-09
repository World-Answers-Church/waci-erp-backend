package com.waci.erp.services;

import com.waci.erp.models.Member;

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
    Member saveMember(Member microservice);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param searchTerm
     * @param offset
     * @param limit
     * @return
     */
    List<Member> getMembers(String searchTerm, int offset, int limit);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    Member getMemberById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param code
     * @return
     */
    Member getMemberByPhoneNumber(String phoneNumber);

}
