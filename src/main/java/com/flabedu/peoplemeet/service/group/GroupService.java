package com.flabedu.peoplemeet.service.group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flabedu.peoplemeet.domain.group.Group;
import com.flabedu.peoplemeet.domain.group.GroupRegion;
import com.flabedu.peoplemeet.domain.group.repository.GroupRegionRepository;
import com.flabedu.peoplemeet.domain.group.repository.GroupRepository;
import com.flabedu.peoplemeet.domain.group.repository.GroupInterestRepository;
import com.flabedu.peoplemeet.dto.group.GroupChangeDTO;
import com.flabedu.peoplemeet.dto.group.GroupDetailDTO;
import com.flabedu.peoplemeet.dto.group.GroupSaveDTO;
import com.flabedu.peoplemeet.dto.region.RegionId;
import com.flabedu.peoplemeet.exception.GroupNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupService {

	private final GroupRepository groupRepository;

	private final GroupInterestRepository groupInterestRepository;

	private final GroupRegionRepository groupRegionRepository;

	public GroupDetailDTO getGroupDetail(final Long groupId){
		Group group = getGroup(groupId);

		return new GroupDetailDTO(group.getName(), group.getDescription(), group.getTitleImageUrl());
	}

	private Group getGroup(Long groupId) {
		return groupRepository.findById(groupId)
			.orElseThrow(() -> new GroupNotFoundException(String.format("해당 그룹을 찾을 수 없습니다 [%d]", groupId)));
	}

	// TODO Interest, User 관계 연결 추가
	@Transactional
	public Long register(final GroupSaveDTO groupSaveDTO){
		Group savedGroup = groupRepository.save(groupSaveDTO.toGroup());

		groupRegionMapping(savedGroup, groupSaveDTO.getRegions());

		return savedGroup.getId();
	}

	private void groupRegionMapping(final Group savedGroup, final List<RegionId> regions) {
		final List<GroupRegion> groupRegions = new ArrayList<>();
		final Long groupId = savedGroup.getId();

		for (RegionId region : regions) {
			groupRegions.add(
				GroupRegion.builder()
					.groupId(groupId)
					.regionId(region.getId())
					.priority(0)
					.build()
			);
		}

		groupRegionRepository.saveAll(groupRegions);
	}

	// TODO 권한 체크 추가
	@Transactional
	public void groupDisable(final Long groupId){
		Group group = getGroup(groupId);

		group.disable();
	}

	// TODO 권한 체크 추가, Region, Interest mapping 추가
	@Transactional
	public void changeGroup(final Long groupId, final GroupChangeDTO groupChangeDTO){
		Group group = getGroup(groupId);

		group.change(
			groupChangeDTO.getTitle(),
			groupChangeDTO.getMaxPeople(),
			groupChangeDTO.getTitleImageUri()
		);
	}

}
