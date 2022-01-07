package com.flabedu.peoplemeet.domain.group.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flabedu.peoplemeet.dto.group.GroupInterestVO;

@Mapper
public interface GroupInterestMapper {

	List<GroupInterestVO> findAllByGroupId(Long groupId);
}
