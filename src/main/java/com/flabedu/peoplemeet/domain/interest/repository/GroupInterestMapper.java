package com.flabedu.peoplemeet.domain.interest.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupInterestMapper {

	List<GroupInterestVO> findAllByGroupId(Long groupId);
}
