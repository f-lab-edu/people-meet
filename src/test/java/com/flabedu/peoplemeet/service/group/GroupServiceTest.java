package com.flabedu.peoplemeet.service.group;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flabedu.peoplemeet.domain.group.repository.GroupRegionRepository;
import com.flabedu.peoplemeet.domain.group.repository.GroupRepository;
import com.flabedu.peoplemeet.dto.group.GroupSaveDTO;
import com.flabedu.peoplemeet.dto.interest.InterestId;
import com.flabedu.peoplemeet.dto.region.RegionId;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

	@Mock
	GroupRepository groupRepository;

	@Mock
	GroupRegionRepository groupRegionRepository;

	@InjectMocks
	GroupService groupService;

	@Test
	void 그룹_추가_테스트() {
		GroupSaveDTO groupSaveDTO = createGroupSaveDTO();

		when(groupService.register(groupSaveDTO)).thenReturn(1L);

		groupService.register(groupSaveDTO);

	}

	private GroupSaveDTO createGroupSaveDTO() {
		GroupSaveDTO groupSaveDTO = new GroupSaveDTO();
		groupSaveDTO.setTitle("test");
		groupSaveDTO.setRegions(Arrays.asList(new RegionId(1L), new RegionId(2L)));
		groupSaveDTO.setInterests(Arrays.asList(new InterestId(1L), new InterestId(2L)));
		groupSaveDTO.setMaxPeople(5);
		groupSaveDTO.setDescription("test group");
		return groupSaveDTO;
	}

}