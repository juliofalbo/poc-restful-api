package com.julio.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonFilter("SomeBeanFilter")
@AllArgsConstructor
@Data
public class SomeBean {
	
	private String field1;
	
	private String field2;
	
	private String field3;

}
