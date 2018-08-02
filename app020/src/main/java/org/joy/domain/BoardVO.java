package org.joy.domain;

import java.util.Date;

/*
 * ...174p.
	create table ztbl_board
	(
		bno	int not null auto_increment
	    ,title	varchar(200) not null
	    ,content text null
	    ,writer	varchar(50) not null
	    ,regdate	timestamp not null	default	now()
	    #,UPDATEDATE	timestamp	default	now()
	    ,view_cnt	int default 0
	    ,primary key(bno)
	); 
	
 * ...502p.
	alter table ztbl_board 
	add column reply_cnt int default 0;
	
 * ...507p.
	UPDATE ztbl_board
	SET reply_cnt = (SELECT COUNT(rno) FROM zbook_ex.ztbl_reply
						WHERE bno = ztbl_board.bno)
	WHERE bno > 0;
 * 	  
 */

public class BoardVO {

	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int view_count;
	private int reply_cnt;
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", view_count=" + view_count + ", reply_cnt=" + reply_cnt + "]";
	}
	
	
}



