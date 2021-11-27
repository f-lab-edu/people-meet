package com.flabedu.peoplemeet.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flabedu.peoplemeet.domain.interest.GroupInterest;

public interface GroupInterestRepository extends JpaRepository<GroupInterest, Long>, GroupInterestMapper {
}
