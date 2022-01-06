package com.flabedu.peoplemeet.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flabedu.peoplemeet.domain.interest.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
