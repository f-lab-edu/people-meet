package com.flabedu.peoplemeet.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flabedu.peoplemeet.domain.group.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
