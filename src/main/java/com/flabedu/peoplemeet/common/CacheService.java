package com.flabedu.peoplemeet.common;

import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.flabedu.peoplemeet.dto.interest.InterestDto;
import com.flabedu.peoplemeet.dto.region.RegionCacheDto;

@Service
public class CacheService {

	private static final String REGION_CACHE_KEY = "regions";

	private static final String INTEREST_CACHE_KEY = "interests";

	private static CacheManager cacheManager;

	CacheService(CacheManager cacheManager) {
		CacheService.cacheManager = cacheManager;
	}

	public static RegionCacheDto getRegion(final Long regionId) {
		return getCache(REGION_CACHE_KEY, regionId, RegionCacheDto.class);
	}

	public static InterestDto getInterest(final Long interestId) {
		return getCache(INTEREST_CACHE_KEY, interestId, InterestDto.class);
	}

	private static <T> T getCache(final String cacheName, final Long key, Class<T> clazz) {
		return Objects.requireNonNull(cacheManager.getCache(cacheName)).get(key, clazz);
	}
}
