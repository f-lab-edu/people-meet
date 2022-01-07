package com.flabedu.peoplemeet.service.region;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flabedu.peoplemeet.dto.region.RegionCacheDto;
import com.flabedu.peoplemeet.domain.region.repository.RegionRepository;

@SpringBootTest
class RegionServiceTest {

	@Autowired
	RegionService regionService;

	@Mock
	RegionRepository regionRepository;

	@Test
	void cacheTest() throws Exception {
		Long regionId = 1L;

		when(regionRepository.findById(regionId)).thenThrow(new IllegalCallerException());

		RegionCacheDto region = regionService.getRegion(regionId);
		RegionCacheDto region2 = regionService.getRegion(regionId);

	}

}