package com.flabedu.peoplemeet.domain.interest.repository;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Alias("groupInterest")
@Getter
@Setter
@NoArgsConstructor
public class GroupInterestVO {

	private Long id;

	private String interestName;

	private Integer priority;

}
