package com.waci.erp.shared.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Bean to handle JWT operations
 */
@Component
public class TokenProvider{
    private Algorithm algorithm;

    public TokenProvider() {
        String secretKey="ANY_FROM_ENVIRONMENT";
        this.algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    /**
     * Validates a supplied Jwt
     * @param authToken
     * @return
     * @throws JWTVerificationException
     */
    public boolean validateToken(String authToken) throws JWTVerificationException {
        JWTVerifier jWTVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jWTVerifier.verify(authToken);
        String username = decodedJWT.getSubject();
        return username!=null;
    }


    @Data
    public class TokenPair{
        private String accessToken;
        private String refreshToken;
        public TokenPair(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

}
