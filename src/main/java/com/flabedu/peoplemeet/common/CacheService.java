package com.flabedu.peoplemeet.common;

import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.flabedu.peoplemeet.domain.interest.dto.InterestDto;
import com.flabedu.peoplemeet.domain.region.dto.RegionCacheDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CacheService {

	private static final String REGION_CACHE_KEY = "regions";

	private static final String INTEREST_CACHE_KEY = "interests";

	private final CacheManager cacheManager;

	public RegionCacheDto getRegion(final Long regionId) {
		return getCache(REGION_CACHE_KEY, regionId, RegionCacheDto.class);
	}

	public InterestDto getInterest(final Long interestId) {
		return getCache(INTEREST_CACHE_KEY, interestId, InterestDto.class);
	}

	private <T> T getCache(final String cacheName, final Long key, Class<T> clazz) {
		return Objects.requireNonNull(cacheManager.getCache(cacheName)).get(key, clazz);
	}
}
