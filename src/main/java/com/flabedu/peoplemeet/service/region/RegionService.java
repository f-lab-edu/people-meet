package com.flabedu.peoplemeet.service.region;

import org.springframework.stereotype.Service;

import com.flabedu.peoplemeet.common.CacheService;
import com.flabedu.peoplemeet.domain.region.dto.RegionCacheDto;
import com.flabedu.peoplemeet.domain.region.repository.RegionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {

	private final RegionMapper regionMapper;

	private final CacheService cacheService;

	public RegionCacheDto getRegion(final Long regionId){
		return cacheService.getRegion(regionId);
	}

}
