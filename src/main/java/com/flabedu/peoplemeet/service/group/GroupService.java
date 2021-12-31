package com.flabedu.peoplemeet.service.group;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flabedu.peoplemeet.domain.group.repository.GroupRepository;
import com.flabedu.peoplemeet.domain.interest.repository.GroupInterestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupService {

	private final GroupRepository groupRepository;

	private final GroupInterestRepository groupInterestRepository;

}
