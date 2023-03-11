package com.waci.erp;

import com.waci.erp.dtos.LookupValueDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.services.LookupValueService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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
	CommandLineRunner run(LookupValueService lookupValueService) {
		return args -> {
			try {
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Finacial Upliftment"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Acedemic Excellence"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Healing"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Deliverance"));
				lookupValueService.save(new LookupValueDTO(LookupType.TESTIMONY_TYPE,"Fullfilled Prophecy"));

			} catch (Exception ex) {

			}

		};
	}
}
