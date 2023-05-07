package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.CountryDao;
import com.waci.erp.daos.RoleDao;
import com.waci.erp.daos.UserDao;
import com.waci.erp.services.UserService;
import com.waci.erp.shared.api.AuthDTO;
import com.waci.erp.shared.api.RoleDTO;
import com.waci.erp.shared.api.UserDTO;
import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.constants.PermissionConstant;
import com.waci.erp.shared.constants.RecordStatus;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.exceptions.ResourceNotFoundException;
import com.waci.erp.shared.models.Country;
import com.waci.erp.shared.models.Role;
import com.waci.erp.shared.models.User;
import com.waci.erp.shared.utils.CustomSearchUtils;
import com.waci.erp.shared.utils.PassEncTech4;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userRepository;
    @Autowired
    RoleDao roleRepository;

    @Autowired
    CountryDao countryDao;

    @Override
    public User saveUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            throw new OperationFailedException("User with username exists");
        }
        log.info("Saving new user to DB", user.getUsername());


        user.setPassword(PassEncTech4.generateSecurePassword(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public User saveUser(UserDTO dto) throws ValidationException {

        if (dto.id!=0) {
            throw new ValidationException("Editing not allowed on this method");
        }
        if (StringUtils.isBlank(dto.username)) {
            throw new ValidationException("Missing username");
        }
        if (StringUtils.isBlank(dto.firstName)) {
            throw new ValidationException("Missing first name");
        }

        if (StringUtils.isBlank(dto.lastName)) {
            throw new ValidationException("Missing lastname");
        }

        if (dto.id==0&&StringUtils.isBlank(dto.initialPassword)) {
            throw new ValidationException("Missing initialPassword");
        }
        User existsWithUsername = getUserByUsername(dto.username);
        if (existsWithUsername != null && existsWithUsername.getId() != dto.id) {
            throw new ValidationException("User with username exists");
        }

        Gender gender= Gender.fromId((int) dto.genderId);
        if(gender==null){
            throw new ValidationException("Missing/Invalid Gender Id");
        }
        User user = new User();
        user.setPassword(PassEncTech4.generateSecurePassword(dto.initialPassword));
        user.setUsername(dto.username);

        if(dto.countryId!=0){
            Country country=countryDao.getReference(dto.countryId);

            if(country==null){
                throw new ValidationException("Missing Country");
            }
            user.setCountry(country);
        }
        user.setEmailAddress(dto.emailAddress);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setPhoneNumber(dto.phoneNumber);


        user.setGender(gender);

        for (long roleId : dto.roleIds) {
            Role role = roleRepository.getReference(roleId);
            user.addRole(role);
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO dto) throws ValidationException {

        if (dto.id==0) {
            throw new ValidationException("Missing Id");
        }

        User user = getUserById(dto.id);
        if (user== null) {
            throw new ValidationException("User with id not found");
        }


        if (StringUtils.isBlank(dto.firstName)) {
            throw new ValidationException("Missing first name");
        }

        if (StringUtils.isBlank(dto.lastName)) {
            throw new ValidationException("Missing lastname");
        }

        if(dto.countryId!=0){
            Country country=countryDao.getReference(dto.countryId);

            if(country==null){
                throw new ValidationException("Missing Country");
            }
            user.setCountry(country);
        }
        user.setEmailAddress(dto.emailAddress);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setPhoneNumber(dto.phoneNumber);

        Gender gender= Gender.fromId((int) dto.genderId);
        if(gender==null){
            throw new ValidationException("Missing/Invalid Gender Id");
        }
        user.setGender(gender);

        for (long roleId : dto.roleIds) {
            Role role = roleRepository.getReference(roleId);
            user.addRole(role);
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) throws ValidationException {
        if (userId==0) {
            throw new ValidationException("Missing Id");
        }

        User existsWithId = getUserById(userId);
        if (existsWithId == null) {
            throw new ValidationException("User with id not found");
        }

        existsWithId.setRecordStatus(RecordStatus.DELETED);
        userRepository.save(existsWithId);

    }


    @Override
    public List<User> getAllUsers(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return userRepository.search(search);
    }
    public long countAllUsers(Search search){
        return userRepository.count(search);
    }
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public UserDTO authenticateUser(AuthDTO authDTO) throws ValidationException {
        if (authDTO == null) {
            throw new ValidationException("No data specified");
        }
        if (StringUtils.isBlank(authDTO.getUsername())) {
            throw new ValidationException("Missing Username");
        }

        if (StringUtils.isBlank(authDTO.getPassword())) {
            throw new ValidationException("Missing password");
        }

        User user = getUserByUsername(authDTO.getUsername());

        if (user == null) {
            throw new ValidationException("Unknown credentials");
        }
        if (!PassEncTech4.verifyUserPassword(authDTO.getPassword(), user.getPassword())) {
            throw new ValidationException("Invalid Username or Password");
        }
 return UserDTO.fromModel(user);
    }



    @Override
    public User getUserByUsername(String username) {
        return userRepository.searchUnique(new Search().addFilterEqual("username",username));
    }

    @Override
    public Role saveRole(Role role) {
        if (getRoleByName(role.getName()) != null) {
            throw new OperationFailedException("Role with same name exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role saveRole(RoleDTO dto) {

        if (StringUtils.isBlank(dto.getName())) {
            throw new OperationFailedException("Missing name");
        }

        if (StringUtils.isBlank(dto.getDescription())) {
            throw new OperationFailedException("Missing description");
        }
        Role existsWithName = getRoleByName(dto.getName());
        if (existsWithName != null && existsWithName.getId() != dto.getId()) {
            throw new OperationFailedException("Role with same name exists");
        }

        Role newRole = new Role();
        newRole.setId(dto.getId());
        newRole.setName(dto.getName());
        newRole.setDescription(dto.getDescription());

        for (long permissionId : dto.getPermissionIds()) {
            PermissionConstant permission = PermissionConstant.getById(permissionId);
            newRole.addPermission(permission);
        }

        return roleRepository.save(newRole);
    }


    @Override
    public List<Role> getAllRoles(Search search, int offset, int limit) {
        search.setFirstResult(offset);
        search.setMaxResults(limit);
        return roleRepository.search(search);
    }


    public long countRoles(Search search){
        return  roleRepository.count(search);
    }


    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.searchUnique(new Search().addFilterEqual("name",roleName));
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = getUserByUsername(username);
        Role role = getRoleByName(roleName);
        user.addRole(role);
        userRepository.save(user);
    }

    public static Search composeSearchObjectForUser(String searchTerm) {
       Search search = CustomSearchUtils.generateSearchTerms(searchTerm,   Arrays.asList("username","lastName", "firstName"));
        search.addSortDesc("id");
        return  search;
    }

    public static Search composeSearchObjectForRole(String searchTerm) {
        Search search = CustomSearchUtils.generateSearchTerms(searchTerm,   Arrays.asList("name","description"));

        return  search;
    }

}

