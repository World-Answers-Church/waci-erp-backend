package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.shared.api.AuthDTO;
import com.waci.erp.shared.api.RoleDTO;
import com.waci.erp.shared.api.UserDTO;
import com.waci.erp.shared.models.Role;
import com.waci.erp.shared.models.User;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Handles CRUD operations on the {@link  User}
 */
public interface UserService {

        /**
         *
         * @param user
         * @return
         */
        User saveUser(User user);

        User saveUser(UserDTO userDTO) throws ValidationException;

        User updateUser(UserDTO userDTO) throws ValidationException;
        void deleteUser(long userId) throws ValidationException;

        /**
         *
         * @return
         */
        List<User> getAllUsers(Search search, int offset, int limit);

        long countAllUsers(Search search);

        /**
         *
         * @param id
         * @return
         */
        User getUserById(long id);

        UserDTO authenticateUser(AuthDTO authDTO) throws ValidationException;


        /**
         *
         * @param username
         * @return
         */
        User getUserByUsername(String username);


        /**
         *
         * @param role
         * @return
         */
        Role saveRole(Role role);
        Role saveRole(RoleDTO role);

        /**
         *
         * @return
         */
        List<Role> getAllRoles(Search search, int offset, int limit);

        long countRoles(Search search);



        /**
         *
         * @param roleName
         * @return
         */
        Role getRoleByName(String roleName);

        /**
         *
         * @param username
         * @param roleName
         */
        void addRoleToUser(String username, String roleName);

}
