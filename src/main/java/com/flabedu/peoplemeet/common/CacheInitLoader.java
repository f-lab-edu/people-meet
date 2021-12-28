package com.flabedu.peoplemeet.common;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.flabedu.peoplemeet.domain.region.dto.RegionCacheDto;
import com.flabedu.peoplemeet.domain.region.repository.RegionMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CacheInitLoader {

	private final CacheManager cacheManager;

	private final RegionMapper regionMapper;

	@PostConstruct
	public void init(){
		regionCacheLoad();
	}

	private void regionCacheLoad() {
		for (RegionCacheDto regionCacheDto : regionMapper.findAll()) {
			Objects.requireNonNull(cacheManager.getCache("regions"))
						.put(regionCacheDto.getRegionId(), regionCacheDto);
		}
	}

}
