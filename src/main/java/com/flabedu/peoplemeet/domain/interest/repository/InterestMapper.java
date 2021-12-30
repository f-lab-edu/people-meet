package com.flabedu.peoplemeet.domain.interest.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flabedu.peoplemeet.domain.interest.dto.InterestDto;

@Mapper
public interface InterestMapper {

	List<InterestDto> findAll();
}
