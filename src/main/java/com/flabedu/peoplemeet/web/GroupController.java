package com.flabedu.peoplemeet.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flabedu.peoplemeet.dto.group.GroupChangeDTO;
import com.flabedu.peoplemeet.dto.group.GroupDetailDTO;
import com.flabedu.peoplemeet.dto.group.GroupSaveDTO;
import com.flabedu.peoplemeet.service.group.GroupService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupController {

	private final GroupService groupService;


	@GetMapping("/groups/{groupId}")
	public GroupDetailDTO groupDetail(@PathVariable("groupId") Long groupId) {
		return groupService.getGroupDetail(groupId);
	}

	@PostMapping("/groups")
	public GroupSaveResponse groupRegister(@RequestBody @Valid GroupSaveDTO groupSaveDTO) {
		Long groupId = groupService.register(groupSaveDTO);
		return new GroupSaveResponse(groupId);
	}

	@PatchMapping("/groups/{groupId}")
	public void groupChange(
		@PathVariable("groupId") Long groupId,
		@RequestBody @Valid GroupChangeDTO groupChangeDTO
	) {
		groupService.changeGroup(groupId, groupChangeDTO);
	}

	@DeleteMapping("/groups/{groupId}")
	public void groupDisable(@PathVariable("groupId") Long groupId) {
		groupService.groupDisable(groupId);
	}

	@Getter
	@RequiredArgsConstructor
	private static class GroupSaveResponse {
		private final Long id;
	}
}
