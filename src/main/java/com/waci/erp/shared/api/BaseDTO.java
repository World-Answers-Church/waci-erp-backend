package com.waci.erp.shared.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseDTO {
	private String status;
	private String message;
	private long createdById;
	private String createdByUsername;
	private long changedById;
	private String changedByUserName;
	private LocalDateTime dateCreated;
	private LocalDateTime dateChanged;
	private String recordStatus;
}
