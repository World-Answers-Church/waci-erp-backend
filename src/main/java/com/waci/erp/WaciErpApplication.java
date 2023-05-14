package com.waci.erp;

import com.waci.erp.dtos.LookupValueDTO;
import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.UserService;
import com.waci.erp.shared.constants.SecurityConstants;
import com.waci.erp.shared.models.Country;
import com.waci.erp.shared.models.Role;
import com.waci.erp.shared.models.User;
import com.waci.erp.shared.utils.CountryApiResponseDTO;
import com.waci.erp.shared.utils.RestService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
public class WaciErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaciErpApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner run(LookupValueService lookupValueService, UserService userService, RestService restService) {
		return args -> {
			try {
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Finacial Upliftment"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Acedemic Excellence"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Healing"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Deliverance"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Fullfilled Prophecy"));

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				Role super_admin_role = userService.saveRole(new Role(SecurityConstants.SUPER_ADMIN_ROLE, "Super admin role"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				Role super_admin_role = userService.getRoleByName(SecurityConstants.SUPER_ADMIN_ROLE);
				User user = new User(SecurityConstants.DEFAULT_ADMIN_USERNAME, SecurityConstants.DEFAULT_ADMIN_PASSWORD, new HashSet<>(Arrays.asList(super_admin_role)));
				user.setFirstName("System");
				user.setLastName("Administrator");
				userService.saveUser(user);
			} catch (Exception ex) {
ex.printStackTrace();
			}

			try {
				if (lookupValueService.countCountries(null) < 1) {
					CountryApiResponseDTO countryApiResponseDTO = restService.getPostsPlainJSON();
					for (CountryApiResponseDTO.CountryDTO data : countryApiResponseDTO.data) {
						Country country = lookupValueService.save(new Country(data.name, data.Iso3));
						Logger.getLogger(WaciErpApplication.class.getName()).info("Saved Country>>>>" + country.getName());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		};
	}
}
