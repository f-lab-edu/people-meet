package com.flabedu.peoplemeet.common;

import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.flabedu.peoplemeet.domain.region.dto.RegionCacheDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CacheService {

	private static final String REGION_CACHE_KEY = "regions";

	private final CacheManager cacheManager;

	public RegionCacheDto getRegion(final Long regionId) {
		return Objects.requireNonNull(cacheManager.getCache(REGION_CACHE_KEY))
					.get(regionId, RegionCacheDto.class);
	}

}
