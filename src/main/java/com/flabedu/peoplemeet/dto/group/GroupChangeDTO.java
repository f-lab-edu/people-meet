package com.flabedu.peoplemeet.dto.group;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.flabedu.peoplemeet.dto.interest.InterestId;
import com.flabedu.peoplemeet.dto.region.RegionId;

import lombok.Getter;

@Getter
public class GroupChangeDTO {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotEmpty
	private List<RegionId> regions;

	@NotEmpty
	private List<InterestId> interests;

	@Min(1)
	private Integer maxPeople;

	private String titleImageUri;
}
