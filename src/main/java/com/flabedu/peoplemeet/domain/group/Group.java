package com.flabedu.peoplemeet.domain.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.flabedu.peoplemeet.domain.BaseEntity;

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

}
