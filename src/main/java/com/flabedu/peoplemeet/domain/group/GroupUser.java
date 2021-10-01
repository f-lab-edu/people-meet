package com.flabedu.peoplemeet.domain.group;

import static javax.persistence.FetchType.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.domain.group.type.JoinStatus;
import com.flabedu.peoplemeet.domain.role.Role;
import com.flabedu.peoplemeet.domain.role.RoleLevel;
import com.flabedu.peoplemeet.domain.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 모임의 참가한 유저
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupUser extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_user_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@Enumerated(EnumType.STRING)
	private RoleLevel roleLevel;

	private LocalDateTime dateJoined;

	@Enumerated(EnumType.STRING)
	private JoinStatus joinStatus; // 가입상태
}
