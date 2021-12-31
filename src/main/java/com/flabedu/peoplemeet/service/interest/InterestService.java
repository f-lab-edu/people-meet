package com.flabedu.peoplemeet.service.interest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flabedu.peoplemeet.common.CacheService;
import com.flabedu.peoplemeet.dto.interest.InterestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterestService {

	private final CacheService cacheService;

	public InterestDto getInterest(final Long id) {
		return cacheService.getInterest(id);
	}
}
