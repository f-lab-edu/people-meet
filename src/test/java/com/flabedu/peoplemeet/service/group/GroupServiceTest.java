package com.flabedu.peoplemeet.service.group;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flabedu.peoplemeet.domain.group.Group;
import com.flabedu.peoplemeet.domain.group.repository.GroupInterestRepository;
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

	@Mock
	GroupInterestRepository groupInterestRepository;

	@InjectMocks
	GroupService groupService;

	@Test
	void 그룹_추가_테스트() {
		GroupSaveDTO groupSaveDTO = createGroupSaveDTO();
		Group savedGroup = createGroup();
		when(groupRepository.save(any(Group.class))).thenReturn(savedGroup);

		groupService.register(groupSaveDTO);

		verify(groupRepository).save(any(Group.class));
		verify(groupRegionRepository).saveAll(anyCollection());
		verify(groupInterestRepository).saveAll(anyCollection());
	}

	private Group createGroup() {
		return Group.builder()
			.id(1L)
			.description("test group")
			.name("test")
			.maximumPeople(5)
			.build();
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