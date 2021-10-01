package com.flabedu.peoplemeet.domain.interest;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.domain.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 관심사
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserInterest extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_interest_id")
	private Long id;

	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "interest_id")
	private Interest interest;

	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	private Integer priority;

}
