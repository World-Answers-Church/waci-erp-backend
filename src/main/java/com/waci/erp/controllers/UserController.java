package com.waci.erp.controllers;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.services.UserService;
import com.waci.erp.services.impl.UserServiceImpl;
import com.waci.erp.shared.api.*;
import com.waci.erp.shared.models.Role;
import com.waci.erp.shared.models.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * Endpoint to register a microservice
     *
     * @return
     */
    @PostMapping("")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws ValidationException {

        User user= userService.saveUser(userDTO);
        return ResponseEntity.ok().body(UserDTO.fromModel(user));

    }

    /**
     * Endpoint to register a microservice
     *
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") long id, @RequestBody UserDTO userDTO) throws ValidationException {
        userDTO.id = id;
        User user =  userService.updateUser(userDTO);
        return ResponseEntity.ok().body(UserDTO.fromModel(user));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable(value = "id",required = true) long id) throws ValidationException {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new BaseResponse(true));
    }
    //Get Users
    @GetMapping("")
    public ResponseEntity<ResponseList<User>> getUsers(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                       @RequestParam(value = "offset", required = false) int offset,
                                                       @RequestParam(value = "limit", required = false) int limit) {
        Search search = UserServiceImpl.composeSearchObjectForUser(searchTerm);
        List<User> users = userService.getAllUsers(search, offset, limit);
        long totalRecords=userService.countAllUsers(search);
        return ResponseEntity.ok().body(new ResponseList<>(users,(int)totalRecords,offset,limit));

    }

    //Get Roles
    @GetMapping("/roles")
    public ResponseEntity<ResponseList<RoleDTO>> getRoles(@RequestParam(value = "searchTerm", required = false) String searchTerm,
                                                          @RequestParam("offset") int offset,
                                                          @RequestParam("limit") int limit) {

        Search search = UserServiceImpl.composeSearchObjectForRole(searchTerm);

        List<RoleDTO> roles = userService.getAllRoles(search,offset,limit).stream().map(RoleDTO::fromRole).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ResponseList<>(roles,10,offset,limit));
    }

    /**
     * Endpoint to register a microservice
     *
     * @return
     */
    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable(value = "id") long id, @RequestBody RoleDTO dto) throws ValidationException {
        dto.setId(id);
        Role savedRole =  userService.saveRole(dto);
        return ResponseEntity.ok().body(RoleDTO.fromRole(savedRole));

    }

    //Get permissions
    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionDTO>> getPermissions() {
        List<PermissionDTO> users = PermissionDTO.getOrderedPermissions();
        return ResponseEntity.ok().body(users);
    }

    /**
     * Save Role
     *
     * @param role
     * @return
     */
    @PostMapping("/roles")
    public ResponseEntity<RoleDTO> saveRoles(@RequestBody RoleDTO role) {
        return ResponseEntity.ok().body(RoleDTO.fromRole(userService.saveRole(role)));
    }
}
