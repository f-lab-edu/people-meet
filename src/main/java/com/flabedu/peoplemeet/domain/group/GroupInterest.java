package com.flabedu.peoplemeet.domain.group;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.domain.group.Group;
import com.flabedu.peoplemeet.domain.interest.Interest;

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
public class GroupInterest extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_interest_id")
	private Long id;

	private Long groupId;

	private Long interestId;

	private Integer priority;
}
