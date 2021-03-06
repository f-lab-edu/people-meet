package com.flabedu.peoplemeet.domain.board;

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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comments extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "comments_id")
	private Long id;

	private String content;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "writer_id")
	private User writer;
}
