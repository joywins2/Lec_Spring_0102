package org.joy.domain;


/*
...463p.
create table ztbl_message_user ( 
 uid varchar(50) NOT NULL, 
 upw varchar(50) NOT NULL,
 uname varchar(100) NOT NULL,
 upoint int NOT NULL DEFAULT 0,
 primary key(uid)
);

insert into ztbl_message_user(uid, upw, uname) values ('user00','user00','IRON MAN');
insert into ztbl_message_user(uid, upw, uname) values ('user01','user01','CAPTAIN');
insert into ztbl_message_user(uid, upw, uname) values ('user02','user02','HULK');
insert into ztbl_message_user(uid, upw, uname) values ('user03','user03','Thor');
insert into ztbl_message_user(uid, upw, uname) values ('user10','user10','Quick Silver');

select * from ztbl_message_user;

create table ztbl_message (
 mid int not null auto_increment,
 receiver_id varchar(50) not null, 
 sender_id varchar(50) not null, 
 message text not null,
 open_date timestamp,
 send_date timestamp not null default now(),
 primary key(mid)
);

alter table ztbl_message add constraint fk_receiver_id 
foreign key (receiver_id) references ztbl_message_user (uid); 

alter table ztbl_message add constraint fk_sender_id 
foreign key (sender_id) references ztbl_message_user (uid); 
*/

import java.util.Date;

public class MsgVO {

	private Integer mid;
	private String receiver_id;
	private String sender_id;
	private String message;
	private Date open_date;
	private Date send_date;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	@Override
	public String toString() {
		return "MessageVO [mid=" + mid 
					  + ", receiver_id=" + receiver_id 
					  + ", sender_id=" + sender_id 
					  + ", message=" + message 
					  + ", open_date=" + open_date 
					  + ", send_date=" + send_date + "]";
	}
	
	

}
