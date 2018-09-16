package org.joy.domain;

import java.sql.Timestamp;
import java.util.Date;

/* ...128p.
create table ZTBL_MEMBER
(
	 user_id	nvarchar(50)	not null
    ,user_pw	nvarchar(50)	not null
    ,user_name	nvarchar(50)	not null
    ,email		nvarchar(100)
	,reg_date 	TIMESTAMP NOT NULL DEFAULT 0
	,update_date TIMESTAMP DEFAULT now()
	//,update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP    
    ,primary key(user_id)
);
*/

public class MemberVO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String email;
	private Date regDate;
	private Date updateDate;
	

	public MemberVO(String user_id, String user_pw, String user_name, String email) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.email = email;
	}	

/*	public MemberVO(String user_id, String user_pw, String user_name, String email, Timestamp regDate, Timestamp updateDate) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.email = email;
		this.regDate = new Date(regDate.getTime());
		this.updateDate = new Date(updateDate.getTime());
	}*/
	
	public MemberVO(String user_id, String user_pw, String user_name, String email, Date regDate, Date updateDate) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.email = email;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

/*	
 * ...How to convert TimeStamp to Date in Java?
 *    https://stackoverflow.com/questions/11839246/how-to-convert-timestamp-to-date-in-java/11839276
    public void setRegDate(Timestamp regDate) {
		this.regDate = new Date(regDate.getTime());
	}*/

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

/*	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = new Date(updateDate.getTime());
	}*/

	@Override
	public String toString() {
		return "MemberVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", email=" + email
				+ ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}
		
}
