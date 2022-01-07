package com.flabedu.peoplemeet.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flabedu.peoplemeet.domain.group.GroupInterest;
import com.flabedu.peoplemeet.domain.group.mapper.GroupInterestMapper;

public interface GroupInterestRepository extends JpaRepository<GroupInterest, Long>, GroupInterestMapper {
}
