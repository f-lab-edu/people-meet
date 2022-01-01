package com.flabedu.peoplemeet.domain.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.domain.group.type.GroupStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 모임
 */

@Entity
@Table(name = "groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Group extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_id")
	private Long id;

	private String name;

	private Integer maximumPeople;

	@Lob
	private String description;

	private String titleImageUrl;

	@Enumerated(EnumType.STRING)
	private GroupStatus status = GroupStatus.ENABLE;

	public void disable() {
		this.status = GroupStatus.DISABLE;
	}
}
