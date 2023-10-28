package com.waci.erp.shared.api;

import com.waci.erp.dtos.OrganisationDTO;
import com.waci.erp.shared.security.TokenProvider;
import lombok.Data;

@Data
public class FullUserDTO extends BaseResponse {
    public UserDTO user;
    public OrganisationDTO church;
    public String accessToken;
    public String refreshToken;

    public FullUserDTO(UserDTO user, TokenProvider.TokenPair tokenPair) {
        this.user= user;
        this.refreshToken = tokenPair.getRefreshToken();
        this.accessToken = tokenPair.getAccessToken();

    }

    public FullUserDTO(UserDTO user, TokenProvider.TokenPair tokenPair, OrganisationDTO organisationDTO) {
        this.user= user;
        this.refreshToken = tokenPair.getRefreshToken();
        this.accessToken = tokenPair.getAccessToken();
this.church=organisationDTO;
    }
}
