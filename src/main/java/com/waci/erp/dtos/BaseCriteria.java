package com.waci.erp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseCriteria {
 
   private String searchTerm;
   private int offset=0;
   private int limit=0;


 
}