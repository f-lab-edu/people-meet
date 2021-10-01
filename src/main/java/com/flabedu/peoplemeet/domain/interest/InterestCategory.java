package com.flabedu.peoplemeet.domain.interest;

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

/**
 * 관심 카테고리
 * ex) IT, 건강, 문화 등
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InterestCategory extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "interest_category_id")
	private Long id;

	private String name;

}
