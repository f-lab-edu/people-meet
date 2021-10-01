package com.flabedu.peoplemeet.domain.meeting;

import static javax.persistence.FetchType.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.domain.group.Group;
import com.flabedu.peoplemeet.domain.region.Region;

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
public class Meeting extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "meeting_id")
	private Long id;

	private Integer cost;

	private String title;

	private Integer maxPeople;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "region_id")
	private Region region;

}
