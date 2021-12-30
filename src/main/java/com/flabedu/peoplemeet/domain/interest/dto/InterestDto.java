package com.flabedu.peoplemeet.domain.interest.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Alias("interestDto")
@Getter
@NoArgsConstructor
public class InterestDto {

	private Long interestId;

	private String name;

	private String categoryName;
}
