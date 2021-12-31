package com.flabedu.peoplemeet.domain.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.flabedu.peoplemeet.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupRegion extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_region_id")
	private Long id;

	// @ManyToOne(fetch = LAZY)
	// @JoinColumn(name = "group_id")
	// private Group group;
	//
	// @ManyToOne(fetch = LAZY)
	// @JoinColumn(name = "region_id")
	// private Region region;

	private Long groupId;

	private Long regionId;

	// 아직 미사용
	private Integer priority;
}