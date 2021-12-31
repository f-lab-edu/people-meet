package com.flabedu.peoplemeet.dto.region;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Alias("regionCacheDto")
@Getter
@NoArgsConstructor
public class RegionCacheDto {

	private Long regionId;

	private String name;

	private Long level;

	private Long parentRegionId;
}
