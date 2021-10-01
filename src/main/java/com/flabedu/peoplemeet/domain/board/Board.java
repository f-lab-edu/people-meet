package com.flabedu.peoplemeet.domain.board;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.domain.board.type.BoardType;
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
public class Board extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "board_id")
	private Long id;

	private String title;

	@Lob
	private String content;

	@Enumerated(EnumType.STRING)
	private BoardType boardType;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "writer_id")
	private User writer;

	private String titleImageUrl;

	private Boolean isActive;
}
