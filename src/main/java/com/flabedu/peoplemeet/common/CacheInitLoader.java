package com.flabedu.peoplemeet.common;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.flabedu.peoplemeet.dto.interest.InterestDto;
import com.flabedu.peoplemeet.domain.interest.repository.InterestMapper;
import com.flabedu.peoplemeet.dto.region.RegionCacheDto;
import com.flabedu.peoplemeet.domain.region.repository.RegionMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CacheInitLoader {

	private final CacheManager cacheManager;

	private final RegionMapper regionMapper;

	private final InterestMapper interestMapper;

	@PostConstruct
	public void init(){
		regionCacheLoad();
		interestCacheLoad();
	}

	private void interestCacheLoad() {
		for (InterestDto interest : interestMapper.findAll()) {
			Objects.requireNonNull(cacheManager.getCache("interests"))
				.put(interest.getInterestId(), interest);
		}
	}

	private void regionCacheLoad() {
		for (RegionCacheDto regionCacheDto : regionMapper.findAll()) {
			Objects.requireNonNull(cacheManager.getCache("regions"))
						.put(regionCacheDto.getRegionId(), regionCacheDto);
		}
	}

}
