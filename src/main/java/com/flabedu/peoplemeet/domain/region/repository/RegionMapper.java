package com.flabedu.peoplemeet.domain.region.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flabedu.peoplemeet.dto.region.RegionCacheDto;

@Mapper
public interface RegionMapper {

	List<RegionCacheDto> findAll();

	RegionCacheDto findById(String regionId);
}
