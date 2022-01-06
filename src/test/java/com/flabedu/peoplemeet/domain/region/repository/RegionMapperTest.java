package com.flabedu.peoplemeet.domain.region.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flabedu.peoplemeet.domain.region.dto.RegionCacheDto;

@SpringBootTest
class RegionMapperTest {

	@Autowired
	RegionMapper regionMapper;

	@Test
	void findAll(){
		List<RegionCacheDto> all = regionMapper.findAll();

		assertThat(all).isNotEmpty();
		all.get(0).getRegionId();
	}

}