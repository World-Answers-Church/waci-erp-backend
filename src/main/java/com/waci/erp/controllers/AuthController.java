package com.waci.erp.controllers;

import com.waci.erp.services.UserService;
import com.waci.erp.shared.api.AuthDTO;
import com.waci.erp.shared.api.FullUserDTO;
import com.waci.erp.shared.api.UserDTO;
import com.waci.erp.shared.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    UserService userService;

    /**
     * Endpoint to register a microservice
     *
     * @param authDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<FullUserDTO> login(@RequestBody AuthDTO authDTO) throws ValidationException {
        UserDTO user = userService.authenticateUser(authDTO);
        TokenProvider.TokenPair tokenPair = tokenProvider.createToken(user, authDTO.isRememberMe());
        return ResponseEntity.ok().body(new FullUserDTO(user, tokenPair));
    }


    //Build get members
    @PostMapping("/refresh/token")
    public ResponseEntity<TokenProvider.TokenPair> getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws ValidationException {
        String authorisationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorisationHeader != null && authorisationHeader.startsWith("Bearer ")) {

            String refreshToken = authorisationHeader.substring("Bearer ".length());
            TokenProvider.TokenPair tokenPair = tokenProvider.refreshAccessToken(refreshToken);
            return ResponseEntity.ok().body(tokenPair);
        } else {
            throw new ValidationException("Missing Refresh Token");
        }
    }
}
