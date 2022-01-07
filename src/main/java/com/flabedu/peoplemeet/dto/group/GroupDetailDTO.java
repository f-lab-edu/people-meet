package com.flabedu.peoplemeet.dto.group;

import lombok.Getter;

@Getter
public class GroupDetailDTO {

	private final String title;

	private final String description;

	private final String titleImageUri;

	public GroupDetailDTO(String title, String description, String titleImageUri) {
		this.title = title;
		this.description = description;
		this.titleImageUri = titleImageUri;
	}
}
