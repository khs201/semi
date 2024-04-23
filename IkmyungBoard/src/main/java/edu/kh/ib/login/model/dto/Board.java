package edu.kh.ib.login.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	private int     boardNo;
	private String  boardTitle;
	private String  boardContent;
	private String  boardWriteDate;
	private String  boardUpdateDate;
	private int     readCount;
	private String  boardDelFl;
	private int     memberNo;
	private int     boardCode;
	


}
